package com.github.itdachen.framework.limiter.aspect;

import com.github.itdachen.framework.context.exception.RateLimiterException;
import com.github.itdachen.framework.limiter.annotation.RedisRateLimiter;
import com.github.itdachen.framework.limiter.enums.LimiterType;
import com.google.common.collect.ImmutableList;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Description: 分布式限流切面
 * Created by 王大宸 on 2023-07-03 11:20
 * Created with IntelliJ IDEA.
 */
@Aspect
@Configuration
public class RedisRateLimiterAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisRateLimiterAspect.class);


    private final RedisTemplate<String, Object> redisTemplate;

    public RedisRateLimiterAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Around("@annotation(com.github.itdachen.framework.limiter.annotation.RedisRateLimiter)")
    public Object around(ProceedingJoinPoint pjp) throws RateLimiterException {
        final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        final Method method = methodSignature.getMethod();
        RedisRateLimiter annotation = method.getAnnotation(RedisRateLimiter.class);
        LimiterType limitType = annotation.limitType();

        String name = annotation.name();
        String key;

        int period = annotation.period();
        int count = annotation.count();

        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = annotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(annotation.prefix(), key));
        try {
            String luaScript = buildLuaScript();
            DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
            Number number = redisTemplate.execute(redisScript, keys, count, period);
            logger.info("Access try count is {} for name = {} and key = {}", number, name, key);
            if (number != null && number.intValue() == 1) {
                return pjp.proceed();
            }
            throw new RateLimiterException(annotation.msg());
        } catch (Throwable e) {
            if (e instanceof RateLimiterException) {
                logger.debug("令牌桶={}，获取令牌失败", key);
                throw new RateLimiterException(e.getLocalizedMessage());
            }
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

    public String buildLuaScript() {
        return "redis.replicate_commands(); local listLen,time" +
                "\nlistLen = redis.call('LLEN', KEYS[1])" +
                // 不超过最大值，则直接写入时间
                "\nif listLen and tonumber(listLen) < tonumber(ARGV[1]) then" +
                "\nlocal a = redis.call('TIME');" +
                "\nredis.call('LPUSH', KEYS[1], a[1]*1000000+a[2])" +
                "\nelse" +
                // 取出现存的最早的那个时间，和当前时间比较，看是小于时间间隔
                "\ntime = redis.call('LINDEX', KEYS[1], -1)" +
                "\nlocal a = redis.call('TIME');" +
                "\nif a[1]*1000000+a[2] - time < tonumber(ARGV[2])*1000000 then" +
                // 访问频率超过了限制，返回0表示失败
                "\nreturn 0;" +
                "\nelse" +
                "\nredis.call('LPUSH', KEYS[1], a[1]*1000000+a[2])" +
                "\nredis.call('LTRIM', KEYS[1], 0, tonumber(ARGV[1])-1)" +
                "\nend" +
                "\nend" +
                "\nreturn 1;";
    }

    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}

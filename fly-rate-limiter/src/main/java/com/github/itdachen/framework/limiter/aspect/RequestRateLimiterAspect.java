package com.github.itdachen.framework.limiter.aspect;

import com.github.itdachen.framework.context.exception.RateLimiterException;
import com.github.itdachen.framework.limiter.annotation.RequestRateLimiter;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Description: 限流切面
 * Created by 王大宸 on 2023-07-03 10:53
 * Created with IntelliJ IDEA.
 */
@Aspect
@Component
public class RequestRateLimiterAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestRateLimiterAspect.class);

    private final Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Around("@annotation(com.github.itdachen.framework.limiter.annotation.RequestRateLimiter)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            //拿 RequestRateLimiter 注解
            RequestRateLimiter requestRateLimiter = method.getAnnotation(RequestRateLimiter.class);
            if (null == requestRateLimiter) {
                return joinPoint.proceed();
            }

            //key作用：不同的接口，不同的流量控制
            key = requestRateLimiter.key();
            RateLimiter rateLimiter;
            //验证缓存是否有命中key
            if (!limitMap.containsKey(key)) {
                // 创建令牌桶
                rateLimiter = RateLimiter.create(requestRateLimiter.total());
                limitMap.put(key, rateLimiter);
                logger.info("新建了令牌桶: {}，容量: {}", key, requestRateLimiter.total());
            }
            rateLimiter = limitMap.get(key);
            // 拿令牌
            boolean acquire = rateLimiter.tryAcquire(requestRateLimiter.timeout(), requestRateLimiter.timeunit());
            // 拿不到命令，直接返回异常提示
            if (!acquire) {
                logger.debug("令牌桶: {}，获取令牌失败", key);
                throw new RateLimiterException(requestRateLimiter.msg());
            }
            return joinPoint.proceed();
        } catch (Exception e) {
            if (e instanceof RateLimiterException) {
                logger.debug("令牌桶: {}，获取令牌失败", key);
                throw new RateLimiterException(e.getMessage());
            }
            throw new RateLimiterException("系统繁忙, 请稍后重试！");
        }
    }


}

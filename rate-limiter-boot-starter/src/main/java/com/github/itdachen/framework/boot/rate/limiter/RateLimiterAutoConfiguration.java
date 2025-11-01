package com.github.itdachen.framework.boot.rate.limiter;

import com.github.itdachen.framework.boot.rate.limiter.aspect.RedisRateLimiterAspect;
import com.github.itdachen.framework.boot.rate.limiter.aspect.RequestRateLimiterAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 自动配置
 *
 * @author 王大宸
 * @date 2023-12-23 0:12
 */
@Configuration
@EnableAspectJAutoProxy
public class RateLimiterAutoConfiguration {

    private final RedisTemplate<String, Object> redisTemplate;

    public RateLimiterAutoConfiguration(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Bean
    public RedisRateLimiterAspect redisRateLimiterAspect(){
        return new RedisRateLimiterAspect(redisTemplate);
    }

    @Bean
    public RequestRateLimiterAspect requestRateLimiterAspect(){
        return new RequestRateLimiterAspect();
    }


}

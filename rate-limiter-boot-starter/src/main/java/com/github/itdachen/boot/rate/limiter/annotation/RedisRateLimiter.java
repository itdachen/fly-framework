package com.github.itdachen.boot.rate.limiter.annotation;

import com.github.itdachen.boot.rate.limiter.enums.LimiterType;

import java.lang.annotation.*;

/**
 * Description: 分布式限流注解
 * 基于 redis + lua 脚本的分布式限流
 * Created by 王大宸 on 2023-07-03 11:12
 * Created with IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RedisRateLimiter {

    // 资源key
    String key() default "";

    // 前缀
    String prefix() default "fly";

    // 最多访问次数
    int total();

    // 限流时间,单位秒
    int timeout() default 60;

    // 类型
    LimiterType limitType() default LimiterType.DEFAULT;

    // 提示信息
    String msg() default "系统繁忙,请稍后再试";

}

package com.github.itdachen.framework.limiter.annotation;

import com.github.itdachen.framework.limiter.enums.LimiterType;

/**
 * Description: 分布式限流注解
 * 基于 redis + lua 脚本的分布式限流
 * Created by 王大宸 on 2023-07-03 11:12
 * Created with IntelliJ IDEA.
 */
public @interface RedisRateLimiter {

    // 资源名称
    String name() default "";

    // 资源key
    String key() default "";

    // 前缀
    String prefix() default "";

    // 时间
    int period();

    // 最多访问次数
    int count();

    // 类型
    LimiterType limitType() default LimiterType.CUSTOMER;

    // 提示信息
    String msg() default "系统繁忙,请稍后再试";


}

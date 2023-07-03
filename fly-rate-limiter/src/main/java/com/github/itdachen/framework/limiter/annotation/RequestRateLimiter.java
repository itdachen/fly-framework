package com.github.itdachen.framework.limiter.annotation;

import java.util.concurrent.TimeUnit;

/**
 * Description: 自定义限流注解 (单机模式)
 * Created by 王大宸 on 2023-07-03 9:56
 * Created with IntelliJ IDEA.
 */
public @interface RequestRateLimiter {

    // 资源key
    String key() default "";

    // 最多访问次数
    double permitsPerSecond();

    // 时间
    long timeout();

    // 时间类型
    TimeUnit timeunit() default TimeUnit.MILLISECONDS;

    // 提示信息
    String msg() default "系统繁忙,请稍后再试";

}

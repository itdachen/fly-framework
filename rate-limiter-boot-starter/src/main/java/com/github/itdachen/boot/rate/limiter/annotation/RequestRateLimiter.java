package com.github.itdachen.boot.rate.limiter.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Description: 自定义限流注解 (单机模式)
 * Created by 王大宸 on 2023-07-03 9:56
 * Created with IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RequestRateLimiter {

    // 资源key
    String key() default "";

    // 最多访问次数
    double total();

    // 时间
    long timeout() default 1000;

    // 时间类型, 默认: 毫秒
    TimeUnit timeunit() default TimeUnit.MILLISECONDS;

    // 提示信息
    String msg() default "系统繁忙,请稍后再试";

}

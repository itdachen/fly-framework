package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口签名注解
 *
 * @author 剑鸣秋朔
 * @date 2024-11-04 10:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ApiSignVerify {

    /**
     * 接口名称
     */
    String value() default "";

    /**
     * 接口版本号
     */
    String version() default "v1";

    /**
     * 校验失败返回消息
     */
    String msg() default "签名验证失败！";

}

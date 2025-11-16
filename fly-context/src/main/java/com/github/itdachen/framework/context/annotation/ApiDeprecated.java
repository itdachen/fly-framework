package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: API 作废注解
 * Created by 剑鸣秋朔 on 2023-07-26 21:59
 * Created with IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ApiDeprecated {

    String msg() default "该接口已作废, 请使用最新的接口";

}

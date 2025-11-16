package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 忽略统一响应注解定义
 * Created by 剑鸣秋朔 on 2023/02/12 21:54
 * Created with IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface IgnoreResponseAdvice {
}

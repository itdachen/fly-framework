package com.itdachen.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 忽略用户鉴权注解
 * * 可以在 controller 类中使用, 也可以在方法中使用
 * * 如果在类上使用, 则整个 controller 中的方法都会被忽略 token 鉴权
 * Created by 王大宸 on 2023/02/06 15:00
 * Created with IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface IgnoreUserToken {
}

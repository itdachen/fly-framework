package com.itdachen.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 检查用户 token
 * Created by 王大宸 on 2023/02/12 21:54
 * Created with IntelliJ IDEA.
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface CheckUserToken {
}

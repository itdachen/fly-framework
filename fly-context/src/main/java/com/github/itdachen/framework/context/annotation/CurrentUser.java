package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:  在 Controller 的方法参数中使用此注解
 * * 该方法在映射时会注入当前登录的 CurrentUserDetails 信息
 * Created by 剑鸣秋朔 on 2023/04/10 10:19
 * Created with IntelliJ IDEA.
 */
@Target(ElementType.PARAMETER)// 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface CurrentUser {
}

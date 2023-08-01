package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 页面预览记录注册, 只能使用在方法上
 * Created by 王大宸 on 2023-07-28 10:51
 * Created with IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PagePreviewLog {

    /***
     * 页面名称
     */
    String title() default "";

    /**
     * 页面类型
     */
    String pageType() default "";

    /**
     * 客户端id
     */
    String clientId() default "";

}

package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.*;

/**
 * 动态数据源自定义注解
 *
 * @author 剑鸣秋朔
 * @date 2023-12-30 17:39
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DynamicDataSource {

    /**
     * 指定的数据源
     */
    String value() default "master";

}

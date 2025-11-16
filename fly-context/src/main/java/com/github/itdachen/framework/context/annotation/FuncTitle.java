package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.*;

/**
 * 功能名称注解
 *
 * @author 剑鸣秋朔
 * @date 2024/4/19 21:28
 */
@Documented //会被javadoc命令识别
@Retention(RetentionPolicy.RUNTIME) //相当于作用时期，比如：运行期、编译期
@Target({ElementType.TYPE, ElementType.METHOD})  //相当于作用域,比如方法、类
public @interface FuncTitle {

    /**
     * 操作模块
     */
    String value();

    /**
     * 客户端id
     */
    String appId() default "PC";

    /**
     * 版本号
     */
    String version() default "v1";


}

package com.github.itdachen.framework.context.annotation;

import com.github.itdachen.framework.context.enums.LogTypeEnum;

import java.lang.annotation.*;

/**
 * Description: 操作日志
 * Created by 剑鸣秋朔 on 2023/02/12 21:55
 * Created with IntelliJ IDEA.
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    LogTypeEnum value() default LogTypeEnum.OTHER;

    /**
     * 版本号
     */
    String version() default "v1.0";

}

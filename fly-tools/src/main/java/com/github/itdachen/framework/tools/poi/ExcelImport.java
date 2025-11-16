package com.github.itdachen.framework.tools.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: ExcelImport
 * Created by 剑鸣秋朔 on 2023-09-21 9:52
 * Created with IntelliJ IDEA.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelImport {


    /**
     * 字段名称
     */
    String value();

    /**
     * 导出映射，格式如：0-未知;1-男;2-女
     */
    String kv() default "";

    /**
     * 是否为必填字段（默认为非必填）
     */
    boolean required() default false;

    /**
     * 最大长度（默认255）
     */
    int maxLength() default 255;

    /**
     * 导入唯一性验证（多个字段则取联合验证）
     */
    boolean unique() default false;


}

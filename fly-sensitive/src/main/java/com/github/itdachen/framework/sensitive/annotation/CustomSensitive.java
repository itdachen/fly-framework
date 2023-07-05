package com.github.itdachen.framework.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.itdachen.framework.sensitive.serializer.CustomSensitiveSerializer;
import com.github.itdachen.framework.sensitive.serializer.SensitiveJsonSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 万能脱敏注解, 自定义长度及符号
 * Created by 王大宸 on 2023-07-05 17:00
 * Created with IntelliJ IDEA.
 */
@Target(ElementType.FIELD) // 作用在字段上
@Retention(RetentionPolicy.RUNTIME) // class文件中保留，运行时也保留，能通过反射读取到
@JacksonAnnotationsInside // 表示自定义自己的注解
@JsonSerialize(using = CustomSensitiveSerializer.class)  // 该注解使用序列化的方式
public @interface CustomSensitive {

    /**
     * 前置不需要打码的长度
     */
    int start() default 1;

    /**
     * 后置不需要打码的长度
     */
    int end() default 1;

    /**
     * 用什么打码
     */
    String symbol() default "*";

}

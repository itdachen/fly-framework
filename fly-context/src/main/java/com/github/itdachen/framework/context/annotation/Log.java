package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.*;

/**
 * Description: 操作日志
 * Created by 王大宸 on 2023/02/12 21:55
 * Created with IntelliJ IDEA.
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /***
     * 操作标题: 新增,修改,删除
     */
    String title();

    /**
     * 操作类型: SAVE, UPDATE, DELETE等
     */
    String type();

}

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
     * 操作标题: 菜单管理, 用户管理
     */
    String title();

    /**
     * 操作类型: 新增, 修改, 删除, 查询等
     */
    String type();

}

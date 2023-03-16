package com.github.itdachen.framework.context.annotation;

import java.lang.annotation.*;

/**
 * Description:
 * Created by 王大宸 on 2023/02/12 21:55
 * Created with IntelliJ IDEA.
 */
@Documented //会被javadoc命令识别
@Retention(RetentionPolicy.RUNTIME) //相当于作用时期，比如：运行期、编译期
@Target({ElementType.TYPE, ElementType.METHOD})  //相当于作用域,比如方法、类
public @interface CheckApiClient {

    /**
     * 操作模块
     */
    String title();

    /**
     * 客户端id
     */
    String clientId();

}

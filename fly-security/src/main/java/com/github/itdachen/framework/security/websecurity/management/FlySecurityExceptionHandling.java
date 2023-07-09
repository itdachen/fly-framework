package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.security.handler.FlyAccessDeniedExceptionHandler;
import com.github.itdachen.framework.security.websecurity.IFlySecurityExceptionHandling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * Description: 认证异常处理
 * Created by 王大宸 on 2023-07-09 13:01
 * Created with IntelliJ IDEA.
 */
@Component
public class FlySecurityExceptionHandling implements IFlySecurityExceptionHandling {

    /* 权限异常配置 */
    private final FlyAccessDeniedExceptionHandler accessDeniedExceptionHandler;

    public FlySecurityExceptionHandling(FlyAccessDeniedExceptionHandler accessDeniedExceptionHandler) {
        this.accessDeniedExceptionHandler = accessDeniedExceptionHandler;
    }

    @Override
    public void exceptionHandling(HttpSecurity http) throws Exception {
        /* 权限异常配置 */
        http.exceptionHandling(handler -> handler
                .accessDeniedHandler(accessDeniedExceptionHandler)
        );
    }
}

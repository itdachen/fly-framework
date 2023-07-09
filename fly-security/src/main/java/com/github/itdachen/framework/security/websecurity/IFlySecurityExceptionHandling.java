package com.github.itdachen.framework.security.websecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Description: 认证异常处理
 * Created by 王大宸 on 2023-07-09 12:54
 * Created with IntelliJ IDEA.
 */
public interface IFlySecurityExceptionHandling {

    void exceptionHandling(HttpSecurity http) throws Exception;

}

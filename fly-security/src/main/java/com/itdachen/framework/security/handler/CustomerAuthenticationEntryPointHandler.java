package com.itdachen.framework.security.handler;

import com.itdachen.framework.security.properties.SecurityBrowserProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 匿名未登录的时候访问,需要登录的资源的调用类
 * Created by 王大宸 on 2022-09-23 16:42
 * Created with IntelliJ IDEA.
 */
public class CustomerAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final SecurityBrowserProperties securityProperties;

    public CustomerAuthenticationEntryPointHandler(SecurityBrowserProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authentication) throws IOException, ServletException {
        // 跳转到登录页面
        response.sendRedirect(securityProperties.getSignOutUrl());
    }
}

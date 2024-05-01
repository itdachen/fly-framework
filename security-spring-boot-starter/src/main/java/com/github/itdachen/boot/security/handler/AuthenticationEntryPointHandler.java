package com.github.itdachen.boot.security.handler;

import com.github.itdachen.boot.autoconfigure.security.properties.FlySecurityProperties;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Description: 匿名未登录的时候访问,需要登录的资源的调用类
 * Created by 王大宸 on 2022-09-23 16:42
 * Created with IntelliJ IDEA.
 */
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final FlySecurityProperties flySecurityProperties;

    public AuthenticationEntryPointHandler(FlySecurityProperties flySecurityProperties) {
        this.flySecurityProperties = flySecurityProperties;
    }


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authentication) throws IOException, ServletException {
        // 跳转到登录页面
        response.sendRedirect(flySecurityProperties.getSignOutUrl());
    }
}

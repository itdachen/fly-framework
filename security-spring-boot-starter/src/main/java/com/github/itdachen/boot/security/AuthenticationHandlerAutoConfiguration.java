package com.github.itdachen.boot.security;

import com.github.itdachen.boot.autoconfigure.security.properties.SecurityProperties;
import com.github.itdachen.boot.security.handler.AuthenticationFailureHandler;
import com.github.itdachen.boot.security.handler.AuthenticationFailureListener;
import com.github.itdachen.boot.security.handler.AuthenticationSuccessHandler;
import com.github.itdachen.boot.security.log.IAuthFailureCredentialsLogHandler;
import com.github.itdachen.boot.security.log.IAuthSuccessCredentialsLogHandler;
import com.github.itdachen.boot.security.log.management.AuthFailureLogHandlerManagement;
import com.github.itdachen.boot.security.log.management.AuthSuccessLogHandlerManagement;
import com.github.itdachen.boot.security.user.CurrentUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 认证成功和失败配置
 *
 * @author 王大宸
 * @date 2023-12-23 1:00
 */
@Configuration
public class AuthenticationHandlerAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationHandlerAutoConfiguration.class);

    private final SecurityProperties securityProperties;

    public AuthenticationHandlerAutoConfiguration(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /***
     * 认证成功处理器
     *
     * @author 王大宸
     * @date 2023/12/23 1:03
     * @return com.github.itdachen.boot.security.handler.AuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler(securityProperties);
    }

    /***
     * 认证失败处理器
     *
     * @author 王大宸
     * @date 2023/12/23 1:03
     * @return com.github.itdachen.boot.security.handler.AuthenticationFailureHandler
     */
    @Bean("authenticationFailureHandler")
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler(securityProperties);
    }

    /***
     * 指定默认登录失败记录方法
     *
     * @author 王大宸
     * @date 2023/7/9 14:34
     * @return com.github.itdachen.framework.security.log.IAuthFailureCredentialsLogHandler
     */
    @Bean
    @ConditionalOnMissingBean(IAuthFailureCredentialsLogHandler.class)
    public IAuthFailureCredentialsLogHandler authFailureCredentialsLogHandler() {
        return new AuthFailureLogHandlerManagement();
    }

    /***
     * 指定默认登录成功记录方式
     *
     * @author 王大宸
     * @date 2023/7/9 14:34
     * @return com.github.itdachen.framework.security.log.IAuthSuccessCredentialsLogHandler
     */
    @Bean
    @ConditionalOnMissingBean(IAuthSuccessCredentialsLogHandler.class)
    public IAuthSuccessCredentialsLogHandler authSuccessCredentialsLogHandler() {
        return new AuthSuccessLogHandlerManagement();
    }


    @Bean
    public AuthenticationFailureListener authenticationFailureListener() {
        logger.info("正在配置登录失败监听...");
        return new AuthenticationFailureListener();
    }

//    @Bean
//    @EventListener
//    public AuthenticationFailureBadCredentialsEvent flyAuthenticationFailureBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {
//        System.out.println("-----------------用户名或者密码错误-----------------");
//        CurrentUserInfo userInfo = (CurrentUserInfo) event.getAuthentication().getPrincipal();
//        String username = event.getAuthentication().getPrincipal().toString();
//        System.err.println("登录失败: " + username);
//        System.err.println("登录失败: " + userInfo.toString());
//        return event;
//
//    }

}

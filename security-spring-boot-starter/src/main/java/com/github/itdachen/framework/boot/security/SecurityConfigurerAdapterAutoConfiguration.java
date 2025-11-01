package com.github.itdachen.framework.boot.security;

import com.github.itdachen.framework.boot.security.authentication.mobile.MobileAuthenticationSecurityConfigurer;
import com.github.itdachen.framework.boot.security.authentication.platforms.PlatformAuthenticationSecurityConfigurerAdapter;
import com.github.itdachen.framework.boot.security.details.AbstractSecurityUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 内置认证方式
 *
 * @author 王大宸
 * @date 2023-12-23 1:36
 */
@Configuration
public class SecurityConfigurerAdapterAutoConfiguration {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AbstractSecurityUserDetailsService userDetailsService;

    public SecurityConfigurerAdapterAutoConfiguration(AuthenticationSuccessHandler authenticationSuccessHandler,
                                                      AuthenticationFailureHandler authenticationFailureHandler,
                                                      AbstractSecurityUserDetailsService userDetailsService) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.userDetailsService = userDetailsService;
    }
    
    /***
    * 第三方平台跳转登录认证处理器
    *
    * @author 王大宸
    * @date 2023/12/23 1:39
    * @return com.github.itdachen.boot.security.authentication.platforms.PlatformAuthenticationSecurityConfigurerAdapter
    */
    @Bean("platformAuthenticationSecurityAdapter")
    public PlatformAuthenticationSecurityConfigurerAdapter platformAuthenticationSecurityConfigurerAdapter() {
        return new PlatformAuthenticationSecurityConfigurerAdapter(authenticationSuccessHandler,
                authenticationFailureHandler,
                userDetailsService);
    }

    
    /***
    * 手机验证码登录
    *
    * @author 王大宸
    * @date 2023/12/23 1:39
    * @return com.github.itdachen.boot.security.authentication.mobile.MobileAuthenticationSecurityConfigurer
    */
    @Bean("mobileAuthenticationSecurityConfigurer")
    public MobileAuthenticationSecurityConfigurer mobileAuthenticationSecurityConfigurer() {
        return new MobileAuthenticationSecurityConfigurer(authenticationSuccessHandler,
                authenticationFailureHandler,
                userDetailsService);
    }


}

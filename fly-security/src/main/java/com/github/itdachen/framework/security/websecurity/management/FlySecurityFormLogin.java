package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.framework.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.github.itdachen.framework.security.validate.code.filter.ValidateCodeFilter;
import com.github.itdachen.framework.security.websecurity.IFlySecurityFormLogin;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Description: 表单登录 ( 账号密码登录/验证码登录 )
 * Created by 王大宸 on 2023-07-09 13:02
 * Created with IntelliJ IDEA.
 */
@Component
public class FlySecurityFormLogin implements IFlySecurityFormLogin {

    private final ValidateCodeFilter validateCodeFilter;
    /* 短信验证码登录 */
    private final SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    /* 登录成功处理 */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    /* 登录失败处理 */
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final FlySecurityProperties securityProperties;

    public FlySecurityFormLogin(ValidateCodeFilter validateCodeFilter,
                                SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig,
                                AuthenticationSuccessHandler authenticationSuccessHandler,
                                AuthenticationFailureHandler authenticationFailureHandler,
                                FlySecurityProperties securityProperties) {
        this.validateCodeFilter = validateCodeFilter;
        this.smsCodeAuthenticationSecurityConfig = smsCodeAuthenticationSecurityConfig;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.securityProperties = securityProperties;
    }

    @Override
    public void formLogin(HttpSecurity http) throws Exception {
        /* 验证码过滤器 */
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);

        /* 短信验证码登录 */
        http.apply(smsCodeAuthenticationSecurityConfig);

        /* 表单登录 */
        http.formLogin(login -> login
                .loginPage(securityProperties.getSignInPage()) // 登录页面路径
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) // 登录访问后台认证地址
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
        );
    }
}

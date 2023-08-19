package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.rememberme.FlySecurityRememberMeProperties;
import com.github.itdachen.framework.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.github.itdachen.framework.security.validate.code.filter.ValidateCodeFilter;
import com.github.itdachen.framework.security.websecurity.IFlySecurityLogout;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Description: 退出登录
 * Created by 王大宸 on 2023-07-09 13:07
 * Created with IntelliJ IDEA.
 */
@Component
public class FlySecurityLogout implements IFlySecurityLogout {

    private final FlySecurityProperties securityProperties;
    /* 退出处理 */
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final FlySecurityRememberMeProperties rememberMeProperties;

    public FlySecurityLogout(FlySecurityProperties securityProperties,
                             LogoutSuccessHandler logoutSuccessHandler,
                             FlySecurityRememberMeProperties rememberMeProperties) {
        this.securityProperties = securityProperties;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.rememberMeProperties = rememberMeProperties;
    }

    @Override
    public void logout(HttpSecurity http) throws Exception {
        /* 退出登录 */
        http.logout(logout -> logout
                        // 退出登录访问地址
                        .logoutUrl(securityProperties.getLogout())
                        // 删除浏览器里面 cookie 里面的认证信息
                        .deleteCookies("JSESSIONID", "SESSION",
                                rememberMeProperties.getCookieName()
                        )
                        // Handler 和 url 是互斥的,只能配置一个, 如果配置了 Handler 就会交给 Handler 来处理
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                // .logoutSuccessUrl(securityProperties.getSignInPage())   // 退出成功之后跳转地址
        );
    }
}

package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.autoconfigure.security.properties.rememberme.FlySecurityRememberMeProperties;
import com.github.itdachen.framework.security.details.AbstractSecurityUserDetailsService;
import com.github.itdachen.framework.security.websecurity.IFlySecurityRememberMe;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

/**
 * Description: 记住我
 * Created by 王大宸 on 2023-07-09 13:11
 * Created with IntelliJ IDEA.
 */
@Component
public class FlySecurityRememberMe implements IFlySecurityRememberMe {

    private final FlySecurityRememberMeProperties rememberMeProperties;
    /* 登录成功处理 */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AbstractSecurityUserDetailsService userDetailsService;
    private final PersistentTokenRepository persistentTokenRepository;

    public FlySecurityRememberMe(FlySecurityRememberMeProperties rememberMeProperties,
                                 AuthenticationSuccessHandler authenticationSuccessHandler,
                                 AbstractSecurityUserDetailsService userDetailsService,
                                 PersistentTokenRepository persistentTokenRepository) {
        this.rememberMeProperties = rememberMeProperties;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userDetailsService = userDetailsService;
        this.persistentTokenRepository = persistentTokenRepository;
    }


    @Override
    public void rememberMe(HttpSecurity http) throws Exception {
        /*
         * 记住我, 原理: 使用 Cookie 存储用户名, 过期时间, 以及一个 Hash
         *  Hash: md5 (用户名 + 过期时间 + 密码 + key), 如果中途改过密码, 则记住我失效
         */
        http.rememberMe(rememberMe -> rememberMe
                .alwaysRemember(rememberMeProperties.getAlwaysRemember())
                .key(rememberMeProperties.getRememberMeKey()) // 记住我加密 key
                .rememberMeCookieName(rememberMeProperties.getRememberMeCookieName()) // cookie 记住我 key,
                .rememberMeCookieDomain(rememberMeProperties.getRememberMeCookieDomain())
                .tokenValiditySeconds(rememberMeProperties.getRememberMeSeconds()) // 过期时间, 单位, 秒
                .tokenRepository(persistentTokenRepository)  // 指定token存储方式
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .userDetailsService(userDetailsService) // 查询用户的接口
        );
    }
}

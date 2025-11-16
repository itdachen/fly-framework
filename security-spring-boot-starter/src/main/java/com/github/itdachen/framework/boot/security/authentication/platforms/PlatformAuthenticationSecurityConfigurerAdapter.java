package com.github.itdachen.framework.boot.security.authentication.platforms;

import com.github.itdachen.framework.boot.security.details.AbstractSecurityUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.PortMapper;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.RequestCache;

/**
 * 第三方平台跳转登录认证处理器
 *
 * @author 剑鸣秋朔
 * @date 2023-12-18 14:35
 */
//@Component("platformAuthenticationSecurityAdapter")
public class PlatformAuthenticationSecurityConfigurerAdapter implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {
    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;
    private LoginUrlAuthenticationEntryPoint authenticationEntryPoint;
    private SavedRequestAwareAuthenticationSuccessHandler defaultSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();


    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AbstractSecurityUserDetailsService userDetailsService;

    public PlatformAuthenticationSecurityConfigurerAdapter(AuthenticationSuccessHandler authenticationSuccessHandler,
                                                           AuthenticationFailureHandler authenticationFailureHandler,
                                                           AbstractSecurityUserDetailsService userDetailsService) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public void init(HttpSecurity http) throws Exception {

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        PortMapper portMapper = http.getSharedObject(PortMapper.class);
        if (portMapper != null) {
            this.authenticationEntryPoint.setPortMapper(portMapper);
        }
        RequestCache requestCache = http.getSharedObject(RequestCache.class);
        if (requestCache != null) {
            this.defaultSuccessHandler.setRequestCache(requestCache);
        }


        // 添加认证提供者
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        /*  设置认证处理器*/
        http.authenticationProvider(new PlatformAuthenticationProvider(userDetailsService));

        // 设置过滤器
        PlatformAuthenticationFilter authenticationFilter = new PlatformAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager);
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);


        if (this.authenticationDetailsSource != null) {
            authenticationFilter.setAuthenticationDetailsSource(this.authenticationDetailsSource);
        }
        SessionAuthenticationStrategy sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
        if (sessionAuthenticationStrategy != null) {
            authenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        }
        RememberMeServices rememberMeServices = http.getSharedObject(RememberMeServices.class);
        if (rememberMeServices != null) {
            authenticationFilter.setRememberMeServices(rememberMeServices);
        }

        SecurityContextConfigurer<HttpSecurity> contextConfigurer = http.getConfigurer(SecurityContextConfigurer.class);
        if (contextConfigurer != null) {
            SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
            if (securityContextRepository == null) {
                securityContextRepository = new DelegatingSecurityContextRepository(new RequestAttributeSecurityContextRepository(), new HttpSessionSecurityContextRepository());
            }
            authenticationFilter.setSecurityContextRepository(securityContextRepository);
        }


        http.addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }


}

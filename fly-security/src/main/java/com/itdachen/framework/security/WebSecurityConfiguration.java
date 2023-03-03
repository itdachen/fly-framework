package com.itdachen.framework.security;

import com.itdachen.framework.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.itdachen.framework.security.constants.SecurityConstants;
import com.itdachen.framework.security.handler.CustomerAccessDeniedExceptionHandler;
import com.itdachen.framework.security.matchers.IFilterMatchers;
import com.itdachen.framework.security.properties.SecurityBrowserProperties;
import com.itdachen.framework.security.rememberme.CustomJdbcPersistentTokenRepository;
import com.itdachen.framework.security.validate.code.ValidateCodeSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.sql.DataSource;

/**
 * Description: 安全认证模块, 安全配置, 勿动
 * Created by 王大宸 on 2023/02/07 10:57
 * Created with IntelliJ IDEA.
 */
// 添加 security 过滤器
@EnableWebSecurity
// 开启方法权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    /* 不需要认证的路径 */
    private final IFilterMatchers filterMatchers;
    /* 安全配置文件 */
    private final SecurityBrowserProperties securityProperties;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    /* 退出处理 */
    private final LogoutSuccessHandler logoutSuccessHandler;
    /* 登录成功处理 */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    /* 登录失败处理 */
    private final AuthenticationFailureHandler authenticationFailureHandler;
    /* session 过期处理 */
    private final SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    /* session 失效处理 */
    private final InvalidSessionStrategy invalidSessionStrategy;
    /* 权限异常配置 */
    private final CustomerAccessDeniedExceptionHandler accessDeniedExceptionHandler;
    /* 短信验证码登录 */
    private final SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    private final ValidateCodeSecurityConfig validateCodeSecurityConfig;

    public WebSecurityConfiguration(IFilterMatchers filterMatchers,
                                    SecurityBrowserProperties securityProperties,
                                    AuthenticationSuccessHandler authenticationSuccessHandler,
                                    AuthenticationFailureHandler authenticationFailureHandler,
                                    DataSource dataSource,
                                    UserDetailsService userDetailsService,
                                    LogoutSuccessHandler logoutSuccessHandler,
                                    SessionInformationExpiredStrategy sessionInformationExpiredStrategy,
                                    InvalidSessionStrategy invalidSessionStrategy,
                                    CustomerAccessDeniedExceptionHandler accessDeniedExceptionHandler,
                                    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig,
                                    ValidateCodeSecurityConfig validateCodeSecurityConfig) {
        this.filterMatchers = filterMatchers;
        this.securityProperties = securityProperties;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.sessionInformationExpiredStrategy = sessionInformationExpiredStrategy;
        this.invalidSessionStrategy = invalidSessionStrategy;
        this.accessDeniedExceptionHandler = accessDeniedExceptionHandler;
        this.smsCodeAuthenticationSecurityConfig = smsCodeAuthenticationSecurityConfig;
        this.validateCodeSecurityConfig = validateCodeSecurityConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* 表单登录 */
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        //   applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig);
        http.apply(smsCodeAuthenticationSecurityConfig);

        http.authorizeHttpRequests()
                .antMatchers(filterMatchers.matchers())
                .permitAll()
                .anyRequest()
                .authenticated();

        /* 记住我 */
        http.rememberMe().tokenRepository(persistentTokenRepository()) // session 持久化类型
                .tokenValiditySeconds(securityProperties.getRememberMe().getRememberMeSeconds())  // 过期时间
                .userDetailsService(userDetailsService);  // 去做登录

        /* session 安全配置 */
        http.sessionManagement()
                /* 会话失效 */
                .invalidSessionStrategy(invalidSessionStrategy)
                /* 限制同一账号最大同时在线 */
                .maximumSessions(securityProperties.getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getSession().getMaxSessionsPreventsLogin())
                // .invalidSessionStrategy(invalidSessionStrategy)
                /* 会话过期后的配置 */
                .expiredSessionStrategy(sessionInformationExpiredStrategy);

        /* 退出登录 */
        http.logout()
                // Handler 和 Url 是互斥的,只能配置一个, 如果配置了 Handler 就会交给 Handler 来处理
                .logoutSuccessHandler(logoutSuccessHandler)
                // 删除浏览器里面 cookie 里面的认证信息
                .deleteCookies("JSESSIONID", "SESSION", securityProperties.getRememberMe().getRememberMeCookieName());

        /* 权限异常配置 */
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedExceptionHandler);

        http.csrf().disable()
                .headers().frameOptions().sameOrigin();

    }

    /***
     * 记住我 持久化指定保存 session 的方法
     * 重写 jdbc 持久化, 表名
     * @author 王大宸
     * @date 2023/2/6 0:52
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        CustomJdbcPersistentTokenRepository tokenRepository = new CustomJdbcPersistentTokenRepository();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


}

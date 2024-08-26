package com.github.itdachen.boot.security.config;

import com.github.itdachen.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.rememberme.SecurityRememberMeProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.boot.security.authentication.mobile.MobileAuthenticationSecurityConfigurer;
import com.github.itdachen.boot.security.authentication.platforms.PlatformAuthenticationSecurityConfigurerAdapter;
import com.github.itdachen.boot.security.details.AbstractSecurityUserDetailsService;
import com.github.itdachen.boot.security.handler.AuthenticationLogoutHandler;
import com.github.itdachen.boot.security.matchers.IAuthorizeRequestMatchers;
import com.github.itdachen.boot.security.validate.code.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * Description: 认证适配器
 * Created by 王大宸 on 2023-11-27 19:55
 * Created with IntelliJ IDEA.
 */
public class WebSecurityConfigurerAdapter {

    @Autowired
    @Lazy
    protected ValidateCodeFilter validateCodeFilter;

    @Autowired
    @Lazy
    /* 登录成功处理 */
    protected AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    @Lazy
    /* 登录失败处理 */
    protected AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    @Lazy
    protected FlySecurityProperties flySecurityProperties;

    /* 不需要认证的路径 */
//    @Autowired(required = false)
//    @Lazy
    @Autowired
    @Lazy
    protected IAuthorizeRequestMatchers authorizeRequestMatchers;


    @Autowired
    @Lazy
    protected SecuritySessionProperties sessionProperties;
    @Autowired
    @Lazy
    /* session 过期处理 */
    protected SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    @Lazy
    /* session 失效处理 */
    protected InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    @Lazy
    /* 退出处理 */
    protected LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    @Lazy
    protected SecurityRememberMeProperties rememberMeProperties;

    @Autowired
    @Lazy
    protected AbstractSecurityUserDetailsService userDetailsService;
    @Autowired
    @Lazy
    protected PersistentTokenRepository persistentTokenRepository;
    @Autowired
    @Lazy
    protected SecurityContextRepository securityContextRepository;

    //  @Autowired
    /* 权限异常配置 */
    // protected FlyAccessDeniedExceptionHandler accessDeniedExceptionHandler;

    /* Rbac 模式鉴权 */
//    @Autowired
//    protected RbacRequestAuthorizationManager requestAuthorizationManager;


    /* 自定义登录 */
    @Autowired
    @Lazy
    protected MobileAuthenticationSecurityConfigurer mobileAuthenticationSecurityConfigurer;
    @Autowired
    @Lazy
    protected PlatformAuthenticationSecurityConfigurerAdapter platformAuthenticationSecurityConfigurerAdapter;


    /***
     * 配置
     *
     * @author 王大宸
     * @date 2023/11/27 20:58
     * @param http http
     * @return void
     */
    public void configure(HttpSecurity http) throws Exception {

        /* 表单登录 */
        formLogin(http);

        apply(http);

        /* 鉴权路径处理 */
        authorizeHttpRequests(http);

        /* 退出登录 */
        logout(http);

        /* 记住我 */
        rememberMe(http);

        /* 异常处理 */
        exceptionHandling(http);

        /* session 管理器 */
        sessionManagement(http);

        csrf(http);

        http.securityContext((context) -> context
                .securityContextRepository(securityContextRepository)
        );

    }


    /***
     * 表单登录 ( 账号密码登录/验证码登录 )
     *
     * @author 王大宸
     * @date 2023/11/27 20:05
     * @param http http
     * @return void
     */
    public void formLogin(HttpSecurity http) throws Exception {
        /* 验证码过滤器 */
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);

        /* 短信验证码登录 */
        http.apply(mobileAuthenticationSecurityConfigurer);
        http.apply(platformAuthenticationSecurityConfigurerAdapter);

        /* 表单登录 */
        http.formLogin(login -> login
                .loginPage(flySecurityProperties.getSignInPage()) // 登录页面路径
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) // 登录访问后台认证地址
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
        );
    }

    public void apply(HttpSecurity http) throws Exception {
        /* TODO 集成方动态添加认证方式 */
        httpSecurityApply(http);
    }


    /***
     * authorizeHttpRequests
     *
     * @author 王大宸
     * @date 2023/11/27 20:05
     * @param http http
     * @return void
     */
    public void authorizeHttpRequests(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(authorizeRequestMatchers.requestMatcher())
                        .permitAll()
                        .anyRequest()
                        /* authenticated 与 access 只能使用一个, 自定义鉴权时, 使用 access */
                        .authenticated()

                // 自定义鉴权管理器
                //     .access(requestAuthorizationManager)
        );
    }


    /***
     * 退出登录
     *
     * @author 王大宸
     * @date 2023/11/27 20:05
     * @param http http
     * @return void
     */
    public void logout(HttpSecurity http) throws Exception {
        /* 退出登录 */
        http.logout(logout -> logout
                        .logoutRequestMatcher(
                                new OrRequestMatcher(
                                        new AntPathRequestMatcher(flySecurityProperties.getLogout(), "GET"),
                                        new AntPathRequestMatcher(flySecurityProperties.getLogout(), "POST")
                                )
                        )
                        .addLogoutHandler(new AuthenticationLogoutHandler()) // 自定义退出登录处理
                        // 退出登录访问地址
                        // .logoutUrl(securityProperties.getLogout())
                        // 删除浏览器里面 cookie 里面的认证信息
                        .deleteCookies("JSESSIONID", "SESSION", rememberMeProperties.getCookieName())
                        // Handler 和 url 是互斥的,只能配置一个, 如果配置了 Handler 就会交给 Handler 来处理
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .invalidateHttpSession(false)
                        .clearAuthentication(true)
                // .logoutSuccessUrl(securityProperties.getSignInPage())   // 退出成功之后跳转地址
        );
    }


    /***
     * 记住我
     *
     * @author 王大宸
     * @date 2023/11/27 20:05
     * @param http http
     * @return void
     */
    public void rememberMe(HttpSecurity http) throws Exception {
        /*
         * 记住我, 原理: 使用 Cookie 存储用户名, 过期时间, 以及一个 Hash
         *  Hash: md5 (用户名 + 过期时间 + 密码 + key), 如果中途改过密码, 则记住我失效
         */
        http.rememberMe(rememberMe -> rememberMe
                .alwaysRemember(rememberMeProperties.getAlwaysRemember())
                .key(rememberMeProperties.getKey()) // 记住我加密 key
                .rememberMeCookieName(rememberMeProperties.getCookieName()) // cookie 记住我 key,
                .rememberMeCookieDomain(rememberMeProperties.getCookieDomain())
                .tokenValiditySeconds(rememberMeProperties.getSeconds()) // 过期时间, 单位, 秒
                .tokenRepository(persistentTokenRepository)  // 指定token存储方式
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .userDetailsService(userDetailsService) // 查询用户的接口
        );
    }


    /***
     * 认证异常处理
     *
     * @author 王大宸
     * @date 2023/11/27 20:04
     * @param http http
     * @return void
     */
    public void exceptionHandling(HttpSecurity http) throws Exception {
        /* 权限异常配置 */
//        http.exceptionHandling(handler -> handler
//                .accessDeniedHandler(accessDeniedExceptionHandler)
//        );
    }


    /***
     * session 管理器
     *
     * @author 王大宸
     * @date 2023/11/27 20:03
     * @param http http
     * @return void
     */
    public void sessionManagement(HttpSecurity http) throws Exception {
        /* session 管理 */
        http.sessionManagement(session -> session
                /*
                 * Spring Security下的枚举 SessionCreationPolicy, 管理session的创建策略
                 *   ALWAYS :总是创建HttpSession
                 *   IF_REQUIRED: Spring Security只会在需要时创建一个HttpSession
                 *   NEVER: Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
                 *   STATELESS: Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
                 */
                // .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                /* 会话失效 */
                .invalidSessionStrategy(invalidSessionStrategy)
                /* 限制同一账号最大同时在线 */
                .maximumSessions(sessionProperties.getMaximumSessions())
                .maxSessionsPreventsLogin(sessionProperties.getMaxSessionsPreventsLogin())
                /* 会话过期后的配置 */
                .expiredSessionStrategy(sessionInformationExpiredStrategy));
    }


    public void csrf(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                // .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 跨越配置
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                );
    }

    /***
     * 自定义登录扩展, 引入方重写这个方法即可
     *
     * @author 王大宸
     * @date 2023/11/27 20:13
     * @param http http
     * @return void
     */
    public void httpSecurityApply(HttpSecurity http) {
        // 自定义登录扩展, 继承方重写这个方法
        // 实现方式, 例如: http.apply(validateCodeSecurityConfig);
    }


}

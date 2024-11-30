package com.github.itdachen.boot.security;

import com.github.itdachen.boot.autoconfigure.app.AppInfoProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.third.SecurityThirdProperties;
import com.github.itdachen.boot.security.details.IRefreshUserDetails;
import com.github.itdachen.boot.security.details.RefreshUserDetailsHandler;
import com.github.itdachen.boot.security.interceptor.FlyWebSecurityInterceptor;
import com.github.itdachen.boot.security.matchers.IAuthorizeRequestMatchers;
import com.github.itdachen.boot.security.matchers.impl.AuthorizeRequestMatchersImpl;
import com.github.itdachen.boot.security.rememberme.CustomJdbcPersistentTokenRepository;
import com.github.itdachen.boot.security.third.filter.ThirdPlatformForwardFilter;
import com.github.itdachen.boot.security.third.filter.VerifyTicketAuthorizationCodeFilter;
import com.github.itdachen.boot.security.third.service.IThirdPlatformVerifyTicketTokenService;
import com.github.itdachen.boot.security.third.service.impl.DefaultThirdPlatformVerifyTicketToken;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.sql.DataSource;

/**
 * WebSecurityAutoConfiguration
 *
 * @author 王大宸
 * @date 2023-12-23 0:57
 */
@Configuration
public class FlyWebSecurityAutoConfiguration {


    private final DataSource dataSource;
    private final SecurityContextRepository securityContextRepository;
    private final FlySecurityProperties flySecurityProperties;
    private final SecuritySessionProperties sessionProperties;
    private final Environment env;


    public FlyWebSecurityAutoConfiguration(@Lazy DataSource dataSource,
                                           @Lazy SecurityContextRepository securityContextRepository,
                                           @Lazy FlySecurityProperties flySecurityProperties,
                                           @Lazy SecuritySessionProperties sessionProperties,
                                           @Lazy Environment env) {
        this.flySecurityProperties = flySecurityProperties;
        this.sessionProperties = sessionProperties;
        this.env = env;
        this.securityContextRepository = securityContextRepository;
        this.dataSource = dataSource;
    }


    /***
     * 获取平台信息, 获取用户认证票据接口
     *
     * @author 王大宸
     * @date 2023/11/14 10:31
     * @return cn.edu.hubu.framework.security.third.service.IThirdPlatformVerifyTicketTokenService
     */
    @Bean
    @ConditionalOnMissingBean(IThirdPlatformVerifyTicketTokenService.class)
    public IThirdPlatformVerifyTicketTokenService defaultThirdPlatformVerifyTicketToken() {
        return new DefaultThirdPlatformVerifyTicketToken();
    }


    /***
     * 动态刷新用户信息默认配置
     *
     * @author 王大宸
     * @date 2023/12/19 14:44
     * @return cn.edu.hubu.security.details.IRefreshUserDetails
     */
    @Bean
    @ConditionalOnMissingBean(IRefreshUserDetails.class)
    public IRefreshUserDetails refreshUserDetails() {
        return new RefreshUserDetailsHandler(securityContextRepository);
    }

    /***
     * 添加默认的不拦截接口, 从配置文件中读取出来
     *
     * @author 王大宸
     * @date 2023/12/23 1:26
     * @return com.github.itdachen.boot.security.matchers.IFilterMatchers
     */
    @Bean
    @ConditionalOnMissingBean(IAuthorizeRequestMatchers.class)
    public IAuthorizeRequestMatchers authorizeRequestMatchers() {
        return new AuthorizeRequestMatchersImpl(flySecurityProperties, sessionProperties, env);
    }


    /***
     * 默认密码加密方式
     *
     * @author 王大宸
     * @date 2023/7/9 15:01
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 默认拦截器
     *
     * @author 王大宸
     * @date 2024/5/1 22:38
     * @return org.springframework.web.servlet.HandlerInterceptor
     */
    @Bean
    @ConditionalOnMissingBean(HandlerInterceptor.class)
    public HandlerInterceptor flyWebSecurityInterceptor() {
        return new FlyWebSecurityInterceptor();
    }

    /**
     * 记住我 持久化指定保存 session 的方法
     *
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     * @author 王大宸
     * @date 2022/9/23 16:56
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        /* 原来的 JDBC 记住我持久化 */
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();


        /* 自定义记住我持久化 */
        final CustomJdbcPersistentTokenRepository tokenRepository = new CustomJdbcPersistentTokenRepository();

        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    
    /***
     * 服务转发, 跳转到其他应用服务
     *
     * @author 王大宸
     * @date 2024/12/1 0:03
     * @return com.github.itdachen.boot.security.third.filter.ThirdPlatformForwardFilter
     */
    @Bean
    public ThirdPlatformForwardFilter thirdPlatformForwardFilter() {
        return new ThirdPlatformForwardFilter();
    }

    @Bean
    public VerifyTicketAuthorizationCodeFilter verifyTicketAuthorizationCodeFilter(){
        return  new VerifyTicketAuthorizationCodeFilter();
    }


}

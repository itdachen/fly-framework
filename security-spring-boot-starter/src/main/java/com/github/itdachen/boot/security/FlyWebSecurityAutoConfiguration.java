package com.github.itdachen.boot.security;

import com.github.itdachen.boot.autoconfigure.security.properties.SecurityProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.boot.security.details.IRefreshUserDetails;
import com.github.itdachen.boot.security.details.RefreshUserDetailsHandler;
import com.github.itdachen.boot.security.matchers.IAuthorizeRequestMatchers;
import com.github.itdachen.boot.security.matchers.impl.AuthorizeRequestMatchersImpl;
import com.github.itdachen.boot.security.rememberme.CustomJdbcPersistentTokenRepository;
import com.github.itdachen.boot.security.third.service.IThirdPlatformVerifyTicketTokenService;
import com.github.itdachen.boot.security.third.service.impl.DefaultThirdPlatformVerifyTicketToken;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.SecurityContextRepository;

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
    private final SecurityProperties securityProperties;
    private final SecuritySessionProperties sessionProperties;
    private final Environment env;


    public FlyWebSecurityAutoConfiguration(DataSource dataSource,
                                           SecurityContextRepository securityContextRepository,
                                           SecurityProperties securityProperties,
                                           SecuritySessionProperties sessionProperties,
                                           Environment env) {
        this.securityProperties = securityProperties;
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
        return new AuthorizeRequestMatchersImpl(securityProperties, sessionProperties, env);
    }


    /***
     * 记住我 持久化指定保存 session 的方法
     *
     * @author 王大宸
     * @date 2022/9/23 16:56
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
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


}

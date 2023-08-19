package com.github.itdachen.framework.security.session;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.session.FlySecuritySessionProperties;
import com.github.itdachen.framework.boot.runner.handler.ContextPathHandler;
import com.github.itdachen.framework.security.handler.FlyClientSessionExceptionHandler;
import com.github.itdachen.framework.security.handler.FlyLogoutSuccessHandler;
import com.github.itdachen.framework.security.session.strategy.BrowserExpiredSessionStrategy;
import com.github.itdachen.framework.security.session.strategy.BrowserInvalidSessionStrategy;
import com.github.itdachen.framework.security.utils.AuthorizeHttpRequestsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * Description:
 * Created by 王大宸 on 2021-11-27 11:26
 * Created with IntelliJ IDEA.
 */
@Configuration
public class BrowserSecurityBeanConfig {
    private static final Logger logger = LoggerFactory.getLogger(BrowserSecurityBeanConfig.class);

    private final FlySecurityProperties securityProperties;
    private final FlySecuritySessionProperties sessionProperties;

    public BrowserSecurityBeanConfig(FlySecurityProperties securityProperties,
                                     FlySecuritySessionProperties sessionProperties) {
        this.securityProperties = securityProperties;
        this.sessionProperties=sessionProperties;
    }

    /***
     * 功能说明：无效 session 处理
     * 用户可以通过重写 InvalidSessionStrategy 接口,覆盖这里默认的接口
     * @author 王大宸
     * @date 2021/1/3 22:51
     * @param
     * @return org.springframework.security.web.session.InvalidSessionStrategy
     */
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new BrowserInvalidSessionStrategy(sessionProperties.getSessionInvalidUrl());
    }

    /***
     * 功能说明：session 过期处理
     * 用户可以通过重写 SessionInformationExpiredStrategy 接口,覆盖这里默认的接口
     * @author 王大宸
     * @date 2021/1/3 22:52
     * @param
     * @return org.springframework.security.web.session.SessionInformationExpiredStrategy
     */
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new BrowserExpiredSessionStrategy(sessionProperties.getSessionInvalidUrl());
    }

    /***
     * 退出登录
     * 用户可以通过重写 LogoutSuccessHandler 接口,覆盖这里默认的接口
     *
     * @author 王大宸
     * @date 2021/11/27 11:27
     * @param
     * @return org.springframework.security.web.authentication.logout.LogoutSuccessHandler
     */
    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        String signOutUrl = securityProperties.getSignOutUrl();
        final String contextPath = ContextPathHandler.contextPath();
        signOutUrl = AuthorizeHttpRequestsHandler.anyRequestUriHandler(contextPath, signOutUrl);
        return new FlyLogoutSuccessHandler(signOutUrl);
    }

    /***
     * 匿名用户 session 处理
     *
     * @author 王大宸
     * @date 2021/11/27 11:26
     * @return BrowserClientSessionExceptionHandler
     */
    @Bean
    public FlyClientSessionExceptionHandler clientSessionStrategy() {
        return new FlyClientSessionExceptionHandler(sessionProperties);
    }


}

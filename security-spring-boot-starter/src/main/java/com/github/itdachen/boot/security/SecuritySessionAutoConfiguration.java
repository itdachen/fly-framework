package com.github.itdachen.boot.security;

import com.github.itdachen.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.boot.security.handler.AuthenticationLogoutSuccessHandler;
import com.github.itdachen.boot.security.session.BrowserExpiredSessionStrategy;
import com.github.itdachen.boot.security.session.BrowserInvalidSessionStrategy;
import com.github.itdachen.boot.security.utils.AuthorizeHttpRequestsHandler;
import com.github.itdachen.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.boot.runner.handler.ContextPathHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * Description:
 * Created by 王大宸 on 2021-11-27 11:26
 * Created with IntelliJ IDEA.
 */
@Configuration
public class SecuritySessionAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(SecuritySessionAutoConfiguration.class);

    private final FlySecurityProperties flySecurityProperties;
    private final SecuritySessionProperties sessionProperties;

    public SecuritySessionAutoConfiguration(FlySecurityProperties flySecurityProperties,
                                            SecuritySessionProperties sessionProperties) {
        this.flySecurityProperties = flySecurityProperties;
        this.sessionProperties = sessionProperties;
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
        String signOutUrl = flySecurityProperties.getSignOutUrl();
        final String contextPath = ContextPathHandler.contextPath();
        signOutUrl = AuthorizeHttpRequestsHandler.anyRequestUriHandler(contextPath, signOutUrl);
        return new AuthenticationLogoutSuccessHandler(signOutUrl);
    }

}

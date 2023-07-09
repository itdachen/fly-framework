package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.autoconfigure.security.properties.session.FlySecuritySessionProperties;
import com.github.itdachen.framework.security.websecurity.IFlySecuritySessionManagement;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

/**
 * Description: session 管理器
 * Created by 王大宸 on 2023-07-09 13:17
 * Created with IntelliJ IDEA.
 */
@Component
public class FlySecuritySessionManagement implements IFlySecuritySessionManagement {

    private final FlySecuritySessionProperties sessionProperties;
    /* session 过期处理 */
    private final SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    /* session 失效处理 */
    private final InvalidSessionStrategy invalidSessionStrategy;

    public FlySecuritySessionManagement(FlySecuritySessionProperties sessionProperties,
                                        SessionInformationExpiredStrategy sessionInformationExpiredStrategy,
                                        InvalidSessionStrategy invalidSessionStrategy) {
        this.sessionProperties = sessionProperties;
        this.sessionInformationExpiredStrategy = sessionInformationExpiredStrategy;
        this.invalidSessionStrategy = invalidSessionStrategy;
    }


    @Override
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
}

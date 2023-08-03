package com.github.itdachen.framework.security.matchers.byo;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.framework.autoconfigure.security.properties.session.FlySecuritySessionProperties;
import com.github.itdachen.framework.security.matchers.pass.ISecurityPassMatchers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

/**
 * Description: 系统安全认证方法不需要拦截的
 * * 静态资源文件和登录接口等
 * Created by 王大宸 on 2022-09-23 14:45
 * Created with IntelliJ IDEA.
 */
public class DefaultSecurityPassMatchers implements ISecurityPassMatchers {

    private final FlySecurityProperties securityProperties;
    private final FlySecuritySessionProperties sessionProperties;
    private final Environment env;

    public DefaultSecurityPassMatchers(FlySecurityProperties securityProperties,
                                       FlySecuritySessionProperties sessionProperties,
                                       Environment env) {
        this.securityProperties = securityProperties;
        this.sessionProperties = sessionProperties;
        this.env = env;
    }

    @Override
    public String[] matchers() {
        final String contextPath = contextPath();
        return new String[]{
                //=== 登录 ===//
                contextPath + securityProperties.getSignInPage(),
                contextPath + securityProperties.getSignOutUrl(),
                contextPath + securityProperties.getSignUpUrl(),
                contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                contextPath + SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                contextPath + SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/**",
                contextPath + "/login", contextPath + "/admin/login",
                /* 开放 api 接口 */
                contextPath + "/open/**", contextPath + "/api/open/**",
                /* SpringBootAdmin 系统检测 */
                contextPath + "/actuator", contextPath + "/actuator/**",
                contextPath + "/instances", contextPath + "/instances/**",
                contextPath + "/v2/api-docs/",
                /* 403/404/500等错误页面 */
                contextPath + "/error", contextPath + "/error/**",
                //=== 前端静态资源 (应该做更细致地校验) ===//
                contextPath + "/favicon.ico", contextPath + "/favicon", contextPath + "favicon",
                contextPath + "/assets/**", contextPath + "/static/**", contextPath + "/heartbeat",
                contextPath + "/forget", contextPath + "/upload/**",
                contextPath + "/webjars/**",
                contextPath + "/assets", contextPath + "/static",

                "/login",
                /* 开放 api 接口 */
                "/open/**", "/api/open/**",
                /* SpringBootAdmin 系统检测 */
                "/actuator", "/actuator/**", "/instances", "/instances/**",
                "/v2/api-docs/",
                /* 403/404/500等错误页面 */
                "/error", "/error/**",
                //=== 前端静态资源 (应该做更细致地校验) ===//
                "/favicon.ico", "/favicon", "favicon",
                "/assets/**", "/static/**", "/heartbeat", "/forget", "/upload/**",
                "/webjars/**",
                "/assets", "/static",

                //=== 登录 ===//
                securityProperties.getSignInPage(),
                securityProperties.getSignOutUrl(),
                securityProperties.getSignUpUrl(),
                sessionProperties.getSessionInvalidUrl(),
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/**",
        };
    }

    private String contextPath() {
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            return "";
        }
        return contextPath;
    }

}

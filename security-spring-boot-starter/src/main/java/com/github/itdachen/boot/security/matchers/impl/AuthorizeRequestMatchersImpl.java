package com.github.itdachen.boot.security.matchers.impl;

import com.github.itdachen.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.boot.security.matchers.IAuthorizeRequestMatchers;
import com.github.itdachen.framework.core.utils.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-09-23 17:24
 * Created with IntelliJ IDEA.
 */
public class AuthorizeRequestMatchersImpl implements IAuthorizeRequestMatchers {


    /* 不需要认证的路径 */
    private final SecuritySessionProperties sessionProperties;
    private final FlySecurityProperties flySecurityProperties;
    private final Environment env;

    public AuthorizeRequestMatchersImpl(FlySecurityProperties flySecurityProperties,
                                        SecuritySessionProperties sessionProperties,
                                        Environment env) {
        this.env = env;
        this.flySecurityProperties = flySecurityProperties;
        this.sessionProperties = sessionProperties;
    }

    @Override
    public AntPathRequestMatcher[] requestMatcher() {
        String[] matchers = securityMatchers();
        List<String> arr = flySecurityProperties.getMatchers();
        arr.add(sessionProperties.getSessionInvalidUrl());

        if (StringUtils.isEmpty(arr) || arr.isEmpty()) {
            return new AntPathRequestMatcher[0];
        }
        List<String> list = new ArrayList<>(Arrays.stream(matchers).toList());
        list.addAll(arr);
        List<AntPathRequestMatcher> requestMatchers = new ArrayList<>();
        for (String uri : list) {
            requestMatchers.add(new AntPathRequestMatcher(uri));
        }
        return requestMatchers.toArray(new AntPathRequestMatcher[0]);
    }

    @Override
    public String[] requestMatchers() {
        String[] matchers = securityMatchers();
        List<String> arr = flySecurityProperties.getMatchers();
        if (StringUtils.isEmpty(arr) || arr.isEmpty()) {
            return rejectSame(Arrays.asList(matchers));
        }
        arr.addAll(Arrays.asList(matchers));
        return rejectSame(arr);
    }


    /***
     * 去除重复的
     *
     * @author 王大宸
     * @date 2022/9/25 12:04
     * @param list list
     * @return java.lang.String[]
     */
    private String[] rejectSame(List<String> list) {
        List<String> arr = new ArrayList<>();
        for (String s : list) {
            if (arr.contains(s)) {
                continue;
            }
            arr.add(s);
        }
        return arr.toArray(new String[0]);
    }


    /***
     * 系统自带不拦截路径
     *
     * @author 王大宸
     * @date 2023/12/19 14:50
     * @return java.lang.String[]
     */
    private String[] securityMatchers() {
        final String contextPath = contextPath();
        return new String[]{
                //=== 登录 ===//
                contextPath + flySecurityProperties.getSignInPage(),
                contextPath + flySecurityProperties.getSignOutUrl(),
                contextPath + flySecurityProperties.getSignUpUrl(),
                contextPath + sessionProperties.getSessionInvalidUrl(),
                contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                contextPath + SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                contextPath + SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                contextPath + SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                contextPath + SecurityConstants.VERIFY_TICKET_TOKEN,
                contextPath + SecurityConstants.VERIFY_TICKET_TOKEN + "/*",
                contextPath + SecurityConstants.THIRD_PLATFORM,
                contextPath + "/login",
                /* 开放 api 接口 */
                contextPath + "/open/**", contextPath + "/api/open/**",
                /* SpringBootAdmin 系统检测 */
                contextPath + "/actuator", contextPath + "/actuator/**",
                contextPath + "/instances", contextPath + "/instances/**",
                contextPath + "/v2/api-docs/",
                /* 403/404/500等错误页面 */
                contextPath + "/error", contextPath + "/error/*", contextPath + "/error/**",
                //=== 前端静态资源 (应该做更细致地校验) ===//
                contextPath + "/favicon.ico", contextPath + "/favicon", contextPath + "favicon",
                contextPath + "/assets/**", contextPath + "/static/**", contextPath + "/heartbeat",
                contextPath + "/forget", contextPath + "/upload/**",
                contextPath + "/webjars/**",

                //=== 登录 ===//
                flySecurityProperties.getSignInPage(),
                flySecurityProperties.getSignOutUrl(),
                flySecurityProperties.getSignUpUrl(),
                sessionProperties.getSessionInvalidUrl(),
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                SecurityConstants.VERIFY_TICKET_TOKEN,
                SecurityConstants.VERIFY_TICKET_TOKEN + "/*",
                SecurityConstants.THIRD_PLATFORM,
                "/login",
                /* 开放 api 接口 */
                "/open/**", "/api/open/**",
                /* SpringBootAdmin 系统检测 */
                "/actuator", "/actuator/**", "/instances", "/instances/**", "/v2/api-docs/",
                /* 403/404/500等错误页面 */
                "/error", "/error/**",
                //=== 前端静态资源 (应该做更细致地校验) ===//
                "/favicon.ico", "/favicon", "favicon",
                "/assets/**", "/static/**", "/heartbeat", "/forget", "/upload/**", "/webjars/**",
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

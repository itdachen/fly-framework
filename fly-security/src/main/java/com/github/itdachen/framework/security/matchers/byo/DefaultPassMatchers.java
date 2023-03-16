package com.github.itdachen.framework.security.matchers.byo;

import com.github.itdachen.framework.security.matchers.pass.IPassMatchers;
import com.github.itdachen.framework.security.properties.SecurityBrowserProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 配置的不拦截路径, 从配置文件中读取
 * Created by 王大宸 on 2022-09-23 14:44
 * Created with IntelliJ IDEA.
 */
public class DefaultPassMatchers implements IPassMatchers {
    private final SecurityBrowserProperties securityProperties;

    public DefaultPassMatchers(SecurityBrowserProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public List<String> matchers() {
        List<String> matchers = securityProperties.getMatchers();
        if (null == matchers) {
            matchers = new ArrayList<>();
        }
        matchers.add(securityProperties.getSignInPage());
        matchers.add(securityProperties.getSignUpUrl());
        matchers.add(securityProperties.getSession().getSessionInvalidUrl());
        return matchers;
    }


}

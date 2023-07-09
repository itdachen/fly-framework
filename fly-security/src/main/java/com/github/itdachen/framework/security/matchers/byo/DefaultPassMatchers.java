package com.github.itdachen.framework.security.matchers.byo;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.properties.session.FlySecuritySessionProperties;
import com.github.itdachen.framework.security.matchers.pass.IPassMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 配置的不拦截路径, 从配置文件中读取
 * Created by 王大宸 on 2022-09-23 14:44
 * Created with IntelliJ IDEA.
 */
public class DefaultPassMatchers implements IPassMatchers {
    private final FlySecurityProperties securityProperties;
    private final FlySecuritySessionProperties sessionProperties;

    public DefaultPassMatchers(FlySecurityProperties securityProperties,
                               FlySecuritySessionProperties sessionProperties) {
        this.securityProperties = securityProperties;
        this.sessionProperties=sessionProperties;
    }

    @Override
    public List<String> matchers() {
        List<String> matchers = securityProperties.getMatchers();
        if (null == matchers) {
            matchers = new ArrayList<>();
        }
        matchers.add(securityProperties.getSignInPage());
        matchers.add(securityProperties.getSignUpUrl());
        matchers.add(sessionProperties.getSessionInvalidUrl());
        return matchers;
    }


}

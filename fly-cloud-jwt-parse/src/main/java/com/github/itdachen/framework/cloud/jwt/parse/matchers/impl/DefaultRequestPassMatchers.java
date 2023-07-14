package com.github.itdachen.framework.cloud.jwt.parse.matchers.impl;

import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyJwtAppClientProperties;
import com.github.itdachen.framework.cloud.jwt.parse.matchers.IRequestPassMatchers;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 22:14
 * Created with IntelliJ IDEA.
 */
public class DefaultRequestPassMatchers implements IRequestPassMatchers {

    private final FlyJwtAppClientProperties jwtsProperties;

    public DefaultRequestPassMatchers(FlyJwtAppClientProperties jwtsProperties) {
        this.jwtsProperties = jwtsProperties;
    }

    @Override
    public String[] passMatchers() {
        List<String> matchers = jwtsProperties.getMatchers();
        if (null == matchers) {
            return new String[0];
        }
        return matchers.toArray(new String[0]);
    }

}

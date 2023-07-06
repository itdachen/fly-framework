package com.github.itdachen.framework.autoconfigure.properties.gateway.routes;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 网关忽略的路径
 * Created by 王大宸 on 2023-07-06 16:12
 * Created with IntelliJ IDEA.
 */
public class GatewayIgnoreProperties {

    /**
     *
     */
    private List<String> matchers = new ArrayList<>();

    public List<String> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<String> matchers) {
        this.matchers = matchers;
    }
}

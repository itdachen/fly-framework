package com.github.itdachen.framework.autoconfigure.properties.gateway;

import com.github.itdachen.framework.autoconfigure.properties.gateway.routes.GatewayIgnoreProperties;
import com.github.itdachen.framework.autoconfigure.properties.gateway.routes.GatewayRoutesProperties;

/**
 * Description: 微服务网关
 * Created by 王大宸 on 2023-07-06 15:52
 * Created with IntelliJ IDEA.
 */
public class FlyGatewayProperties {

    /**
     * 读取网关路由
     */
    private GatewayRoutesProperties routes = new GatewayRoutesProperties();

    /**
     * 网关不拦截的路劲
     */
    private GatewayIgnoreProperties ignore = new GatewayIgnoreProperties();

    public GatewayRoutesProperties getRoutes() {
        return routes;
    }

    public void setRoutes(GatewayRoutesProperties routes) {
        this.routes = routes;
    }

    public GatewayIgnoreProperties getIgnore() {
        return ignore;
    }

    public void setIgnore(GatewayIgnoreProperties ignore) {
        this.ignore = ignore;
    }
}

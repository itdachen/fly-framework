package com.github.itdachen.framework.boot.autoconfigure.cloud.gateway;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.framework.boot.autoconfigure.cloud.gateway.ignore.GatewayIgnoreProperties;
import com.github.itdachen.framework.boot.autoconfigure.cloud.gateway.routes.GatewayRoutesProperties;

/**
 * CloudGatewayHelperProperties
 *
 * @author 王大宸
 * @date 2024-06-19 16:34
 */
public class CloudGatewayHelperProperties {

    /***
    * 忽略不拦截路径
    *
    * @author 王大宸
    * @date 2024/6/19 16:36
    * @return com.github.itdachen.boot.autoconfigure.cloud.gateway.ignore.GatewayIgnoreProperties
    */
    public GatewayIgnoreProperties ignore() {
        return AppContextHelper.getBean(GatewayIgnoreProperties.class);
    }

    /***
    * 动态路由
    *
    * @author 王大宸
    * @date 2024/6/19 16:36
    * @return com.github.itdachen.boot.autoconfigure.cloud.gateway.routes.GatewayRoutesProperties
    */
    public GatewayRoutesProperties routes() {
        return AppContextHelper.getBean(GatewayRoutesProperties.class);
    }

}

package com.github.itdachen.framework.boot.autoconfigure.cloud;

import com.github.itdachen.framework.boot.autoconfigure.cloud.gateway.CloudGatewayHelperProperties;
import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties.CloudAuthHelperProperties;

/**
 * CloudHelperProperties
 *
 * @author 王大宸
 * @date 2024-06-19 16:19
 */
public class CloudHelperProperties {

    /***
     * 微服务模块安全认证
     *
     * @author 王大宸
     * @date 2024/6/19 16:31
     * @return com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudAuthHelperProperties
     */
    public CloudAuthHelperProperties auth() {
        return new CloudAuthHelperProperties();
    }

    /***
    * 网关配置
    *
    * @author 王大宸
    * @date 2024/6/19 16:35
    * @return com.github.itdachen.boot.autoconfigure.cloud.gateway.CloudGatewayHelperProperties
    */
    public CloudGatewayHelperProperties gateway() {
        return new CloudGatewayHelperProperties();
    }


}

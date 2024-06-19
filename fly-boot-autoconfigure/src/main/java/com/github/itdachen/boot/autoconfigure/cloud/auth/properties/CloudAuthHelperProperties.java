package com.github.itdachen.boot.autoconfigure.cloud.auth.properties;

import com.github.itdachen.boot.autoconfigure.AppContextHelper;

/**
 * CloudAuthHelperProperties
 *
 * @author 王大宸
 * @date 2024-06-19 16:31
 */
public class CloudAuthHelperProperties {

    /***
    * token 应用信息
    *
    * @author 王大宸
    * @date 2024/6/19 16:33
    * @return com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudAppClientProperties
    */
    public CloudAppClientProperties app() {
        return AppContextHelper.getBean(CloudAppClientProperties.class);
    }

    /***
    * token 配置信息
    *
    * @author 王大宸
    * @date 2024/6/19 16:33
    * @return com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties
    */
    public CloudTokenProperties token() {
        return AppContextHelper.getBean(CloudTokenProperties.class);
    }

}

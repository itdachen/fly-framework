package com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;

/**
 * CloudAuthHelperProperties
 *
 * @author 剑鸣秋朔
 * @date 2024-06-19 16:31
 */
public class CloudAuthHelperProperties {

    /***
    * token 应用信息
    *
    * @author 剑鸣秋朔
    * @date 2024/6/19 16:33
    * @return com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudAppClientProperties
    */
    public CloudAppClientProperties app() {
        return AppContextHelper.getBean(CloudAppClientProperties.class);
    }

    /***
    * token 配置信息
    *
    * @author 剑鸣秋朔
    * @date 2024/6/19 16:33
    * @return com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties
    */
    public CloudTokenProperties token() {
        return AppContextHelper.getBean(CloudTokenProperties.class);
    }

}

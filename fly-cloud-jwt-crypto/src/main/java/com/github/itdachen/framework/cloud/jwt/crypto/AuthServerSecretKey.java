package com.github.itdachen.framework.cloud.jwt.crypto;

import org.springframework.context.annotation.Configuration;

/**
 * Description: 服务端秘钥存储
 * Created by 王大宸 on 2023-07-15 23:06
 * Created with IntelliJ IDEA.
 */
// @Configuration
@Deprecated
public class AuthServerSecretKey {

    private String servicePriKey;

    private String servicePubKey;

    public String getServicePriKey() {
        return servicePriKey;
    }

    public void setServicePriKey(String servicePriKey) {
        this.servicePriKey = servicePriKey;
    }

    public String getServicePubKey() {
        return servicePubKey;
    }

    public void setServicePubKey(String servicePubKey) {
        this.servicePubKey = servicePubKey;
    }
}

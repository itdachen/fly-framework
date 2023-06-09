package com.github.itdachen.framework.jwt.config;

import org.springframework.context.annotation.Configuration;

/**
 * Description:  配置 密钥/公钥
 * Created by 王大宸 on 2023/04/25 21:42
 * Created with IntelliJ IDEA.
 */
@Configuration
public class SecretKeyConfiguration {

    private String userPubKey;

    private String userPriKey;

    private String servicePriKey;

    private String servicePubKey;


    public String getUserPubKey() {
        return userPubKey;
    }

    public void setUserPubKey(String userPubKey) {
        this.userPubKey = userPubKey;
    }

    public String getUserPriKey() {
        return userPriKey;
    }

    public void setUserPriKey(String userPriKey) {
        this.userPriKey = userPriKey;
    }

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

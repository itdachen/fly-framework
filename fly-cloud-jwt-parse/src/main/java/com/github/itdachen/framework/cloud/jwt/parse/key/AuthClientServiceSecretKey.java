package com.github.itdachen.framework.cloud.jwt.parse.key;

/**
 * Description: 客户端公钥
 * Created by 王大宸 on 2023/05/01 13:57
 * Created with IntelliJ IDEA.
 */
public class AuthClientServiceSecretKey {

    /**
     * 服务公钥
     */
    private String servicePubKey;


    public String getServicePubKey() {
        return servicePubKey;
    }

    public void setServicePubKey(String servicePubKey) {
        this.servicePubKey = servicePubKey;
    }

}

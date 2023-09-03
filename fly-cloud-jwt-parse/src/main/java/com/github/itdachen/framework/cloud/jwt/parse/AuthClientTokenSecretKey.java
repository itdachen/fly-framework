package com.github.itdachen.framework.cloud.jwt.parse;

import org.springframework.context.annotation.Configuration;

/**
 * Description: 认证客户端公钥
 * Created by 王大宸 on 2023/05/01 13:49
 * Created with IntelliJ IDEA.
 */
@Configuration
public class AuthClientTokenSecretKey {

    /**
     * 服务公钥
     */
    private String servicePubKey;

    /**
     * token 公钥
     */
    private String tokenPublicKey;

    public String getServicePubKey() {
        return servicePubKey;
    }

    public void setServicePubKey(String servicePubKey) {
        this.servicePubKey = servicePubKey;
    }

    public String getTokenPublicKey() {
        return tokenPublicKey;
    }

    public void setTokenPublicKey(String tokenPublicKey) {
        this.tokenPublicKey = tokenPublicKey;
    }

}

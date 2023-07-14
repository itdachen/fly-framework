package com.github.itdachen.framework.cloud.jwt.parse.key;

/**
 * Description: 认证客户端公钥
 * Created by 王大宸 on 2023/05/01 13:49
 * Created with IntelliJ IDEA.
 */
public class AuthClientTokenSecretKey {

    /**
     * token 公钥
     */
    private String tokenPublicKey;

    public String getTokenPublicKey() {
        return tokenPublicKey;
    }

    public void setTokenPublicKey(String tokenPublicKey) {
        this.tokenPublicKey = tokenPublicKey;
    }

}

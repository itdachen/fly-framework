package com.github.itdachen.boot.jwt.crypto;

import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;

/**
 * Description: token 公钥/私钥 存储
 * Created by 王大宸 on 2023-07-15 23:05
 * Created with IntelliJ IDEA.
 */
@Configuration
public class AuthTokenSecretKey {

    private String userPubKey;

    private String userPriKey;

    private PrivateKey privateKey;

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

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}

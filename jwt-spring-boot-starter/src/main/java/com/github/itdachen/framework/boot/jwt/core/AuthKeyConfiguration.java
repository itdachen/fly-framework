package com.github.itdachen.framework.boot.jwt.core;

import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-22 13:34
 * Created with IntelliJ IDEA.
 */
@Configuration
public class AuthKeyConfiguration {

    private byte[] userPubKey;

    private byte[] userPriKey;

    public byte[] getUserPubKey() {
        return userPubKey;
    }

    public void setUserPubKey(byte[] userPubKey) {
        this.userPubKey = userPubKey;
    }

    public byte[] getUserPriKey() {
        return userPriKey;
    }

    public void setUserPriKey(byte[] userPriKey) {
        this.userPriKey = userPriKey;
    }
}

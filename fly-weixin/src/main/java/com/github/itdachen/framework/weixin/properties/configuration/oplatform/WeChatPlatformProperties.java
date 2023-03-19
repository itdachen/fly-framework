package com.github.itdachen.framework.weixin.properties.configuration.oplatform;

import java.io.Serializable;

/**
 * Description: 微信开放平台
 * Created by 王大宸 on 2023/03/19 13:29
 * Created with IntelliJ IDEA.
 */
public class WeChatPlatformProperties implements Serializable {
    private static final long serialVersionUID = 3413550161118989335L;

    private String appId;

    private String appSecret;

    /**
     * 消息校验Token
     */
    private String token;

    /**
     * 消息加解密Key
     */
    private String key;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

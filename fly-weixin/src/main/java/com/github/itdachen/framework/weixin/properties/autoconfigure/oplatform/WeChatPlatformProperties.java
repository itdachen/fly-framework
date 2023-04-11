package com.github.itdachen.framework.weixin.properties.autoconfigure.oplatform;

import java.io.Serializable;

/**
 * Description:
 * Created by 王大宸 on 2023/04/11 21:42
 * Created with IntelliJ IDEA.
 */
public class WeChatPlatformProperties implements Serializable {
    private static final long serialVersionUID = 3413550161118989335L;

    /**
     * 是否加密
     */
    private Boolean crypto = false;

    /**
     * 平台 appId
     */
    private String appId;

    /**
     * 平台秘钥
     */
    private String appSecret;

    /**
     * 消息校验Token
     */
    private String token;

    /**
     * 消息加解密Key
     */
    private String key;

    public Boolean getCrypto() {
        return crypto;
    }

    public void setCrypto(Boolean crypto) {
        this.crypto = crypto;
    }

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

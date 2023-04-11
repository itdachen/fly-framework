package com.github.itdachen.framework.weixin.properties.autoconfigure.applet;

/**
 * Description:
 * Created by 王大宸 on 2023/04/11 23:56
 * Created with IntelliJ IDEA.
 */
public class WeChatAppletProperties {

    /**
     * 是否加密
     */
    private Boolean crypto = false;

    /**
     * appId
     */
    private String appId;

    /**
     * app 秘钥
     */
    private String appSecret;

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
}

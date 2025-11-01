package com.github.itdachen.framework.boot.autoconfigure.wechat.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信小程序配置
 *
 * @author 王大宸
 * @date 2024-11-21 11:14
 */
@ConfigurationProperties(prefix = "fly.wechat.applet")
public class WeChatAppletProperties {


    /**
     * 应用ID
     */
    private String appId = "";

    /**
     * 应用秘钥
     */
    private String appSecret = "";


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

package com.github.itdachen.framework.weixin.properties.configuration;

import com.github.itdachen.framework.weixin.properties.configuration.applet.AppletProperties;
import com.github.itdachen.framework.weixin.properties.configuration.oplatform.WeChatPlatformProperties;
import com.github.itdachen.framework.weixin.properties.configuration.pay.WeChatPayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 微信配置
 * Created by 王大宸 on 2023/03/19 13:17
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "wechat")
public class WeChatCloudProperties {

    /**
     * 是否加密
     */
    private Boolean crypto = false;

    /**
     * 微信小程序
     */
    private AppletProperties applet = new AppletProperties();

    /**
     * 微信支付
     */
    private WeChatPayProperties pay = new WeChatPayProperties();

    /**
     * 微信开放平台
     */
    private WeChatPlatformProperties platform = new WeChatPlatformProperties();

    public Boolean getCrypto() {
        return crypto;
    }

    public void setCrypto(Boolean crypto) {
        this.crypto = crypto;
    }

    public AppletProperties getApplet() {
        return applet;
    }

    public void setApplet(AppletProperties applet) {
        this.applet = applet;
    }

    public WeChatPayProperties getPay() {
        return pay;
    }

    public void setPay(WeChatPayProperties pay) {
        this.pay = pay;
    }

    public WeChatPlatformProperties getPlatform() {
        return platform;
    }

    public void setPlatform(WeChatPlatformProperties platform) {
        this.platform = platform;
    }



}

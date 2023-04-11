package com.github.itdachen.framework.weixin.properties.autoconfigure;

import com.github.itdachen.framework.weixin.properties.autoconfigure.applet.WeChatAppletProperties;
import com.github.itdachen.framework.weixin.properties.autoconfigure.oplatform.WeChatPlatformProperties;
import com.github.itdachen.framework.weixin.properties.autoconfigure.pay.WeChatPayConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 微信配置
 * Created by 王大宸 on 2023/04/11 23:48
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "weixin")
public class WeChatProperties {

    /**
     * 微信小程序
     */
    private WeChatAppletProperties applet = new WeChatAppletProperties();

    /**
     * 微信支付
     */
    private WeChatPayConfigurationProperties pay = new WeChatPayConfigurationProperties();

    /**
     * 第三方平台
     */
    private WeChatPlatformProperties oplatform = new WeChatPlatformProperties();


    public WeChatAppletProperties getApplet() {
        return applet;
    }

    public void setApplet(WeChatAppletProperties applet) {
        this.applet = applet;
    }

    public WeChatPayConfigurationProperties getPay() {
        return pay;
    }

    public void setPay(WeChatPayConfigurationProperties pay) {
        this.pay = pay;
    }

    public WeChatPlatformProperties getOplatform() {
        return oplatform;
    }

    public void setOplatform(WeChatPlatformProperties oplatform) {
        this.oplatform = oplatform;
    }
}

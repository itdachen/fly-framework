package com.github.itdachen.framework.weixin.properties.configuration.applet;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Description: 微信小程序配置
 * Created by 王大宸 on 2023/03/19 13:32
 * Created with IntelliJ IDEA.
 */
public class AppletProperties {

    private String appId = "wxd749fa110239b97118";

    private String appSecret = "nGSOtmjjRxdrJGwm4w==";

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("appId", getAppId())
                .append("appSecret", getAppSecret())
                .toString();
    }

}

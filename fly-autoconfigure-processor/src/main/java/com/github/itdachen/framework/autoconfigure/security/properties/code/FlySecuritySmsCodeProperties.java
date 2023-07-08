package com.github.itdachen.framework.autoconfigure.security.properties.code;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 短信验证码
 * Created by 王大宸 on 2022-09-23 9:57
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.security.code.sms")
public class FlySecuritySmsCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 验证码有效时间
     */
    private int expireIn = 60;

    /**
     * 需要拦截的地址
     */
    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

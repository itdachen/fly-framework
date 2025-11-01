package com.github.itdachen.framework.boot.autoconfigure.security.properties.rememberme;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 记住我
 * Created by 王大宸 on 2022-09-29 16:25
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.security.rememberme")
public class SecurityRememberMeProperties {

    /**
     * '记住我' 加密 key
     */
    private String key = "remember-me";

    /**
     * 记住我 cookie key, 可以设置成需要配置跨域的域名
     */
    private String cookieName = "remember-me";

    /**
     * '记住我'功能的有效时间(秒数)，默认7天
     */
    private int seconds = 604800;

    /**
     * 记住我需要配置的域名
     */
    private String cookieDomain;

    /**
     * 需要配置跨域的域名
     */
    private String corsCookieDomain;

    /**
     * 是否永远记住(如果是永远记住, 登录失效后, 会重新自动登录)
     */
    private Boolean alwaysRemember = Boolean.FALSE;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public String getCorsCookieDomain() {
        return corsCookieDomain;
    }

    public void setCorsCookieDomain(String corsCookieDomain) {
        this.corsCookieDomain = corsCookieDomain;
    }

    public Boolean getAlwaysRemember() {
        return alwaysRemember;
    }

    public void setAlwaysRemember(Boolean alwaysRemember) {
        this.alwaysRemember = alwaysRemember;
    }
}

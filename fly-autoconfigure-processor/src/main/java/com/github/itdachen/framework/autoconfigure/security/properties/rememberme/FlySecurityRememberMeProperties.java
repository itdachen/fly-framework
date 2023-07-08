package com.github.itdachen.framework.autoconfigure.security.properties.rememberme;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 记住我
 * Created by 王大宸 on 2022-09-29 16:25
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.security.rememberme")
public class FlySecurityRememberMeProperties {

    /* '记住我' 加密 key */
    private String rememberMeKey = "some_secret";

    /* 记住我 cookie key, 可以设置成需要配置跨域的域名 */
    private String rememberMeCookieName = "remember-me";

    /* '记住我'功能的有效时间(秒数)，默认7天 */
    private int rememberMeSeconds = 604800;

    /* 记住我需要配置的域名 */
    private String rememberMeCookieDomain;

    /* 需要配置跨域的域名 */
    private String corsCookieDomain;

    /* 是否永远记住 */
    private Boolean alwaysRemember = Boolean.TRUE;


    public String getRememberMeKey() {
        return rememberMeKey;
    }

    public void setRememberMeKey(String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    public String getRememberMeCookieName() {
        return rememberMeCookieName;
    }

    public void setRememberMeCookieName(String rememberMeCookieName) {
        this.rememberMeCookieName = rememberMeCookieName;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getRememberMeCookieDomain() {
        return rememberMeCookieDomain;
    }

    public void setRememberMeCookieDomain(String rememberMeCookieDomain) {
        this.rememberMeCookieDomain = rememberMeCookieDomain;
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

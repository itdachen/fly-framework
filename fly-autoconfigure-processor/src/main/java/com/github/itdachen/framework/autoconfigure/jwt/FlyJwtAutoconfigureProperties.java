package com.github.itdachen.framework.autoconfigure.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 单体项目 jwt token 配置
 * Created by 王大宸 on 2023-07-22 13:31
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.auth.jwt")
public class FlyJwtAutoconfigureProperties {

    /**
     * 过期时间, 单位: 秒,
     */
    private int expire = 14400;

    /**
     * 认证秘钥
     */
    private String authSecret = "xx1WET12^%3^(WE45";

    /**
     * token 请求头
     */
    private String tokenHeader = "Authorization";

    /**
     * 签发者
     */
    private String issuer = "com.github.itdachen";

    /**
     * token 公钥 / 私钥 缓存方式
     */
    private KeyCacheType keyCache = KeyCacheType.MEMORY;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getAuthSecret() {
        return authSecret;
    }

    public void setAuthSecret(String authSecret) {
        this.authSecret = authSecret;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public KeyCacheType getKeyCache() {
        return keyCache;
    }

    public void setKeyCache(KeyCacheType keyCache) {
        this.keyCache = keyCache;
    }
}

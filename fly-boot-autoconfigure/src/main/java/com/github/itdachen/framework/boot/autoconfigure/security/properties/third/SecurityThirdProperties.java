package com.github.itdachen.framework.boot.autoconfigure.security.properties.third;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 第三方平台跳转认证信息
 *
 * @author 王大宸
 * @date 2023-11-14 17:22
 */
@ConfigurationProperties(prefix = "fly.security.third")
public class SecurityThirdProperties {

    /**
     * 过期时间, 分钟
     */
    private long expTime = 5;


    public long getExpTime() {
        return expTime;
    }

    public void setExpTime(long expTime) {
        this.expTime = expTime;
    }
}

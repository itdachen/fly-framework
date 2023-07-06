package com.github.itdachen.framework.autoconfigure.properties;

import com.github.itdachen.framework.autoconfigure.properties.gateway.FlyGatewayProperties;
import com.github.itdachen.framework.autoconfigure.properties.jjwt.FlyJJwtProperties;
import com.github.itdachen.framework.autoconfigure.properties.oss.FlyOssProperties;
import com.github.itdachen.framework.autoconfigure.properties.security.FlySecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 配置
 * Created by 王大宸 on 2023-07-06 15:20
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly")
public class FlyAutoconfigureProperties {

    /**
     * 文件上传
     */
    private FlyOssProperties oss = new FlyOssProperties();

    /**
     * jwt 配置
     */
    private FlyJJwtProperties jjwt = new FlyJJwtProperties();

    /**
     * SpringSecurity 配置
     */
    private FlySecurityProperties security = new FlySecurityProperties();

    /**
     * 网关配置
     */
    private FlyGatewayProperties gateway = new FlyGatewayProperties();

    public FlyOssProperties getOss() {
        return oss;
    }

    public void setOss(FlyOssProperties oss) {
        this.oss = oss;
    }

    public FlyJJwtProperties getJjwt() {
        return jjwt;
    }

    public void setJjwt(FlyJJwtProperties jjwt) {
        this.jjwt = jjwt;
    }

    public FlySecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(FlySecurityProperties security) {
        this.security = security;
    }

    public FlyGatewayProperties getGateway() {
        return gateway;
    }

    public void setGateway(FlyGatewayProperties gateway) {
        this.gateway = gateway;
    }
}

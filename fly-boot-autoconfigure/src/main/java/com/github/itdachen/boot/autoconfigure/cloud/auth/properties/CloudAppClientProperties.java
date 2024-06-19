package com.github.itdachen.boot.autoconfigure.cloud.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Description: 微服务客户端认证信息配置
 * Created by 王大宸 on 2023-07-15 0:57
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.cloud.auth.app")
public class CloudAppClientProperties {

    /**
     * 认证中心
     */
    private String serviceId;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用秘钥
     */
    private String appSecret;

    /**
     * 不拦截路径
     */
    private List<String> matchers;


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

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

    public List<String> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<String> matchers) {
        this.matchers = matchers;
    }
}

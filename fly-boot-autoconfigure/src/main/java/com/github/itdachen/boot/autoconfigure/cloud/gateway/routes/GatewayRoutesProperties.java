package com.github.itdachen.boot.autoconfigure.cloud.gateway.routes;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 基于 Nacos 动态网关路由配置
 * Created by 王大宸 on 2023-07-06 15:53
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.cloud.gateway.routes")
public class GatewayRoutesProperties {

    /**
     * 超时时间
     */
    private long timeout = 30000;

    /**
     * nacos 账号
     */
    private String username = "nacos";

    /**
     * nacos 密码
     */
    private String password = "nacos";

    /**
     * Nacos 地址
     */
    private String serverAddr = "127.0.0.1:8848";

    /**
     * 命名空间
     */
    private String namespace = "b6783835-a27f-4ec3-b9ee-05bfa917e812";

    /**
     * 配置中心 dataId
     */
    private String dataId = "GATEWAY_ROUTERS";

    /**
     * 分组
     */
    private String group = "GATEWAY_ROUTERS";

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}

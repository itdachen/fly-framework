package com.github.itdachen.framework.cloud.gateway.routes.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * Description: Nacos 动态路由配置, 网关命令空间, 不要跟服务注册发现的命令空间一样
 * Created by 王大宸 on 2023/02/16 15:42
 * Created with IntelliJ IDEA.
 */
@Configuration
@ConfigurationProperties(prefix = "gateway.routes")
public class GatewayRoutesProperties implements Serializable {
    private static final long serialVersionUID = 4765004771227991443L;

    private long timeout = 30000;

    /**
     * Nacos 地址
     */
    private String serverAddr = "127.0.0.1:8848";

    /**
     * 命名空间
     */
    private String namespace = "b6783835-a27f-4ec3-b9ee-05bfa917e812";

    /**
     * dataId
     */
    private String dataId = "GATE_WAY_ROUTERS";

    /**
     * 分组
     */
    private String group = "GATE_WAY_ROUTERS";

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
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

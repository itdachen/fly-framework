package com.itdachen.framework.security.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * Description: 客户端配置
 * Created by 王大宸 on 2022-10-16 13:29
 * Created with IntelliJ IDEA.
 */
@Configuration
@ConfigurationProperties(prefix = "client")
public class WebClientConfig implements Serializable {
    private static final long serialVersionUID = 8063601184895567058L;

    /**
     * 客户端id
     */
    private String id = "web_app";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 版权所有
     */
    private String copyright = "湖北大学软件工程研究所";

    /**
     * 项目名称
     */
    private String name = "汇聚整理平台";

    /**
     * 备注
     */
    private String remarks = "";

    /**
     * 版权年份
     */
    private String copyrightYear = "2023";

    /**
     * 发行人
     */
    private String issuer = "湖北大学软件工程研究所";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "WebClientConfig{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", copyright='" + copyright + '\'' +
                ", name='" + name + '\'' +
                ", remarks='" + remarks + '\'' +
                ", copyrightYear='" + copyrightYear + '\'' +
                ", issuer='" + issuer + '\'' +
                '}';
    }
}

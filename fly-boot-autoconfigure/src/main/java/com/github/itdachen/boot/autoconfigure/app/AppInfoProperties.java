package com.github.itdachen.boot.autoconfigure.app;

import com.github.itdachen.boot.autoconfigure.app.enums.BackstageTemplateEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 应用配置
 * Created by 王大宸 on 2024-04-03 22:10
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.app")
public class AppInfoProperties {

    /**
     * 应用ID
     */
    private String appId = "web_app";

    /**
     * 应用秘钥
     */
    private String appSecret = "web_app";

    /**
     * 项目名称
     */
    private String appName = "FLY-NEXT-ADMIN";

    /**
     * 上下文(不用配置)
     */
    @Value("${server.servlet.context-path}")
    private String contextPath = "";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 版权所有
     */
    private String copyright = "com.github.itdachen";

    /**
     * 备注
     */
    private String remarks = "";

    /**
     * 开发年份
     */
    private String yearly = "2023";

    /**
     * 发行人
     */
    private String issuer = "com.github.itdachen";

    /**
     * 简称
     */
    private String toAs = "";

    /**
     * 默认后台模板类型
     */
    private BackstageTemplateEnum template = BackstageTemplateEnum.PEAR;

    /**
     * 请求地址
     */
    private String askUri = "http://127.0.0.1:8080";

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

    public String getYearly() {
        return yearly;
    }

    public void setYearly(String yearly) {
        this.yearly = yearly;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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

    public BackstageTemplateEnum getTemplate() {
        return template;
    }

    public void setTemplate(BackstageTemplateEnum template) {
        this.template = template;
    }

    public String getAskUri() {
        return askUri;
    }

    public void setAskUri(String askUri) {
        this.askUri = askUri;
    }

    public String getContextPath() {
        if ("".equals(contextPath) || null == contextPath) {
            return "";
        }
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getToAs() {
        if ("".equals(toAs) || null == toAs) {
            return appName;
        }
        return toAs;
    }

    public void setToAs(String toAs) {
        this.toAs = toAs;
    }
}

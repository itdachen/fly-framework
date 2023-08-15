package com.github.itdachen.framework.autoconfigure.client;

import com.github.itdachen.framework.autoconfigure.client.enums.BackstageTemplateEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 项目配置, 针对于单体项目
 * Created by 王大宸 on 2023-08-15 10:44
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.client")
public class FlyClientProperties {

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
    private String copyright = "com.github.itdachen";

    /**
     * 项目名称
     */
    private String name = "FLY-NEXT";

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
    private String issuer = "com.github.itdachen";

    /**
     * 默认后台模板类型
     */
    private BackstageTemplateEnum template = BackstageTemplateEnum.PEAR;

    /**
     * 请求地址
     */
    private String askUri = "http://127.0.0.1:8080";

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

}

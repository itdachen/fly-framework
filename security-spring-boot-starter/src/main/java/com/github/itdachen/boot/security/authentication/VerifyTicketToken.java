package com.github.itdachen.boot.security.authentication;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户校验凭证
 *
 * @author admin
 * @date 2023-11-13 14:40:32
 */
public class VerifyTicketToken implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键唯一标识
     */
    private String id;

    /**
     * 应用端标识
     */
    private String appClient;

    /**
     * 应用端访问地址
     */
    private String appAskUri;

    /**
     * 目标平台标识
     */
    private String themeCode;

    /**
     * 目标访问地址
     */
    private String askUri;

    /**
     * 过期时间
     */
    private LocalDateTime expTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppClient() {
        return appClient;
    }

    public void setAppClient(String appClient) {
        this.appClient = appClient;
    }

    public String getAppAskUri() {
        return appAskUri;
    }

    public void setAppAskUri(String appAskUri) {
        this.appAskUri = appAskUri;
    }

    public String getThemeCode() {
        return themeCode;
    }

    public void setThemeCode(String themeCode) {
        this.themeCode = themeCode;
    }

    public String getAskUri() {
        return askUri;
    }

    public void setAskUri(String askUri) {
        this.askUri = askUri;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }
}

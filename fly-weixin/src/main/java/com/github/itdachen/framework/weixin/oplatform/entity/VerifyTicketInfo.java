package com.github.itdachen.framework.weixin.oplatform.entity;

import java.io.Serializable;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2023-01-30 20:23
 * Created with IntelliJ IDEA.
 */
public class VerifyTicketInfo implements Serializable {
    private static final long serialVersionUID = 238946690110248739L;


    private String appId;

    /**
     * 验证票证
     */
    private String verifyTicket;

    /**
     * 固定为："component_verify_ticket"
     */

    private String infoType;

    /**
     * 微信推送时间
     */
    private String createTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVerifyTicket() {
        return verifyTicket;
    }

    public void setVerifyTicket(String verifyTicket) {
        this.verifyTicket = verifyTicket;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VerifyTicketInfo{" +
                "appId='" + appId + '\'' +
                ", verifyTicket='" + verifyTicket + '\'' +
                ", infoType='" + infoType + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}

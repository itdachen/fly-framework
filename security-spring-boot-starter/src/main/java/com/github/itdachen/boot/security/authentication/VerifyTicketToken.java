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
     * 登录账号
     */
    private String account;

    /**
     * 租户ID/公司ID
     */
    private String tenantId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 身份ID
     */
    private String anId;

    /**
     * 身份名称
     */
    private String anTitle;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 服务提供商ID, 例如: 小程序 APPID
     */
    private String appId;

    /**
     * 服务商提供的用户ID, 例如: 小程序用户 openId
     */
    private String openId;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptTitle;

    /**
     * 上级部门ID
     */
    private String parentDeptId;

    /**
     * 部门层级
     */
    private String deptLevel;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAnId() {
        return anId;
    }

    public void setAnId(String anId) {
        this.anId = anId;
    }

    public String getAnTitle() {
        return anTitle;
    }

    public void setAnTitle(String anTitle) {
        this.anTitle = anTitle;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptTitle() {
        return deptTitle;
    }

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    public String getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }

    @Override
    public String toString() {
        return "VerifyTicketToken{" +
                "id='" + id + '\'' +
                ", appClient='" + appClient + '\'' +
                ", appAskUri='" + appAskUri + '\'' +
                ", themeCode='" + themeCode + '\'' +
                ", askUri='" + askUri + '\'' +
                ", account='" + account + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", anId='" + anId + '\'' +
                ", anTitle='" + anTitle + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", appId='" + appId + '\'' +
                ", openId='" + openId + '\'' +
                ", userType='" + userType + '\'' +
                ", sex='" + sex + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptTitle='" + deptTitle + '\'' +
                ", parentDeptId='" + parentDeptId + '\'' +
                ", deptLevel='" + deptLevel + '\'' +
                ", expTime=" + expTime +
                '}';
    }
}

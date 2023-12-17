package com.github.itdachen.framework.context.userdetails;

import com.github.itdachen.framework.context.permission.PermissionInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Description: 当前登录用户信息封装
 * Created by 王大宸 on 2023/04/15 21:29
 * Created with IntelliJ IDEA.
 */
public class CurrentUserDetails implements Serializable {
    private static final Long serialVersionUID = 6829345754240159691L;

    /**
     * 用户ID
     */
    private String id;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String accountSecret;

    /**
     * 租户ID/公司ID
     */
    private String tenantId;

    /**
     * 登录客户端
     */
    private String clientId;

    /**
     * 登录方式
     */
    private String signMethod;

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
     * 用户状态
     */
    private String status;

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
     * 是否超级管理员
     */
    private Boolean isSuperAdmin;

    /**
     * 过期时间(密码过期时间, 有些系统需要定期更新账号的密码)
     */
    private LocalDateTime expTime;

    /**
     * 其他信息
     */
    private Map<String, String> other;

    /**
     * 权限信息
     */
    private List<PermissionInfo> permissions;


    public CurrentUserDetails() {
    }

    public CurrentUserDetails(String id,
                              String tenantId,
                              String nickName,
                              String avatar) {
        this.id = id;
        this.tenantId = tenantId;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public CurrentUserDetails(String id,
                              String tenantId,
                              String clientId,
                              String signMethod,
                              String nickName,
                              String avatar,
                              String anId,
                              String anTitle,
                              String telephone,
                              String email,
                              String account,
                              String accountSecret,
                              String status,
                              String appId,
                              String openId,
                              String userType,
                              String sex,
                              String deptId,
                              String deptTitle,
                              Boolean isSuperAdmin,
                              Map<String, String> other,
                              List<PermissionInfo> permissions) {
        this.id = id;
        this.tenantId = tenantId;
        this.clientId = clientId;
        this.signMethod = signMethod;
        this.nickName = nickName;
        this.anId = anId;
        this.anTitle = anTitle;
        this.avatar = avatar;
        this.telephone = telephone;
        this.email = email;
        this.account = account;
        this.accountSecret = accountSecret;
        this.status = status;
        this.appId = appId;
        this.openId = openId;
        this.userType = userType;
        this.sex = sex;
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.isSuperAdmin = isSuperAdmin;
        this.other = other;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountSecret() {
        return accountSecret;
    }

    public void setAccountSecret(String accountSecret) {
        this.accountSecret = accountSecret;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }

    public Map<String, String> getOther() {
        return other;
    }

    public void setOther(Map<String, String> other) {
        this.other = other;
    }

    public List<PermissionInfo> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionInfo> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("clientId", getClientId())
                .append("signMethod", getSignMethod())
                .append("nickName", getNickName())
                .append("avatar", getAvatar())
                .append("anId", getAnId())
                .append("anTitle", getAnTitle())
                .append("telephone", getTelephone())
                .append("email", getEmail())
                .append("account", getAccount())
                .append("appId", getAppId())
                .append("openId", getOpenId())
                .append("userType", getUserType())
                .append("sex", getSex())
                .append("deptId", getDeptId())
                .append("deptTitle", getDeptTitle())
                .append("parentDeptId", getParentDeptId())
                .append("deptLevel", getDeptLevel())
                .append("other", getOther())
                .append("account", getAccount())
                .append("status", getStatus())
                .append("expireTime", getExpTime())
                .append("permissions", getPermissions())
                .toString();
    }


}

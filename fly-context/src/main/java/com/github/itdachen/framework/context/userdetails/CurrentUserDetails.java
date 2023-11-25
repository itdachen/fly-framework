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
     * 租户ID
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
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String accountSecret;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 服务提供商ID
     */
    private String appId;

    /**
     * 服务商提供的用户ID
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
     * 岗位ID
     */
    private String postId;

    /**
     * 岗位名称
     */
    private String postTitle;

    /**
     * 等级/级次
     */
    private String grade;

    /**
     * 是否超级管理员
     */
    private Boolean isSuperAdmin;

    /**
     * 删除标志(1-已删除;0-未删除)
     */
    private String delFlag;

    /**
     * 是否可删除(0-不可删除;1-可删除)
     */
    private String canDel;

    /**
     * 过期时间(密码过期时间, 有些系统需要定期更新账号的密码)
     */
    private LocalDateTime expireTime;

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

    public CurrentUserDetails(String id, String tenantId,
                              String clientId, String signMethod,
                              String nickName, String avatar,
                              String anId, String anTitle,
                              String telephone, String email,
                              String account, String accountSecret,
                              String status, String appId,
                              String openId, String userType,
                              String sex, String deptId,
                              String deptTitle, String postId,
                              String postTitle, String grade,
                              String delFlag, String canDel,
                              LocalDateTime expireTime,
                              Boolean isSuperAdmin, Map<String, String> other,
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
        this.postId = postId;
        this.postTitle = postTitle;
        this.grade = grade;
        this.delFlag = delFlag;
        this.canDel = canDel;
        this.expireTime = expireTime;
        this.isSuperAdmin = isSuperAdmin;
        this.other = other;
        this.permissions = permissions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
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

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    public String getDeptTitle() {
        return deptTitle;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setIsSuperAdmin(Boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public Boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setOther(Map<String, String> other) {
        this.other = other;
    }

    public Map<String, String> getOther() {
        return other;
    }

    public String getAccountSecret() {
        return accountSecret;
    }

    public void setAccountSecret(String accountSecret) {
        this.accountSecret = accountSecret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCanDel() {
        return canDel;
    }

    public void setCanDel(String canDel) {
        this.canDel = canDel;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
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
                .append("postId", getPostId())
                .append("postTitle", getPostTitle())
                .append("grade", getGrade())
                .append("isSuperAdmin", getIsSuperAdmin())
                .append("other", getOther())
                .append("accountSecret", getAccountSecret())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("canDel", getCanDel())
                .append("expireTime", getExpireTime())
                .append("permissions", getPermissions())
                .toString();
    }


}

package com.github.itdachen.framework.context.userdetails;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
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
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

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
    private String isSuperAdmin;

    /**
     * 其他信息
     */
    private Map<String, String> other;


    public CurrentUserDetails() {
    }

    public CurrentUserDetails(String id, String tenantId,
                              String nickName, String avatar,
                              String telephone, String email,
                              String account, String appId,
                              String openId, String userType,
                              String sex, String deptId,
                              String deptTitle, String postId,
                              String postTitle, String grade,
                              String isSuperAdmin, Map<String, String> other) {
        this.id = id;
        this.tenantId = tenantId;
        this.nickName = nickName;
        this.avatar = avatar;
        this.telephone = telephone;
        this.email = email;
        this.account = account;
        this.appId = appId;
        this.openId = openId;
        this.userType = userType;
        this.sex = sex;
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.postId = postId;
        this.postTitle = postTitle;
        this.grade = grade;
        this.isSuperAdmin = isSuperAdmin;
        this.other = other;
    }

    public static CurrentUserDetailsBuilder builder() {
        return new CurrentUserDetailsBuilder();
    }

    public static class CurrentUserDetailsBuilder {
        private String id;
        private String tenantId;
        private String nickName;
        private String avatar;
        private String telephone;
        private String email;
        private String account;
        private String appId;
        private String openId;
        private String userType;
        private String sex;
        private String deptId;
        private String deptTitle;
        private String postId;
        private String postTitle;
        private String grade;
        private String isSuperAdmin;
        private Map<String, String> other;

        public CurrentUserDetailsBuilder() {
        }

        /* 用户ID */
        public CurrentUserDetailsBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 租户ID */
        public CurrentUserDetailsBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 昵称 */
        public CurrentUserDetailsBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        /* 头像 */
        public CurrentUserDetailsBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        /* 联系电话 */
        public CurrentUserDetailsBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        /* 电子邮箱 */
        public CurrentUserDetailsBuilder email(String email) {
            this.email = email;
            return this;
        }

        /* 登录账号 */
        public CurrentUserDetailsBuilder account(String account) {
            this.account = account;
            return this;
        }

        /* 服务提供商ID */
        public CurrentUserDetailsBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        /* 服务商提供的用户ID */
        public CurrentUserDetailsBuilder openId(String openId) {
            this.openId = openId;
            return this;
        }

        /* 用户类型 */
        public CurrentUserDetailsBuilder userType(String userType) {
            this.userType = userType;
            return this;
        }

        /* 用户性别 */
        public CurrentUserDetailsBuilder sex(String sex) {
            this.sex = sex;
            return this;
        }

        /* 部门ID */
        public CurrentUserDetailsBuilder deptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        /* 部门名称 */
        public CurrentUserDetailsBuilder deptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
            return this;
        }

        /* 岗位ID */
        public CurrentUserDetailsBuilder postId(String postId) {
            this.postId = postId;
            return this;
        }

        /* 岗位名称 */
        public CurrentUserDetailsBuilder postTitle(String postTitle) {
            this.postTitle = postTitle;
            return this;
        }

        /* 等级/级次 */
        public CurrentUserDetailsBuilder grade(String grade) {
            this.grade = grade;
            return this;
        }

        /* 是否超级管理员 */
        public CurrentUserDetailsBuilder isSuperAdmin(String isSuperAdmin) {
            this.isSuperAdmin = isSuperAdmin;
            return this;
        }

        /* 其他信息 */
        public CurrentUserDetailsBuilder other(Map<String, String> other) {
            this.other = other;
            return this;
        }

        public CurrentUserDetails build() {
            return new CurrentUserDetails(id,
                    tenantId,
                    nickName,
                    avatar,
                    telephone,
                    email,
                    account,
                    appId,
                    openId,
                    userType,
                    sex,
                    deptId,
                    deptTitle,
                    postId,
                    postTitle,
                    grade,
                    isSuperAdmin,
                    other
            );
        }

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

    public void setIsSuperAdmin(String isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public String getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setOther(Map<String, String> other) {
        this.other = other;
    }

    public Map<String, String> getOther() {
        return other;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("nickName", getNickName())
                .append("avatar", getAvatar())
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
                .toString();
    }


}

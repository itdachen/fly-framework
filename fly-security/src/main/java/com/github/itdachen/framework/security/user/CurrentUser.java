package com.github.itdachen.framework.security.user;

import com.github.itdachen.framework.context.permission.PermissionInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Description:
 * Created by 王大宸 on 2022-10-16 13:50
 * Created with IntelliJ IDEA.
 */
public class CurrentUser extends User implements UserDetails {
    private static final long serialVersionUID = 3011307718906802263L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户登录账号
     */
    private String username;
    /**
     * 生产商家id,服务提供商
     */
    private String providerId;
    /**
     * 生产商家id,服务提供商
     */
    private String openId;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户类型
     */
    private String type;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户所属部门id
     */
    private String departId;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * 等级
     */
    private String grade;
    /**
     * 是否超级管理员
     */
    private String isSuperAdmin;
    /**
     * 用户手机号码
     */
    private String phone;
    /**
     * 积分
     */
    private String attr3;
    /**
     * 是否是商家 1-是 2-否 3-审核中
     */
    private String attr4;
    /**
     * 是否是志愿者 1-是 2-否
     */
    private String attr5;
    /**
     * 是否是管理员 1-是 2-否
     */
    private String attr6;
    /**
     * 是否是团队账号 1-是 2-否
     */
    private String attr7;
    /**
     * 活动次数
     */
    private String attr8;

    /**
     * 可以访问的 url
     */
    private Set<PermissionInfo> urls;

    /**
     * 拥有的权限标识
     */
    private Set<String> perms;

    /**
     * 拥有的角色
     */
    private Collection<GrantedAuthority> authorities;

    public CurrentUser(Builder builder) {
        // 登录账号,数据库密码,账号是否可用,账号没有过期,密码没过期,账户没被冻结,权限
        super(builder.username,
                builder.password,
                builder.enabled,
                builder.accountNonExpired,
                builder.credentialsNonExpired,
                builder.accountNonLocked,
                builder.authorities);
        this.id = builder.id;
        this.username = builder.username;
        this.name = builder.name;
        this.providerId = builder.providerId;
        this.openId = builder.openId;
        this.phone = builder.phone;
        this.sex = builder.sex;
        this.avatar = builder.avatar;
        this.departId = builder.departId;
        this.tenantId = builder.tenantId;
        this.grade = builder.grade;
        this.isSuperAdmin = builder.isSuperAdmin;
        this.type = builder.type;
        this.urls = builder.urls;
        this.perms = builder.perms;
        this.authorities = builder.authorities;
    }

    public static class Builder {
        private String id;
        private String username;
        private String password;
        private String providerId;
        private String openId;
        private String name;
        private String type;
        private String sex;
        private String avatar;
        private String departId;
        private String tenantId;
        private String grade;
        private String isSuperAdmin;
        private String phone;
        private Boolean enabled = true;
        private Boolean accountNonExpired = true;
        private Boolean credentialsNonExpired = true;
        private Boolean accountNonLocked = true;
        private Set<PermissionInfo> urls;
        private Set<String> perms;
        private Collection<GrantedAuthority> authorities;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder providerId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder openId(String openId) {
            this.openId = openId;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder departId(String departId) {
            this.departId = departId;
            return this;
        }

        public Builder grade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder isSuperAdmin(String isSuperAdmin) {
            this.isSuperAdmin = isSuperAdmin;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder accountNonExpired(Boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public Builder credentialsNonExpired(Boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Builder accountNonLocked(Boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public Builder urls(Set<PermissionInfo> urls) {
            this.urls = urls;
            return this;
        }

        public Builder perms(Set<String> perms) {
            this.perms = perms;
            return this;
        }

        public Builder authorities(Collection<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public CurrentUser build() {
            return new CurrentUser(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(String isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<PermissionInfo> getUrls() {
        return urls;
    }

    public void setUrls(Set<PermissionInfo> urls) {
        this.urls = urls;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public String getAttr8() {
        return attr8;
    }

    public void setAttr8(String attr8) {
        this.attr8 = attr8;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", providerId='" + providerId + '\'' +
                ", openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", departId='" + departId + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", grade='" + grade + '\'' +
                ", isSuperAdmin='" + isSuperAdmin + '\'' +
                ", phone='" + phone + '\'' +
                ", urls=" + urls +
                ", perms=" + perms +
                ", authorities=" + authorities +
                '}';
    }

}

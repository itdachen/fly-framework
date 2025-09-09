package com.github.itdachen.framework.context.userdetails;

import com.github.itdachen.framework.context.BizContextHandler;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

/**
 * Description: 用户信息封装
 * Created by 王大宸 on 2023/04/15 21:29
 * Created with IntelliJ IDEA.
 */
public class UserInfoDetails implements Serializable {
    private static final Long serialVersionUID = 6829345754240159691L;

    /**
     * 用户ID
     */
    private String id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码(登录成功后重置为空)
     */
    private String password;

    /**
     * 登录平台ID
     */
    private String platId;

    /**
     * 登录平台名称
     */
    private String platName;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用版本号
     */
    private String appVersion;

    /**
     * 应用上下文
     */
    private String appContextPath;

    /**
     * 租户ID/公司ID
     */
    private String tenantId;

    /**
     * 租户名称
     */
    private String tenantTitle;

    /**
     * 登录方式
     */
    private String loginMethod;

    /**
     * 昵称/姓名
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户类型: Y-超级管理员账号;N-普通账号
     */
    private String userType;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 有效标志: Y-有效;N-无效
     */
    private String validFlag;

    /**
     * 身份ID
     */
    private String roleId;

    /**
     * 身份名称
     */
    private String roleName;

    /**
     * 主身份标识: Y-主身份;N-非主身份标识
     */
    private String roleFlag;

    /**
     * 身份所属省ID, 例如: 贵州-52
     */
    private String provCode;

    /**
     * 省名称, 例如: 贵州省
     */
    private String provName;

    /**
     * 身份所属市/州ID,例如: 贵阳-5201
     */
    private String cityCode;

    /**
     * 身份所属市/州,例如: 贵阳
     */
    private String cityName;

    /**
     * 身份所属区/县ID例如: 贵阳云岩-520102
     */
    private String countyCode;

    /**
     * 身份所属区/县例如: 贵阳云岩
     */
    private String countyName;

    /**
     * 街道ID
     */
    private String townCode;

    /**
     * 街道名称
     */
    private String townName;

    /**
     * 身份所属部门ID
     */
    private String deptId;

    /**
     * 身份所属部门名称
     */
    private String deptTitle;

    /**
     * 身份所属部门类型/部门职能代码, 例如:10-董事会;11-总裁
     */
    private String deptType;

    /**
     * 身份所属部门等级, 例如:00-总部;10-省级
     */
    private String deptLevel;

    /**
     * 身份所属上级部门代码
     */
    private String deptParentId;

    /**
     * 操作主机IP
     */
    private String hostIp;

    /**
     * 操作主机系统
     */
    private String hostOs;

    /**
     * 操作主机浏览器
     */
    private String hostBrowser;

    /**
     * 操作地址, 例如: 贵州省贵阳市观山湖区...
     */
    private String hostAddr;

    /**
     * 操作主机浏览器代理信息
     */
    private String hostUserAgent;

    /**
     * 操作主机所在省ID
     */
    private String hostProv;

    /**
     * 操作主机所在市/州ID
     */
    private String hostCity;

    /**
     * 密码过期时间
     */
    private LocalDateTime expTime;

    /**
     * 密码上次更新时间
     */
    private LocalDateTime lastTime;


    public UserInfoDetails() {
    }

    public UserInfoDetails(String id, String username, String password, String platId, String platName, String appId, String appName, String appVersion, String appContextPath, String tenantId, String tenantTitle, String loginMethod, String nickName, String avatar, String email, String sex, String userType, String telephone, String validFlag, String roleId, String roleName, String roleFlag, String provCode, String provName, String cityCode, String cityName, String countyCode, String countyName,
                           String townCode, String townName,
                           String deptId, String deptTitle, String deptType, String deptLevel, String deptParentId, String hostIp, String hostOs, String hostBrowser, String hostAddr, String hostUserAgent, String hostProv, String hostCity, LocalDateTime expTime, LocalDateTime lastTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.platId = platId;
        this.platName = platName;
        this.appId = appId;
        this.appName = appName;
        this.appVersion = appVersion;
        this.appContextPath = appContextPath;
        this.tenantId = tenantId;
        this.tenantTitle = tenantTitle;
        this.loginMethod = loginMethod;
        this.nickName = nickName;
        this.avatar = avatar;
        this.email = email;
        this.sex = sex;
        this.userType = userType;
        this.telephone = telephone;
        this.validFlag = validFlag;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleFlag = roleFlag;
        this.provCode = provCode;
        this.provName = provName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.countyCode = countyCode;
        this.countyName = countyName;
        this.townCode = townCode;
        this.townName = townName;
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.deptType = deptType;
        this.deptLevel = deptLevel;
        this.deptParentId = deptParentId;
        this.hostIp = hostIp;
        this.hostOs = hostOs;
        this.hostBrowser = hostBrowser;
        this.hostAddr = hostAddr;
        this.hostUserAgent = hostUserAgent;
        this.hostProv = hostProv;
        this.hostCity = hostCity;
        this.expTime = expTime;
        this.lastTime = lastTime;
    }

    public static UserInfoDetailsBuilder builder() {
        return new UserInfoDetailsBuilder();
    }

    public static class UserInfoDetailsBuilder {
        private String id;
        private String username;
        private String password;
        private String platId;
        private String platName;
        private String appId;
        private String appName;
        private String appVersion;
        private String appContextPath;
        private String tenantId;
        private String tenantTitle;
        private String loginMethod;
        private String nickName;
        private String avatar;
        private String email;
        private String sex;
        private String userType;
        private String telephone;
        private String validFlag;
        private String roleId;
        private String roleName;
        private String roleFlag;
        private String provCode;
        private String provName;
        private String cityCode;
        private String cityName;
        private String countyCode;
        private String countyName;
        private String townCode;
        private String townName;
        private String deptId;
        private String deptTitle;
        private String deptType;
        private String deptLevel;
        private String deptParentId;
        private String hostIp;
        private String hostOs;
        private String hostBrowser;
        private String hostAddr;
        private String hostUserAgent;
        private String hostProv;
        private String hostCity;
        private LocalDateTime expTime;
        private LocalDateTime lastTime;

        public UserInfoDetailsBuilder() {
        }

        /* 用户ID */
        public UserInfoDetailsBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 登录账号 */
        public UserInfoDetailsBuilder username(String username) {
            this.username = username;
            return this;
        }

        /* 登录密码(登录成功后重置为空) */
        public UserInfoDetailsBuilder password(String password) {
            this.password = password;
            return this;
        }

        /* 登录平台ID */
        public UserInfoDetailsBuilder platId(String platId) {
            this.platId = platId;
            return this;
        }

        /* 登录平台名称 */
        public UserInfoDetailsBuilder platName(String platName) {
            this.platName = platName;
            return this;
        }

        /* 应用ID */
        public UserInfoDetailsBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        /* 应用名称 */
        public UserInfoDetailsBuilder appName(String appName) {
            this.appName = appName;
            return this;
        }

        /* 应用版本号 */
        public UserInfoDetailsBuilder appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        /* 应用上下文 */
        public UserInfoDetailsBuilder appContextPath(String appContextPath) {
            this.appContextPath = appContextPath;
            return this;
        }

        /* 租户ID/公司ID */
        public UserInfoDetailsBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 租户名称 */
        public UserInfoDetailsBuilder tenantTitle(String tenantTitle) {
            this.tenantTitle = tenantTitle;
            return this;
        }

        /* 登录方式 */
        public UserInfoDetailsBuilder loginMethod(String loginMethod) {
            this.loginMethod = loginMethod;
            return this;
        }

        /* 昵称/姓名 */
        public UserInfoDetailsBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        /* 头像 */
        public UserInfoDetailsBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        /* 电子邮箱 */
        public UserInfoDetailsBuilder email(String email) {
            this.email = email;
            return this;
        }

        /* 性别 */
        public UserInfoDetailsBuilder sex(String sex) {
            this.sex = sex;
            return this;
        }

        /* 用户类型: Y-超级管理员账号;N-普通账号 */
        public UserInfoDetailsBuilder userType(String userType) {
            this.userType = userType;
            return this;
        }

        /* 电话号码 */
        public UserInfoDetailsBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        /* 有效标志: Y-有效;N-无效 */
        public UserInfoDetailsBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        /* 身份ID */
        public UserInfoDetailsBuilder roleId(String roleId) {
            this.roleId = roleId;
            return this;
        }

        /* 身份名称 */
        public UserInfoDetailsBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        /* 主身份标识: Y-主身份;N-非主身份标识 */
        public UserInfoDetailsBuilder roleFlag(String roleFlag) {
            this.roleFlag = roleFlag;
            return this;
        }

        /* 身份所属省ID, 例如: 贵州-52 */
        public UserInfoDetailsBuilder provCode(String provCode) {
            this.provCode = provCode;
            return this;
        }

        /* 省名称, 例如: 贵州省 */
        public UserInfoDetailsBuilder provName(String provName) {
            this.provName = provName;
            return this;
        }

        /* 身份所属市/州ID,例如: 贵阳-5201 */
        public UserInfoDetailsBuilder cityCode(String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        /* 身份所属市/州,例如: 贵阳 */
        public UserInfoDetailsBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        /* 身份所属区/县ID例如: 贵阳云岩-520102 */
        public UserInfoDetailsBuilder countyCode(String countyCode) {
            this.countyCode = countyCode;
            return this;
        }

        /* 身份所属区/县例如: 贵阳云岩 */
        public UserInfoDetailsBuilder countyName(String countyName) {
            this.countyName = countyName;
            return this;
        }

        /* 街道ID */
        public UserInfoDetailsBuilder townCode(String townCode) {
            this.townCode = townCode;
            return this;
        }

        /* 街道名称 */
        public UserInfoDetailsBuilder townName(String townName) {
            this.townName = townName;
            return this;
        }

        /* 身份所属部门ID */
        public UserInfoDetailsBuilder deptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        /* 身份所属部门名称 */
        public UserInfoDetailsBuilder deptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
            return this;
        }

        /* 身份所属部门类型, 例如:10-董事会;11-总裁 */
        public UserInfoDetailsBuilder deptType(String deptType) {
            this.deptType = deptType;
            return this;
        }

        /* 身份所属部门等级, 例如:00-总部;10-省级 */
        public UserInfoDetailsBuilder deptLevel(String deptLevel) {
            this.deptLevel = deptLevel;
            return this;
        }

        /* 身份所属上级部门代码 */
        public UserInfoDetailsBuilder deptParentId(String deptParentId) {
            this.deptParentId = deptParentId;
            return this;
        }

        /* 操作主机IP */
        public UserInfoDetailsBuilder hostIp(String hostIp) {
            this.hostIp = hostIp;
            return this;
        }

        /* 操作主机系统 */
        public UserInfoDetailsBuilder hostOs(String hostOs) {
            this.hostOs = hostOs;
            return this;
        }

        /* 操作主机浏览器 */
        public UserInfoDetailsBuilder hostBrowser(String hostBrowser) {
            this.hostBrowser = hostBrowser;
            return this;
        }

        /* 操作地址, 例如: 贵州省贵阳市观山湖区... */
        public UserInfoDetailsBuilder hostAddr(String hostAddr) {
            this.hostAddr = hostAddr;
            return this;
        }

        /* 操作主机浏览器代理信息 */
        public UserInfoDetailsBuilder hostUserAgent(String hostUserAgent) {
            this.hostUserAgent = hostUserAgent;
            return this;
        }

        /* 操作主机所在省ID */
        public UserInfoDetailsBuilder hostProv(String hostProv) {
            this.hostProv = hostProv;
            return this;
        }

        /* 操作主机所在市/州ID */
        public UserInfoDetailsBuilder hostCity(String hostCity) {
            this.hostCity = hostCity;
            return this;
        }

        /* 密码过期时间 */
        public UserInfoDetailsBuilder expTime(LocalDateTime expTime) {
            this.expTime = expTime;
            return this;
        }

        /* 密码上次更新时间 */
        public UserInfoDetailsBuilder lastTime(LocalDateTime lastTime) {
            this.lastTime = lastTime;
            return this;
        }

        public UserInfoDetails build() {
            return new UserInfoDetails(id,
                    username,
                    password,
                    platId,
                    platName,
                    appId,
                    appName,
                    appVersion,
                    appContextPath,
                    tenantId,
                    tenantTitle,
                    loginMethod,
                    nickName,
                    avatar,
                    email,
                    sex,
                    userType,
                    telephone,
                    validFlag,
                    roleId,
                    roleName,
                    roleFlag,
                    provCode,
                    provName,
                    cityCode,
                    cityName,
                    countyCode,
                    countyName,
                    townCode,
                    townName,
                    deptId,
                    deptTitle,
                    deptType,
                    deptLevel,
                    deptParentId,
                    hostIp,
                    hostOs,
                    hostBrowser,
                    hostAddr,
                    hostUserAgent,
                    hostProv,
                    hostCity,
                    expTime,
                    lastTime
            );
        }

    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getPlatId() {
        return platId;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getPlatName() {
        return platName;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppContextPath(String appContextPath) {
        this.appContextPath = appContextPath;
    }

    public String getAppContextPath() {
        return appContextPath;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantTitle(String tenantTitle) {
        this.tenantTitle = tenantTitle;
    }

    public String getTenantTitle() {
        return tenantTitle;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getLoginMethod() {
        return loginMethod;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getProvName() {
        return provName;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
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

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptParentId(String deptParentId) {
        this.deptParentId = deptParentId;
    }

    public String getDeptParentId() {
        return deptParentId;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostOs(String hostOs) {
        this.hostOs = hostOs;
    }

    public String getHostOs() {
        return hostOs;
    }

    public void setHostBrowser(String hostBrowser) {
        this.hostBrowser = hostBrowser;
    }

    public String getHostBrowser() {
        return hostBrowser;
    }

    public void setHostAddr(String hostAddr) {
        this.hostAddr = hostAddr;
    }

    public String getHostAddr() {
        return hostAddr;
    }

    public void setHostUserAgent(String hostUserAgent) {
        this.hostUserAgent = hostUserAgent;
    }

    public String getHostUserAgent() {
        return hostUserAgent;
    }

    public void setHostProv(String hostProv) {
        this.hostProv = hostProv;
    }

    public String getHostProv() {
        return hostProv;
    }

    public void setHostCity(String hostCity) {
        this.hostCity = hostCity;
    }

    public String getHostCity() {
        return hostCity;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("platId", getPlatId())
                .append("platName", getPlatName())
                .append("appId", getAppId())
                .append("appName", getAppName())
                .append("appVersion", getAppVersion())
                .append("appContextPath", getAppContextPath())
                .append("tenantId", getTenantId())
                .append("tenantTitle", getTenantTitle())
                .append("loginMethod", getLoginMethod())
                .append("nickName", getNickName())
                .append("avatar", getAvatar())
                .append("email", getEmail())
                .append("sex", getSex())
                .append("userType", getUserType())
                .append("telephone", getTelephone())
                .append("validFlag", getValidFlag())
                .append("roleId", getRoleId())
                .append("roleName", getRoleName())
                .append("roleFlag", getRoleFlag())
                .append("provCode", getProvCode())
                .append("provName", getProvName())
                .append("cityCode", getCityCode())
                .append("cityName", getCityName())
                .append("countyCode", getCountyCode())
                .append("countyName", getCountyName())
                .append("townCode", getTownCode())
                .append("townName", getTownName())
                .append("deptId", getDeptId())
                .append("deptTitle", getDeptTitle())
                .append("deptType", getDeptType())
                .append("deptLevel", getDeptLevel())
                .append("deptParentId", getDeptParentId())
                .append("hostIp", getHostIp())
                .append("hostOs", getHostOs())
                .append("hostBrowser", getHostBrowser())
                .append("hostAddr", getHostAddr())
                .append("hostUserAgent", getHostUserAgent())
                .append("hostProv", getHostProv())
                .append("hostCity", getHostCity())
                .append("expTime", getExpTime())
                .append("lastTime", getLastTime())
                .toString();
    }


}

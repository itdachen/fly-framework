package com.github.itdachen.framework.context.userdetails;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

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
     * 昵称/姓名
     */
    private String nickName;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码(登录成功后重置为空)
     */
    private String password;

    /**
     * 租户ID/公司ID
     */
    private String tenantId;

    /**
     * 登录方式
     */
    private String signMethod;

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
     * 用户类型
     */
    private String userType;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 有效标志: Y-是;N-否;
     */
    private String validFlag;

    /**
     * 登录平台ID
     */
    private String platId;

    /**
     * 登录平台名称
     */
    private String platName;

    /**
     * 登录应用ID
     */
    private String appId;

    /**
     * 登录应用名称
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
     * 身份ID/身份代码
     */
    private String roleId;

    /**
     * 主身份标识: Y-主身份;N-非主身份标识
     */
    private String roleFlag;

    /**
     * 身份名称
     */
    private String roleName;

    /**
     * 部门代码(不同身份可能会不一样, 跟着不同身份信息绑定)
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptTitle;

    /**
     * 上级部门代码
     */
    private String parentDeptId;

    /**
     * 部门等级
     */
    private String deptLevel;

    /**
     * 身份所属省份代码, 例如: 贵州-52
     */
    private String provId;

    /**
     * 身份所属市州代码, 例如: 贵阳-5201
     */
    private String cityId;

    /**
     * 身份所属区县代码, 例如: 贵阳南明-520101
     */
    private String countyId;

    /**
     * 登录主机IP
     */
    private String hostIp;

    /**
     * 登录主机地址代码, 例如: 贵州-52
     */
    private String hostProv;

    /**
     * 登录主机地址代码, 例如: 贵阳-5201
     */
    private String hostCity;

    /**
     * 登录主机详细地址
     */
    private String hostAddr;

    /**
     * 代理信息
     */
    private String userAgent;

    /**
     * 过期时间(密码过期时间, 有些系统需要定期更新账号的密码)
     */
    private LocalDateTime expTime;


    public UserInfoDetails() {
    }

    public UserInfoDetails(String id, String username, String password, String tenantId, String signMethod, String nickName, String avatar, String email, String sex, String userType, String telephone, String validFlag, String platId, String platName, String appId, String appName, String appVersion, String appContextPath, String roleId, String roleFlag, String roleName, String deptId, String deptTitle, String parentDeptId, String deptLevel, String provId, String cityId, String countyId, String hostIp, String hostProv, String hostCity, String hostAddr, String userAgent, LocalDateTime expTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tenantId = tenantId;
        this.signMethod = signMethod;
        this.nickName = nickName;
        this.avatar = avatar;
        this.email = email;
        this.sex = sex;
        this.userType = userType;
        this.telephone = telephone;
        this.validFlag = validFlag;
        this.platId = platId;
        this.platName = platName;
        this.appId = appId;
        this.appName = appName;
        this.appVersion = appVersion;
        this.appContextPath = appContextPath;
        this.roleId = roleId;
        this.roleFlag = roleFlag;
        this.roleName = roleName;
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.parentDeptId = parentDeptId;
        this.deptLevel = deptLevel;
        this.provId = provId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.hostIp = hostIp;
        this.hostProv = hostProv;
        this.hostCity = hostCity;
        this.hostAddr = hostAddr;
        this.userAgent = userAgent;
        this.expTime = expTime;
    }

    public static UserInfoDetailsBuilder builder() {
        return new UserInfoDetailsBuilder();
    }

    public static class UserInfoDetailsBuilder {
        private String id;
        private String username;
        private String password;
        private String tenantId;
        private String signMethod;
        private String nickName;
        private String avatar;
        private String email;
        private String sex;
        private String userType;
        private String telephone;
        private String validFlag;
        private String platId;
        private String platName;
        private String appId;
        private String appName;
        private String appVersion;
        private String appContextPath;
        private String roleId;
        private String roleFlag;
        private String roleName;
        private String deptId;
        private String deptTitle;
        private String parentDeptId;
        private String deptLevel;
        private String provId;
        private String cityId;
        private String countyId;
        private String hostIp;
        private String hostProv;
        private String hostCity;
        private String hostAddr;
        private String userAgent;
        private LocalDateTime expTime;

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

        /* 租户ID/公司ID */
        public UserInfoDetailsBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 登录方式 */
        public UserInfoDetailsBuilder signMethod(String signMethod) {
            this.signMethod = signMethod;
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

        /* 用户类型 */
        public UserInfoDetailsBuilder userType(String userType) {
            this.userType = userType;
            return this;
        }

        /* 电话号码 */
        public UserInfoDetailsBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        /* 有效标志: Y-是;N-否; */
        public UserInfoDetailsBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
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

        /* 登录应用ID */
        public UserInfoDetailsBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        /* 登录应用名称 */
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

        /* 身份ID/身份代码 */
        public UserInfoDetailsBuilder roleId(String roleId) {
            this.roleId = roleId;
            return this;
        }

        /* 主身份标识: Y-主身份;N-非主身份标识 */
        public UserInfoDetailsBuilder roleFlag(String roleFlag) {
            this.roleFlag = roleFlag;
            return this;
        }

        /* 身份名称 */
        public UserInfoDetailsBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        /* 部门代码(不同身份可能会不一样, 跟着不同身份信息绑定) */
        public UserInfoDetailsBuilder deptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        /* 部门名称 */
        public UserInfoDetailsBuilder deptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
            return this;
        }

        /* 上级部门代码 */
        public UserInfoDetailsBuilder parentDeptId(String parentDeptId) {
            this.parentDeptId = parentDeptId;
            return this;
        }

        /* 部门等级 */
        public UserInfoDetailsBuilder deptLevel(String deptLevel) {
            this.deptLevel = deptLevel;
            return this;
        }

        /* 身份所属省份代码, 例如: 贵州-52 */
        public UserInfoDetailsBuilder provId(String provId) {
            this.provId = provId;
            return this;
        }

        /* 身份所属市州代码, 例如: 贵阳-5201 */
        public UserInfoDetailsBuilder cityId(String cityId) {
            this.cityId = cityId;
            return this;
        }

        /* 身份所属区县代码, 例如: 贵阳南明-520101 */
        public UserInfoDetailsBuilder countyId(String countyId) {
            this.countyId = countyId;
            return this;
        }

        /* 登录主机IP */
        public UserInfoDetailsBuilder hostIp(String hostIp) {
            this.hostIp = hostIp;
            return this;
        }

        /* 登录主机地址代码, 例如: 贵州-52 */
        public UserInfoDetailsBuilder hostProv(String hostProv) {
            this.hostProv = hostProv;
            return this;
        }

        /* 登录主机地址代码, 例如: 贵阳-5201 */
        public UserInfoDetailsBuilder hostCity(String hostCity) {
            this.hostCity = hostCity;
            return this;
        }

        /* 登录主机详细地址 */
        public UserInfoDetailsBuilder hostAddr(String hostAddr) {
            this.hostAddr = hostAddr;
            return this;
        }

        /* 代理信息 */
        public UserInfoDetailsBuilder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /* 过期时间(密码过期时间, 有些系统需要定期更新账号的密码) */
        public UserInfoDetailsBuilder expTime(LocalDateTime expTime) {
            this.expTime = expTime;
            return this;
        }

        public UserInfoDetails build() {
            return new UserInfoDetails(id,
                    username,
                    password,
                    tenantId,
                    signMethod,
                    nickName,
                    avatar,
                    email,
                    sex,
                    userType,
                    telephone,
                    validFlag,
                    platId,
                    platName,
                    appId,
                    appName,
                    appVersion,
                    appContextPath,
                    roleId,
                    roleFlag,
                    roleName,
                    deptId,
                    deptTitle,
                    parentDeptId,
                    deptLevel,
                    provId,
                    cityId,
                    countyId,
                    hostIp,
                    hostProv,
                    hostCity,
                    hostAddr,
                    userAgent,
                    expTime
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

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getSignMethod() {
        return signMethod;
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

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
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

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getParentDeptId() {
        return parentDeptId;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvId() {
        return provId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostIp() {
        return hostIp;
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

    public void setHostAddr(String hostAddr) {
        this.hostAddr = hostAddr;
    }

    public String getHostAddr() {
        return hostAddr;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("tenantId", getTenantId())
                .append("signMethod", getSignMethod())
                .append("nickName", getNickName())
                .append("avatar", getAvatar())
                .append("email", getEmail())
                .append("sex", getSex())
                .append("userType", getUserType())
                .append("telephone", getTelephone())
                .append("validFlag", getValidFlag())
                .append("platId", getPlatId())
                .append("platName", getPlatName())
                .append("appId", getAppId())
                .append("appName", getAppName())
                .append("appVersion", getAppVersion())
                .append("appContextPath", getAppContextPath())
                .append("roleId", getRoleId())
                .append("roleFlag", getRoleFlag())
                .append("roleName", getRoleName())
                .append("deptId", getDeptId())
                .append("deptTitle", getDeptTitle())
                .append("parentDeptId", getParentDeptId())
                .append("deptLevel", getDeptLevel())
                .append("provId", getProvId())
                .append("cityId", getCityId())
                .append("countyId", getCountyId())
                .append("hostIp", getHostIp())
                .append("hostProv", getHostProv())
                .append("hostCity", getHostCity())
                .append("hostAddr", getHostAddr())
                .append("userAgent", getUserAgent())
                .append("expTime", getExpTime())
                .toString();
    }


}

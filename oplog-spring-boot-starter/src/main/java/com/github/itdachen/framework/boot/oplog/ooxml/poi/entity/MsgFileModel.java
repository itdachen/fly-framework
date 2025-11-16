package com.github.itdachen.framework.boot.oplog.ooxml.poi.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 消息文件
 *
 * @author 剑鸣秋朔
 * @date 2025-07-22 10:49
 */
public class MsgFileModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键唯一标识
     */
    private String id;

    /**
     * 租户ID/公司ID
     */
    private String tenantId;

    /**
     * 租户名称/公司名称
     */
    private String tenantTitle;

    /**
     * 平台ID
     */
    private String platId;

    /**
     * 平台名称
     */
    private String platTitle;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appTitle;

    /**
     * 应用版本号
     */
    private String appVersion;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 身份ID
     */
    private String roleId;

    /**
     * 身份名称
     */
    private String roleName;

    /**
     * 省代码
     */
    private String provId;

    /**
     * 省名称
     */
    private String provTitle;

    /**
     * 市/州代码
     */
    private String cityId;

    /**
     * 市/州名称
     */
    private String cityTitle;

    /**
     * 区/县代码
     */
    private String countyId;

    /**
     * 区县名称
     */
    private String countyTitle;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptTitle;

    /**
     * 部门等级
     */
    private String deptLevel;

    /**
     * 上级部门代码
     */
    private String deptParentId;

    /**
     * 操作IP地址
     */
    private String hostIp;

    /**
     * 操作地址
     */
    private String hostAddress;

    /**
     * 操作系统
     */
    private String hostOs;

    /**
     * 操作浏览器
     */
    private String hostBrowser;

    /**
     * 用户代理
     */
    private String hostUserAgent;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息类型名称
     */
    private String msgTypeTitle;

    /**
     * 消息分类
     */
    private String clazzType;

    /**
     * 消息分类名称
     */
    private String clazzTitle;

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 消息标题
     */
    private String msgTitle;

    /**
     * 文件名称
     */
    private String fileTitle;

    /**
     * 文件格式
     */
    private String fileFormat;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 文件大小
     */
    private String fileSize;


    /**
     * 文件MD5值
     */
    private String hexMd5;

    /**
     * 年份
     */
    private String yearly;

    /**
     * 月份
     */
    private String monthly;


    public MsgFileModel() {
    }

    public MsgFileModel(String id, String tenantId, String tenantTitle,
                        String platId, String platTitle, String appId, String appTitle, String appVersion,
                        String userId, String nickName, String roleId, String roleName,
                        String provId, String provTitle, String cityId, String cityTitle, String countyId, String countyTitle,
                        String deptId, String deptTitle, String deptLevel, String deptParentId,
                        String hostIp, String hostAddress, String hostOs, String hostBrowser, String hostUserAgent,
                        String msgType, String msgTypeTitle, String clazzType, String clazzTitle,
                        String msgId, String msgTitle, String fileTitle, String fileFormat, String fileUrl,
                        String fileSize, String hexMd5, String yearly, String monthly) {
        this.id = id;
        this.tenantId = tenantId;
        this.tenantTitle = tenantTitle;
        this.platId = platId;
        this.platTitle = platTitle;
        this.appId = appId;
        this.appTitle = appTitle;
        this.appVersion = appVersion;
        this.userId = userId;
        this.nickName = nickName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.provId = provId;
        this.provTitle = provTitle;
        this.cityId = cityId;
        this.cityTitle = cityTitle;
        this.countyId = countyId;
        this.countyTitle = countyTitle;
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.deptLevel = deptLevel;
        this.deptParentId = deptParentId;
        this.hostIp = hostIp;
        this.hostAddress = hostAddress;
        this.hostOs = hostOs;
        this.hostBrowser = hostBrowser;
        this.hostUserAgent = hostUserAgent;
        this.msgType = msgType;
        this.msgTypeTitle = msgTypeTitle;
        this.clazzType = clazzType;
        this.clazzTitle = clazzTitle;
        this.msgId = msgId;
        this.msgTitle = msgTitle;
        this.fileTitle = fileTitle;
        this.fileFormat = fileFormat;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.hexMd5 = hexMd5;
        this.yearly = yearly;
        this.monthly = monthly;
    }

    public static MsgFileModelBuilder builder() {
        return new MsgFileModelBuilder();
    }

    public static class MsgFileModelBuilder {
        private String id;
        private String tenantId;
        private String tenantTitle;
        private String platId;
        private String platTitle;
        private String appId;
        private String appTitle;
        private String appVersion;
        private String userId;
        private String nickName;
        private String roleId;
        private String roleName;
        private String provId;
        private String provTitle;
        private String cityId;
        private String cityTitle;
        private String countyId;
        private String countyTitle;
        private String deptId;
        private String deptTitle;
        private String deptLevel;
        private String deptParentId;
        private String hostIp;
        private String hostAddress;
        private String hostOs;
        private String hostBrowser;
        private String hostUserAgent;
        private String msgType;
        private String msgTypeTitle;
        private String clazzType;
        private String clazzTitle;
        private String msgId;
        private String msgTitle;
        private String fileTitle;
        private String fileFormat;
        private String fileUrl;
        private String fileSize;
        private String hexMd5;
        private String yearly;
        private String monthly;

        public MsgFileModelBuilder() {
        }

        /* 主键唯一标识 */
        public MsgFileModelBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 租户ID/公司ID */
        public MsgFileModelBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 租户名称/公司名称 */
        public MsgFileModelBuilder tenantTitle(String tenantTitle) {
            this.tenantTitle = tenantTitle;
            return this;
        }

        /* 平台ID */
        public MsgFileModelBuilder platId(String platId) {
            this.platId = platId;
            return this;
        }

        /* 平台名称 */
        public MsgFileModelBuilder platTitle(String platTitle) {
            this.platTitle = platTitle;
            return this;
        }

        /* 应用ID */
        public MsgFileModelBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        /* 应用名称 */
        public MsgFileModelBuilder appTitle(String appTitle) {
            this.appTitle = appTitle;
            return this;
        }

        /* 应用版本号 */
        public MsgFileModelBuilder appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        /* 用户ID */
        public MsgFileModelBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        /* 昵称 */
        public MsgFileModelBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        /* 身份ID */
        public MsgFileModelBuilder roleId(String roleId) {
            this.roleId = roleId;
            return this;
        }

        /* 身份名称 */
        public MsgFileModelBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        /* 省代码 */
        public MsgFileModelBuilder provId(String provId) {
            this.provId = provId;
            return this;
        }

        /* 省名称 */
        public MsgFileModelBuilder provTitle(String provTitle) {
            this.provTitle = provTitle;
            return this;
        }

        /* 市/州代码 */
        public MsgFileModelBuilder cityId(String cityId) {
            this.cityId = cityId;
            return this;
        }

        /* 市/州名称 */
        public MsgFileModelBuilder cityTitle(String cityTitle) {
            this.cityTitle = cityTitle;
            return this;
        }

        /* 区/县代码 */
        public MsgFileModelBuilder countyId(String countyId) {
            this.countyId = countyId;
            return this;
        }

        /* 区县名称 */
        public MsgFileModelBuilder countyTitle(String countyTitle) {
            this.countyTitle = countyTitle;
            return this;
        }

        /* 部门ID */
        public MsgFileModelBuilder deptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        /* 部门名称 */
        public MsgFileModelBuilder deptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
            return this;
        }

        /* 部门等级 */
        public MsgFileModelBuilder deptLevel(String deptLevel) {
            this.deptLevel = deptLevel;
            return this;
        }

        /* 上级部门代码 */
        public MsgFileModelBuilder deptParentId(String deptParentId) {
            this.deptParentId = deptParentId;
            return this;
        }

        /* 操作IP地址 */
        public MsgFileModelBuilder hostIp(String hostIp) {
            this.hostIp = hostIp;
            return this;
        }

        /* 操作地址 */
        public MsgFileModelBuilder hostAddress(String hostAddress) {
            this.hostAddress = hostAddress;
            return this;
        }

        /* 操作系统 */
        public MsgFileModelBuilder hostOs(String hostOs) {
            this.hostOs = hostOs;
            return this;
        }

        /* 操作浏览器 */
        public MsgFileModelBuilder hostBrowser(String hostBrowser) {
            this.hostBrowser = hostBrowser;
            return this;
        }

        /* 用户代理 */
        public MsgFileModelBuilder hostUserAgent(String hostUserAgent) {
            this.hostUserAgent = hostUserAgent;
            return this;
        }

        /* 消息类型 */
        public MsgFileModelBuilder msgType(String msgType) {
            this.msgType = msgType;
            return this;
        }

        /* 消息类型名称 */
        public MsgFileModelBuilder msgTypeTitle(String msgTypeTitle) {
            this.msgTypeTitle = msgTypeTitle;
            return this;
        }

        /* 消息分类 */
        public MsgFileModelBuilder clazzType(String clazzType) {
            this.clazzType = clazzType;
            return this;
        }

        /* 消息分类名称 */
        public MsgFileModelBuilder clazzTitle(String clazzTitle) {
            this.clazzTitle = clazzTitle;
            return this;
        }

        /* 消息ID */
        public MsgFileModelBuilder msgId(String msgId) {
            this.msgId = msgId;
            return this;
        }

        /* 消息标题 */
        public MsgFileModelBuilder msgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
            return this;
        }

        /* 文件名称 */
        public MsgFileModelBuilder fileTitle(String fileTitle) {
            this.fileTitle = fileTitle;
            return this;
        }

        /* 文件格式 */
        public MsgFileModelBuilder fileFormat(String fileFormat) {
            this.fileFormat = fileFormat;
            return this;
        }

        /* 文件地址 */
        public MsgFileModelBuilder fileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        /* 文件大小 */
        public MsgFileModelBuilder fileSize(String fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public MsgFileModelBuilder hexMd5(String hexMd5) {
            this.hexMd5 = hexMd5;
            return this;
        }

        /* 年份 */
        public MsgFileModelBuilder yearly(String yearly) {
            this.yearly = yearly;
            return this;
        }

        /* 月份 */
        public MsgFileModelBuilder monthly(String monthly) {
            this.monthly = monthly;
            return this;
        }

        public MsgFileModel build() {
            return new MsgFileModel(id,
                    tenantId,
                    tenantTitle,
                    platId,
                    platTitle,
                    appId,
                    appTitle,
                    appVersion,
                    userId,
                    nickName,
                    roleId,
                    roleName,
                    provId,
                    provTitle,
                    cityId,
                    cityTitle,
                    countyId,
                    countyTitle,
                    deptId,
                    deptTitle,
                    deptLevel,
                    deptParentId,
                    hostIp,
                    hostAddress,
                    hostOs,
                    hostBrowser,
                    hostUserAgent,
                    msgType,
                    msgTypeTitle,
                    clazzType,
                    clazzTitle,
                    msgId,
                    msgTitle,
                    fileTitle,
                    fileFormat,
                    fileUrl,
                    fileSize,
                    hexMd5,
                    yearly,
                    monthly
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

    public void setTenantTitle(String tenantTitle) {
        this.tenantTitle = tenantTitle;
    }

    public String getTenantTitle() {
        return tenantTitle;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getPlatId() {
        return platId;
    }

    public void setPlatTitle(String platTitle) {
        this.platTitle = platTitle;
    }

    public String getPlatTitle() {
        return platTitle;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
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

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvTitle(String provTitle) {
        this.provTitle = provTitle;
    }

    public String getProvTitle() {
        return provTitle;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyTitle(String countyTitle) {
        this.countyTitle = countyTitle;
    }

    public String getCountyTitle() {
        return countyTitle;
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

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostAddress() {
        return hostAddress;
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

    public void setHostUserAgent(String hostUserAgent) {
        this.hostUserAgent = hostUserAgent;
    }

    public String getHostUserAgent() {
        return hostUserAgent;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgTypeTitle(String msgTypeTitle) {
        this.msgTypeTitle = msgTypeTitle;
    }

    public String getMsgTypeTitle() {
        return msgTypeTitle;
    }

    public void setClazzType(String clazzType) {
        this.clazzType = clazzType;
    }

    public String getClazzType() {
        return clazzType;
    }

    public void setClazzTitle(String clazzTitle) {
        this.clazzTitle = clazzTitle;
    }

    public String getClazzTitle() {
        return clazzTitle;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }


    public String getHexMd5() {
        return hexMd5;
    }

    public void setHexMd5(String hexMd5) {
        this.hexMd5 = hexMd5;
    }

    public void setYearly(String yearly) {
        this.yearly = yearly;
    }

    public String getYearly() {
        return yearly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getMonthly() {
        return monthly;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("tenantTitle", getTenantTitle())
                .append("platId", getPlatId())
                .append("platTitle", getPlatTitle())
                .append("appId", getAppId())
                .append("appTitle", getAppTitle())
                .append("appVersion", getAppVersion())
                .append("userId", getUserId())
                .append("nickName", getNickName())
                .append("roleId", getRoleId())
                .append("roleName", getRoleName())
                .append("provId", getProvId())
                .append("provTitle", getProvTitle())
                .append("cityId", getCityId())
                .append("cityTitle", getCityTitle())
                .append("countyId", getCountyId())
                .append("countyTitle", getCountyTitle())
                .append("deptId", getDeptId())
                .append("deptTitle", getDeptTitle())
                .append("deptLevel", getDeptLevel())
                .append("deptParentId", getDeptParentId())
                .append("hostIp", getHostIp())
                .append("hostAddress", getHostAddress())
                .append("hostOs", getHostOs())
                .append("hostBrowser", getHostBrowser())
                .append("hostUserAgent", getHostUserAgent())
                .append("msgType", getMsgType())
                .append("msgTypeTitle", getMsgTypeTitle())
                .append("clazzType", getClazzType())
                .append("clazzTitle", getClazzTitle())
                .append("msgId", getMsgId())
                .append("msgTitle", getMsgTitle())
                .append("fileTitle", getFileTitle())
                .append("fileFormat", getFileFormat())
                .append("fileUrl", getFileUrl())
                .append("fileSize", getFileSize())
                .append("hexMd5", getHexMd5())
                .append("yearly", getYearly())
                .append("monthly", getMonthly())
                .toString();
    }

}

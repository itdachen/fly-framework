package com.github.itdachen.framework.boot.oplog.ooxml.poi.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 导入导出
 *
 * @author 剑鸣秋朔
 * @date 2025-07-28 15:31
 */
public class OplogPoiModel implements Serializable {
    private static final long serialVersionUID = 973371028355346801L;

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
     * 区/县名称
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
     * 消息表ID
     */
    private String msgId;

    /**
     * 功能ID/菜单ID(备用)
     */
    private String funcId;

    /**
     * 功能名称(备用)
     */
    private String funcTitle;

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
     * 请求ID
     */
    private String requestId;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 请求方式: POST, PUT, GET, DELETE
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 数据总量
     */
    private String dataTotal;

    /**
     * 导入文件名称
     */
    private String fileName;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件数量
     */
    private String fileTotal;

    /**
     * 异常信息
     */
    private String exInfo;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 服务端处理耗时
     */
    private String executeTime;


    public OplogPoiModel() {
    }

    public OplogPoiModel(String id, String tenantId, String tenantTitle, String platId, String platTitle, String appId, String appTitle, String appVersion, String userId, String nickName, String roleId, String roleName, String provId, String provTitle, String cityId, String cityTitle, String countyId, String countyTitle, String deptId, String deptTitle, String deptLevel, String deptParentId, String msgId, String funcId, String funcTitle, String hostIp, String hostAddress, String hostOs, String hostBrowser, String hostUserAgent, String requestId, String requestUri, String requestMethod, String requestParams, String dataTotal, String fileName, String fileUrl, String fileSize, String fileTotal, String exInfo, String remarks, String executeTime) {
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
        this.msgId = msgId;
        this.funcId = funcId;
        this.funcTitle = funcTitle;
        this.hostIp = hostIp;
        this.hostAddress = hostAddress;
        this.hostOs = hostOs;
        this.hostBrowser = hostBrowser;
        this.hostUserAgent = hostUserAgent;
        this.requestId = requestId;
        this.requestUri = requestUri;
        this.requestMethod = requestMethod;
        this.requestParams = requestParams;
        this.dataTotal = dataTotal;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.fileTotal = fileTotal;
        this.exInfo = exInfo;
        this.remarks = remarks;
        this.executeTime = executeTime;
    }

    public static OplogPoiModelBuilder builder() {
        return new OplogPoiModelBuilder();
    }

    public static class OplogPoiModelBuilder {
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
        private String msgId;
        private String funcId;
        private String funcTitle;
        private String hostIp;
        private String hostAddress;
        private String hostOs;
        private String hostBrowser;
        private String hostUserAgent;
        private String requestId;
        private String requestUri;
        private String requestMethod;
        private String requestParams;
        private String dataTotal;
        private String fileName;
        private String fileUrl;
        private String fileSize;
        private String fileTotal;
        private String exInfo;
        private String remarks;
        private String executeTime;

        public OplogPoiModelBuilder() {
        }

        /* 主键唯一标识 */
        public OplogPoiModelBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 租户ID/公司ID */
        public OplogPoiModelBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 租户名称/公司名称 */
        public OplogPoiModelBuilder tenantTitle(String tenantTitle) {
            this.tenantTitle = tenantTitle;
            return this;
        }

        /* 平台ID */
        public OplogPoiModelBuilder platId(String platId) {
            this.platId = platId;
            return this;
        }

        /* 平台名称 */
        public OplogPoiModelBuilder platTitle(String platTitle) {
            this.platTitle = platTitle;
            return this;
        }

        /* 应用ID */
        public OplogPoiModelBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        /* 应用名称 */
        public OplogPoiModelBuilder appTitle(String appTitle) {
            this.appTitle = appTitle;
            return this;
        }

        /* 应用版本号 */
        public OplogPoiModelBuilder appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        /* 用户ID */
        public OplogPoiModelBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        /* 昵称 */
        public OplogPoiModelBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        /* 身份ID */
        public OplogPoiModelBuilder roleId(String roleId) {
            this.roleId = roleId;
            return this;
        }

        /* 身份名称 */
        public OplogPoiModelBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        /* 省代码 */
        public OplogPoiModelBuilder provId(String provId) {
            this.provId = provId;
            return this;
        }

        /* 省名称 */
        public OplogPoiModelBuilder provTitle(String provTitle) {
            this.provTitle = provTitle;
            return this;
        }

        /* 市/州代码 */
        public OplogPoiModelBuilder cityId(String cityId) {
            this.cityId = cityId;
            return this;
        }

        /* 市/州名称 */
        public OplogPoiModelBuilder cityTitle(String cityTitle) {
            this.cityTitle = cityTitle;
            return this;
        }

        /* 区/县代码 */
        public OplogPoiModelBuilder countyId(String countyId) {
            this.countyId = countyId;
            return this;
        }

        /* 区/县名称 */
        public OplogPoiModelBuilder countyTitle(String countyTitle) {
            this.countyTitle = countyTitle;
            return this;
        }

        /* 部门ID */
        public OplogPoiModelBuilder deptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        /* 部门名称 */
        public OplogPoiModelBuilder deptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
            return this;
        }

        /* 部门等级 */
        public OplogPoiModelBuilder deptLevel(String deptLevel) {
            this.deptLevel = deptLevel;
            return this;
        }

        /* 上级部门代码 */
        public OplogPoiModelBuilder deptParentId(String deptParentId) {
            this.deptParentId = deptParentId;
            return this;
        }

        /* 消息表ID */
        public OplogPoiModelBuilder msgId(String msgId) {
            this.msgId = msgId;
            return this;
        }

        /* 功能ID/菜单ID(备用) */
        public OplogPoiModelBuilder funcId(String funcId) {
            this.funcId = funcId;
            return this;
        }

        /* 功能名称(备用) */
        public OplogPoiModelBuilder funcTitle(String funcTitle) {
            this.funcTitle = funcTitle;
            return this;
        }

        /* 操作IP地址 */
        public OplogPoiModelBuilder hostIp(String hostIp) {
            this.hostIp = hostIp;
            return this;
        }

        /* 操作地址 */
        public OplogPoiModelBuilder hostAddress(String hostAddress) {
            this.hostAddress = hostAddress;
            return this;
        }

        /* 操作系统 */
        public OplogPoiModelBuilder hostOs(String hostOs) {
            this.hostOs = hostOs;
            return this;
        }

        /* 操作浏览器 */
        public OplogPoiModelBuilder hostBrowser(String hostBrowser) {
            this.hostBrowser = hostBrowser;
            return this;
        }

        /* 用户代理 */
        public OplogPoiModelBuilder hostUserAgent(String hostUserAgent) {
            this.hostUserAgent = hostUserAgent;
            return this;
        }

        /* 请求ID */
        public OplogPoiModelBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        /* 请求URI */
        public OplogPoiModelBuilder requestUri(String requestUri) {
            this.requestUri = requestUri;
            return this;
        }

        /* 请求方式: POST, PUT, GET, DELETE */
        public OplogPoiModelBuilder requestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        /* 请求参数 */
        public OplogPoiModelBuilder requestParams(String requestParams) {
            this.requestParams = requestParams;
            return this;
        }

        /* 数据总量 */
        public OplogPoiModelBuilder dataTotal(String dataTotal) {
            this.dataTotal = dataTotal;
            return this;
        }

        /* 导入文件名称 */
        public OplogPoiModelBuilder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        /* 文件地址 */
        public OplogPoiModelBuilder fileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        /* 文件大小 */
        public OplogPoiModelBuilder fileSize(String fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        /* 文件数量 */
        public OplogPoiModelBuilder fileTotal(String fileTotal) {
            this.fileTotal = fileTotal;
            return this;
        }

        /* 异常信息 */
        public OplogPoiModelBuilder exInfo(String exInfo) {
            this.exInfo = exInfo;
            return this;
        }

        /* 备注 */
        public OplogPoiModelBuilder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        /* 服务端处理耗时 */
        public OplogPoiModelBuilder executeTime(String executeTime) {
            this.executeTime = executeTime;
            return this;
        }

        public OplogPoiModel build() {
            return new OplogPoiModel(id,
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
                    msgId,
                    funcId,
                    funcTitle,
                    hostIp,
                    hostAddress,
                    hostOs,
                    hostBrowser,
                    hostUserAgent,
                    requestId,
                    requestUri,
                    requestMethod,
                    requestParams,
                    dataTotal,
                    fileName,
                    fileUrl,
                    fileSize,
                    fileTotal,
                    exInfo,
                    remarks,
                    executeTime
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

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncTitle(String funcTitle) {
        this.funcTitle = funcTitle;
    }

    public String getFuncTitle() {
        return funcTitle;
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

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setDataTotal(String dataTotal) {
        this.dataTotal = dataTotal;
    }

    public String getDataTotal() {
        return dataTotal;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
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

    public void setFileTotal(String fileTotal) {
        this.fileTotal = fileTotal;
    }

    public String getFileTotal() {
        return fileTotal;
    }

    public void setExInfo(String exInfo) {
        this.exInfo = exInfo;
    }

    public String getExInfo() {
        return exInfo;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteTime() {
        return executeTime;
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
                .append("msgId", getMsgId())
                .append("funcId", getFuncId())
                .append("funcTitle", getFuncTitle())
                .append("hostIp", getHostIp())
                .append("hostAddress", getHostAddress())
                .append("hostOs", getHostOs())
                .append("hostBrowser", getHostBrowser())
                .append("hostUserAgent", getHostUserAgent())
                .append("requestId", getRequestId())
                .append("requestUri", getRequestUri())
                .append("requestMethod", getRequestMethod())
                .append("requestParams", getRequestParams())
                .append("dataTotal", getDataTotal())
                .append("fileName", getFileName())
                .append("fileUrl", getFileUrl())
                .append("fileSize", getFileSize())
                .append("fileTotal", getFileTotal())
                .append("exInfo", getExInfo())
                .append("remarks", getRemarks())
                .append("executeTime", getExecuteTime())
                .toString();
    }

}

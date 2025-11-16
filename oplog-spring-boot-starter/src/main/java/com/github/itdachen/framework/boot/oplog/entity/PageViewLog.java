package com.github.itdachen.framework.boot.oplog.entity;

import com.github.itdachen.framework.context.BizContextHandler;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 页面访问日志
 *
 * @author 剑鸣秋朔
 * @date 2025/9/9 22:03
 */
public class PageViewLog implements Serializable {
    private static final long serialVersionUID = 8287074742255609519L;

    /**
     * 主键
     */
    private String id = "-";

    /**
     * 平台ID
     */
    private String platId = "-";

    /**
     * 平台名称
     */
    private String platTitle = "-";

    /**
     * 应用ID
     */
    private String appId = "-";

    /**
     * 应用名称
     */
    private String appTitle = "-";

    /**
     * 应用版本号
     */
    private String appVersion = "-";

    /**
     * 租户ID/公司ID
     */
    private String tenantId = "-";

    /**
     * 租户名称/公司名称
     */
    private String tenantTitle = "-";

    /**
     * 省代码
     */
    private String provCode = "-";

    /**
     * 省名称
     */
    private String provName = "-";

    /**
     * 市/州代码
     */
    private String cityCode = "-";

    /**
     * 市州名称
     */
    private String cityName = "-";

    /**
     * 区/县代码
     */
    private String countyCode = "-";

    /**
     * 区县名称
     */
    private String countyName = "-";

    /**
     * 乡镇/街道办代码
     */
    private String townCode = "-";

    /**
     * 乡镇/街道办名称
     */
    private String townName = "-";

    /**
     * 部门ID
     */
    private String deptId = "-";

    /**
     * 部门名称
     */
    private String deptTitle = "-";

    /**
     * 部门等级
     */
    private String deptLevel = "-";

    /**
     * 上级部门代码
     */
    private String deptParentId = "-";

    /**
     * 操作人ID
     */
    private String userId = BizContextHandler.getUserId();

    /**
     * 操作人姓名
     */
    private String nickName = BizContextHandler.getNickName();

    /**
     * 菜单名称
     */
    private String menuTitle = "-";

    /**
     * 菜单ID
     */
    private String menuId = "-";

    /**
     * 操作类型: SAVE,UPDATE,JUMP,REMOVE
     */
    private String opCode = "-";

    /**
     * 操作类型: 新增, 修改, 删除, 查看
     */
    private String opTitle = "-";

    /**
     * 版本号
     */
    private String opVersion = "-";

    /**
     * 操作时间
     */
    private LocalDateTime opTime = LocalDateTime.now();

    /**
     * 操作IP地址
     */
    private String hostIp = "-";

    /**
     * 操作地址
     */
    private String hostAddress = "-";

    /**
     * 操作系统
     */
    private String hostOs = "-";

    /**
     * 操作浏览器
     */
    private String hostBrowser = "-";

    /**
     * 用户代理
     */
    private String userAgent = "-";

    /**
     * 请求ID
     */
    private String requestId = "-";

    /**
     * 请求URI
     */
    private String requestUri = "-";

    /**
     * 请求方式: POST, PUT, GET, DELETE
     */
    private String requestMethod = "-";

    /**
     * 异常信息
     */
    private String exInfo = "-";

    /**
     * 备注
     */
    private String remarks = "-";

    /**
     * 服务端处理耗时
     */
    private String executeTime = "-";


    public PageViewLog() {
    }

    public PageViewLog(String id, String platId, String platTitle, String appId, String appTitle, String appVersion, String tenantId, String tenantTitle, String provCode, String provName, String cityCode, String cityName, String countyCode, String countyName, String townCode, String townName, String deptId, String deptTitle, String deptLevel, String deptParentId, String userId, String nickName, String menuTitle, String menuId, String opCode, String opTitle, String opVersion, LocalDateTime opTime, String hostIp, String hostAddress, String hostOs, String hostBrowser, String userAgent, String requestId, String requestUri, String requestMethod, String exInfo, String remarks, String executeTime) {
        this.id = id;
        this.platId = platId;
        this.platTitle = platTitle;
        this.appId = appId;
        this.appTitle = appTitle;
        this.appVersion = appVersion;
        this.tenantId = tenantId;
        this.tenantTitle = tenantTitle;
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
        this.deptLevel = deptLevel;
        this.deptParentId = deptParentId;
        this.userId = userId;
        this.nickName = nickName;
        this.menuTitle = menuTitle;
        this.menuId = menuId;
        this.opCode = opCode;
        this.opTitle = opTitle;
        this.opVersion = opVersion;
        this.opTime = opTime;
        this.hostIp = hostIp;
        this.hostAddress = hostAddress;
        this.hostOs = hostOs;
        this.hostBrowser = hostBrowser;
        this.userAgent = userAgent;
        this.requestId = requestId;
        this.requestUri = requestUri;
        this.requestMethod = requestMethod;
        this.exInfo = exInfo;
        this.remarks = remarks;
        this.executeTime = executeTime;
    }

    public PageViewLogBuilder builder() {
        return new PageViewLogBuilder();
    }

    public static class PageViewLogBuilder {
        private String id;
        private String platId = BizContextHandler.getPlatId();
        private String platTitle = BizContextHandler.getPlatName();
        private String appId = BizContextHandler.getAppId();
        private String appTitle = BizContextHandler.getAppName();
        private String appVersion = BizContextHandler.getAppVersion();
        private String tenantId = BizContextHandler.getTenantId();
        private String tenantTitle = BizContextHandler.getTenantTitle();
        private String provCode = BizContextHandler.getProvCode();
        private String provName = BizContextHandler.getProvName();
        private String cityCode = BizContextHandler.getCityCode();
        private String cityName = BizContextHandler.getCityName();
        private String countyCode = BizContextHandler.getCountyCode();
        private String countyName = BizContextHandler.getCountyName();
        private String townCode = BizContextHandler.getTownCode();
        private String townName = BizContextHandler.getTownName();
        private String deptId = BizContextHandler.getDeptId();
        private String deptTitle = BizContextHandler.getDeptTitle();
        private String deptLevel = BizContextHandler.getDeptLevel();
        private String deptParentId = BizContextHandler.getDeptParentId();
        private String userId = BizContextHandler.getUserId();
        private String nickName = BizContextHandler.getNickName();
        private String menuTitle = "-";
        private String menuId = "-";
        private String opCode = "-";
        private String opTitle = "-";
        private String opVersion = "-";
        private LocalDateTime opTime = LocalDateTime.now();
        private String hostIp = "-";
        private String hostAddress = "-";
        private String hostOs = "-";
        private String hostBrowser = "-";
        private String userAgent = "-";
        private String requestId = "-";
        private String requestUri = "-";
        private String requestMethod = "-";
        private String exInfo = "-";
        private String remarks = "-";
        private String executeTime = "-";

        public PageViewLogBuilder() {
        }

        /* 主键 */
        public PageViewLogBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 平台ID */
        public PageViewLogBuilder platId(String platId) {
            this.platId = platId;
            return this;
        }

        /* 平台名称 */
        public PageViewLogBuilder platTitle(String platTitle) {
            this.platTitle = platTitle;
            return this;
        }

        /* 应用ID */
        public PageViewLogBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        /* 应用名称 */
        public PageViewLogBuilder appTitle(String appTitle) {
            this.appTitle = appTitle;
            return this;
        }

        /* 应用版本号 */
        public PageViewLogBuilder appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        /* 租户ID/公司ID */
        public PageViewLogBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 租户名称/公司名称 */
        public PageViewLogBuilder tenantTitle(String tenantTitle) {
            this.tenantTitle = tenantTitle;
            return this;
        }

        /* 省代码 */
        public PageViewLogBuilder provCode(String provCode) {
            this.provCode = provCode;
            return this;
        }

        /* 省名称 */
        public PageViewLogBuilder provName(String provName) {
            this.provName = provName;
            return this;
        }

        /* 市/州代码 */
        public PageViewLogBuilder cityCode(String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        /* 市州名称 */
        public PageViewLogBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        /* 区/县代码 */
        public PageViewLogBuilder countyCode(String countyCode) {
            this.countyCode = countyCode;
            return this;
        }

        /* 区县名称 */
        public PageViewLogBuilder countyName(String countyName) {
            this.countyName = countyName;
            return this;
        }

        /* 乡镇/街道办代码 */
        public PageViewLogBuilder townCode(String townCode) {
            this.townCode = townCode;
            return this;
        }

        /* 乡镇/街道办名称 */
        public PageViewLogBuilder townName(String townName) {
            this.townName = townName;
            return this;
        }

        /* 部门ID */
        public PageViewLogBuilder deptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        /* 部门名称 */
        public PageViewLogBuilder deptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
            return this;
        }

        /* 部门等级 */
        public PageViewLogBuilder deptLevel(String deptLevel) {
            this.deptLevel = deptLevel;
            return this;
        }

        /* 上级部门代码 */
        public PageViewLogBuilder deptParentId(String deptParentId) {
            this.deptParentId = deptParentId;
            return this;
        }

        /* 操作人ID */
        public PageViewLogBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        /* 操作人姓名 */
        public PageViewLogBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        /* 菜单名称 */
        public PageViewLogBuilder menuTitle(String menuTitle) {
            this.menuTitle = menuTitle;
            return this;
        }

        /* 菜单ID */
        public PageViewLogBuilder menuId(String menuId) {
            this.menuId = menuId;
            return this;
        }

        /* 操作类型: SAVE,UPDATE,JUMP,REMOVE */
        public PageViewLogBuilder opCode(String opCode) {
            this.opCode = opCode;
            return this;
        }

        /* 操作类型: 新增, 修改, 删除, 查看 */
        public PageViewLogBuilder opTitle(String opTitle) {
            this.opTitle = opTitle;
            return this;
        }

        /* 版本号 */
        public PageViewLogBuilder opVersion(String opVersion) {
            this.opVersion = opVersion;
            return this;
        }

        /* 操作时间 */
        public PageViewLogBuilder opTime(LocalDateTime opTime) {
            this.opTime = opTime;
            return this;
        }

        /* 操作IP地址 */
        public PageViewLogBuilder hostIp(String hostIp) {
            this.hostIp = hostIp;
            return this;
        }

        /* 操作地址 */
        public PageViewLogBuilder hostAddress(String hostAddress) {
            this.hostAddress = hostAddress;
            return this;
        }

        /* 操作系统 */
        public PageViewLogBuilder hostOs(String hostOs) {
            this.hostOs = hostOs;
            return this;
        }

        /* 操作浏览器 */
        public PageViewLogBuilder hostBrowser(String hostBrowser) {
            this.hostBrowser = hostBrowser;
            return this;
        }

        /* 用户代理 */
        public PageViewLogBuilder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /* 请求ID */
        public PageViewLogBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        /* 请求URI */
        public PageViewLogBuilder requestUri(String requestUri) {
            this.requestUri = requestUri;
            return this;
        }

        /* 请求方式: POST, PUT, GET, DELETE */
        public PageViewLogBuilder requestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        /* 异常信息 */
        public PageViewLogBuilder exInfo(String exInfo) {
            this.exInfo = exInfo;
            return this;
        }

        /* 备注 */
        public PageViewLogBuilder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        /* 服务端处理耗时 */
        public PageViewLogBuilder executeTime(String executeTime) {
            this.executeTime = executeTime;
            return this;
        }

        public PageViewLog build() {
            return new PageViewLog(id,
                    platId,
                    platTitle,
                    appId,
                    appTitle,
                    appVersion,
                    tenantId,
                    tenantTitle,
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
                    deptLevel,
                    deptParentId,
                    userId,
                    nickName,
                    menuTitle,
                    menuId,
                    opCode,
                    opTitle,
                    opVersion,
                    opTime,
                    hostIp,
                    hostAddress,
                    hostOs,
                    hostBrowser,
                    userAgent,
                    requestId,
                    requestUri,
                    requestMethod,
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

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownName() {
        return townName;
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

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpTitle(String opTitle) {
        this.opTitle = opTitle;
    }

    public String getOpTitle() {
        return opTitle;
    }

    public void setOpVersion(String opVersion) {
        this.opVersion = opVersion;
    }

    public String getOpVersion() {
        return opVersion;
    }

    public void setOpTime(LocalDateTime opTime) {
        this.opTime = opTime;
    }

    public LocalDateTime getOpTime() {
        return opTime;
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

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
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
                .append("platId", getPlatId())
                .append("platTitle", getPlatTitle())
                .append("appId", getAppId())
                .append("appTitle", getAppTitle())
                .append("appVersion", getAppVersion())
                .append("tenantId", getTenantId())
                .append("tenantTitle", getTenantTitle())
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
                .append("deptLevel", getDeptLevel())
                .append("deptParentId", getDeptParentId())
                .append("userId", getUserId())
                .append("nickName", getNickName())
                .append("menuTitle", getMenuTitle())
                .append("menuId", getMenuId())
                .append("opCode", getOpCode())
                .append("opTitle", getOpTitle())
                .append("opVersion", getOpVersion())
                .append("opTime", getOpTime())
                .append("hostIp", getHostIp())
                .append("hostAddress", getHostAddress())
                .append("hostOs", getHostOs())
                .append("hostBrowser", getHostBrowser())
                .append("userAgent", getUserAgent())
                .append("requestId", getRequestId())
                .append("requestUri", getRequestUri())
                .append("requestMethod", getRequestMethod())
                .append("exInfo", getExInfo())
                .append("remarks", getRemarks())
                .append("executeTime", getExecuteTime())
                .toString();
    }

}

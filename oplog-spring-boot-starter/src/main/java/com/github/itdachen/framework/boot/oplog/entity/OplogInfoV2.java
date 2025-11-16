package com.github.itdachen.framework.boot.oplog.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * OplogInfoV2
 *
 * @author 剑鸣秋朔
 * @date 2025-09-09 16:44
 */
@Deprecated
public class OplogInfoV2 implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 日志标题: 用户管理, 菜单管理等
     */
    private String logTitle = "-";

    /**
     * 日志类型: button, uri
     */
    private String logType = "-";

    /**
     * 日志类型: 按钮, 链接
     */
    private String logTypeTitle = "-";

    /**
     * 操作类型: SAVE,UPDATE,JUMP,REMOVE
     */
    private String opType = "-";

    /**
     * 操作类型: 新增, 修改, 删除, 查看
     */
    private String opTitle = "-";

    /**
     * 版本号
     */
    private String opVersion = "-";

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
     * 请求参数
     */
    private String requestParams = "-";

    /**
     * 相应数据
     */
    private String responseSjon = "-";

    /**
     * 相应状态码
     */
    private String responseCode = "-";

    /**
     * 操作状态: 成功, 失败, 异常
     */
    private String responseStatus = "-";

    /**
     * 返回消息
     */
    private String responseMsg = "-";

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

    /**
     * 年份
     */
    private String yearly = "-";

    /**
     * 月份
     */
    private String monthly = "-";


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

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogTypeTitle(String logTypeTitle) {
        this.logTypeTitle = logTypeTitle;
    }

    public String getLogTypeTitle() {
        return logTypeTitle;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpType() {
        return opType;
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

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setResponseSjon(String responseSjon) {
        this.responseSjon = responseSjon;
    }

    public String getResponseSjon() {
        return responseSjon;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
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
                .append("logTitle", getLogTitle())
                .append("logType", getLogType())
                .append("logTypeTitle", getLogTypeTitle())
                .append("opType", getOpType())
                .append("opTitle", getOpTitle())
                .append("opVersion", getOpVersion())
                .append("hostIp", getHostIp())
                .append("hostAddress", getHostAddress())
                .append("hostOs", getHostOs())
                .append("hostBrowser", getHostBrowser())
                .append("userAgent", getUserAgent())
                .append("requestId", getRequestId())
                .append("requestUri", getRequestUri())
                .append("requestMethod", getRequestMethod())
                .append("requestParams", getRequestParams())
                .append("responseSjon", getResponseSjon())
                .append("responseCode", getResponseCode())
                .append("responseStatus", getResponseStatus())
                .append("responseMsg", getResponseMsg())
                .append("exInfo", getExInfo())
                .append("remarks", getRemarks())
                .append("executeTime", getExecuteTime())
                .append("yearly", getYearly())
                .append("monthly", getMonthly())
                .toString();
    }

}

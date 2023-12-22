package com.github.itdachen.boot.oplog.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description: 操作日志
 * Created by 王大宸 on 2022/08/27 21:12
 * Created with IntelliJ IDEA.
 */
public class OplogClient implements Serializable {
    private static final long serialVersionUID = 5196495926190816449L;
    /**
     * 主键唯一标识
     */
    private String id;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 服务ID
     */
    private String serviceId;


    /**
     * 客户端id: PC端, 微程序, APP等
     */
    private String clientId;


    /**
     * http 请求id
     */
    private String requestId;


    /**
     * 日志标题: 用户管理, 菜单管理等
     */
    private String menuTitle;


    /**
     * 操作类型: 新增, 修改, 删除, 查看
     */
    private String makeUseType;


    /**
     * 日志类型: button-按钮, uri-链接
     */
    private String logType;


    /**
     * 操作IP地址
     */
    private String makeUseIp;


    /**
     * 操作地址
     */
    private String makeUseAddress;


    /**
     * 操作状态: 成功, 失败, 异常
     */
    private String makeUseStatus;


    /**
     * 用户代理
     */
    private String userAgent;


    /**
     * 请求URI
     */
    private String requestUri;


    /**
     * 操作方式
     */
    private String requestMethod;


    /**
     * 操作提交的数据
     */
    private String params;


    /**
     * 返回数据
     */
    private String jsonResult;


    /**
     * 删除标记
     */
    private String delFlag;


    /**
     * 异常信息
     */
    private String exception;


    /**
     * 备注
     */
    private String remarks;


    /**
     * 服务端处理耗时
     */
    private String executeTime;


    /**
     * 返回消息
     */
    private String msg;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 创建人
     */
    private String createUser;


    /**
     * 创建人id
     */
    private String createUserId;


    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    /**
     * 更新人
     */
    private String updateUser;


    /**
     * 更新人id
     */
    private String updateUserId;


    public OplogClient() {
    }

    public OplogClient(String id, String tenantId, String serviceId, String clientId, String requestId, String menuTitle, String makeUseType, String logType, String makeUseIp, String makeUseAddress, String makeUseStatus, String userAgent, String requestUri, String requestMethod, String params, String jsonResult, String delFlag, String exception, String remarks, String executeTime, String msg, LocalDateTime createTime, String createUser, String createUserId, LocalDateTime updateTime, String updateUser, String updateUserId) {
        this.id = id;
        this.tenantId = tenantId;
        this.serviceId = serviceId;
        this.clientId = clientId;
        this.requestId = requestId;
        this.menuTitle = menuTitle;
        this.makeUseType = makeUseType;
        this.logType = logType;
        this.makeUseIp = makeUseIp;
        this.makeUseAddress = makeUseAddress;
        this.makeUseStatus = makeUseStatus;
        this.userAgent = userAgent;
        this.requestUri = requestUri;
        this.requestMethod = requestMethod;
        this.params = params;
        this.jsonResult = jsonResult;
        this.delFlag = delFlag;
        this.exception = exception;
        this.remarks = remarks;
        this.executeTime = executeTime;
        this.msg = msg;
        this.createTime = createTime;
        this.createUser = createUser;
        this.createUserId = createUserId;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.updateUserId = updateUserId;
    }

    public static ApiLogClientBuilder builder() {
        return new ApiLogClientBuilder();
    }

    public static class ApiLogClientBuilder {
        private String id;
        private String tenantId;
        private String serviceId;
        private String clientId;
        private String requestId;
        private String menuTitle;
        private String makeUseType;
        private String logType;
        private String makeUseIp;
        private String makeUseAddress;
        private String makeUseStatus;
        private String userAgent;
        private String requestUri;
        private String requestMethod;
        private String params;
        private String jsonResult;
        private String delFlag;
        private String exception;
        private String remarks;
        private String executeTime;
        private String msg;
        private LocalDateTime createTime;
        private String createUser;
        private String createUserId;
        private LocalDateTime updateTime;
        private String updateUser;
        private String updateUserId;

        public ApiLogClientBuilder() {
        }

        /* 主键唯一标识 */
        public ApiLogClientBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 租户id */
        public ApiLogClientBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 服务ID */
        public ApiLogClientBuilder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        /* 客户端id: PC端, 微程序, APP等  */
        public ApiLogClientBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        /* http 请求id */
        public ApiLogClientBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        /* 日志标题: 用户管理, 菜单管理等 */
        public ApiLogClientBuilder menuTitle(String menuTitle) {
            this.menuTitle = menuTitle;
            return this;
        }

        /* 操作类型: 新增, 修改, 删除, 查看 */
        public ApiLogClientBuilder makeUseType(String makeUseType) {
            this.makeUseType = makeUseType;
            return this;
        }

        /* 日志类型: button-按钮, uri-链接 */
        public ApiLogClientBuilder logType(String logType) {
            this.logType = logType;
            return this;
        }

        /* 操作IP地址 */
        public ApiLogClientBuilder makeUseIp(String makeUseIp) {
            this.makeUseIp = makeUseIp;
            return this;
        }

        /* 操作地址 */
        public ApiLogClientBuilder makeUseAddress(String makeUseAddress) {
            this.makeUseAddress = makeUseAddress;
            return this;
        }

        /* 操作状态: 成功, 失败, 异常 */
        public ApiLogClientBuilder makeUseStatus(String makeUseStatus) {
            this.makeUseStatus = makeUseStatus;
            return this;
        }

        /* 用户代理 */
        public ApiLogClientBuilder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /* 请求URI */
        public ApiLogClientBuilder requestUri(String requestUri) {
            this.requestUri = requestUri;
            return this;
        }

        /* 操作方式 */
        public ApiLogClientBuilder requestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        /* 操作提交的数据 */
        public ApiLogClientBuilder params(String params) {
            this.params = params;
            return this;
        }

        /* 返回数据 */
        public ApiLogClientBuilder jsonResult(String jsonResult) {
            this.jsonResult = jsonResult;
            return this;
        }

        /* 删除标记 */
        public ApiLogClientBuilder delFlag(String delFlag) {
            this.delFlag = delFlag;
            return this;
        }

        /* 异常信息 */
        public ApiLogClientBuilder exception(String exception) {
            this.exception = exception;
            return this;
        }

        /* 备注 */
        public ApiLogClientBuilder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        /* 服务端处理耗时 */
        public ApiLogClientBuilder executeTime(String executeTime) {
            this.executeTime = executeTime;
            return this;
        }

        /* 返回消息 */
        public ApiLogClientBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        /* 创建时间 */
        public ApiLogClientBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        /* 创建人 */
        public ApiLogClientBuilder createUser(String createUser) {
            this.createUser = createUser;
            return this;
        }

        /* 创建人id */
        public ApiLogClientBuilder createUserId(String createUserId) {
            this.createUserId = createUserId;
            return this;
        }

        /* 更新时间 */
        public ApiLogClientBuilder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        /* 更新人 */
        public ApiLogClientBuilder updateUser(String updateUser) {
            this.updateUser = updateUser;
            return this;
        }

        /* 更新人id */
        public ApiLogClientBuilder updateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
            return this;
        }

        public OplogClient build() {
            return new OplogClient(id, tenantId, serviceId, clientId, requestId, menuTitle, makeUseType, logType, makeUseIp, makeUseAddress, makeUseStatus, userAgent, requestUri, requestMethod, params, jsonResult, delFlag, exception, remarks, executeTime, msg, createTime, createUser, createUserId, updateTime, updateUser, updateUserId);
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

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMakeUseType(String makeUseType) {
        this.makeUseType = makeUseType;
    }

    public String getMakeUseType() {
        return makeUseType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }

    public void setMakeUseIp(String makeUseIp) {
        this.makeUseIp = makeUseIp;
    }

    public String getMakeUseIp() {
        return makeUseIp;
    }

    public void setMakeUseAddress(String makeUseAddress) {
        this.makeUseAddress = makeUseAddress;
    }

    public String getMakeUseAddress() {
        return makeUseAddress;
    }

    public void setMakeUseStatus(String makeUseStatus) {
        this.makeUseStatus = makeUseStatus;
    }

    public String getMakeUseStatus() {
        return makeUseStatus;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
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

    public void setParams(String params) {
        this.params = params;
    }

    public String getParams() {
        return params;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("serviceId", getServiceId())
                .append("clientId", getClientId())
                .append("requestId", getRequestId())
                .append("menuTitle", getMenuTitle())
                .append("makeUseType", getMakeUseType())
                .append("logType", getLogType())
                .append("makeUseIp", getMakeUseIp())
                .append("makeUseAddress", getMakeUseAddress())
                .append("makeUseStatus", getMakeUseStatus())
                .append("userAgent", getUserAgent())
                .append("requestUri", getRequestUri())
                .append("requestMethod", getRequestMethod())
                .append("params", getParams())
                .append("jsonResult", getJsonResult())
                .append("delFlag", getDelFlag())
                .append("exception", getException())
                .append("remarks", getRemarks())
                .append("executeTime", getExecuteTime())
                .append("msg", getMsg())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("createUserId", getCreateUserId())
                .append("updateTime", getUpdateTime())
                .append("updateUser", getUpdateUser())
                .append("updateUserId", getUpdateUserId())
                .toString();
    }

}

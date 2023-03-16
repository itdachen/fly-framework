package com.github.itdachen.framework.log.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 * Created by 王大宸 on 2022/08/27 21:12
 * Created with IntelliJ IDEA.
 */
public class ApiLogClient implements Serializable {
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
    private String title;

    /**
     * 日志类型: button-按钮, uri-链接
     */
    private String type;

    /**
     * 操作类型: 新增, 修改, 删除, 查看
     */
    private String remoteType;

    /**
     * 操作IP地址
     */
    private String remoteIp;

    /**
     * 操作地址
     */
    private String remoteAddress;

    /**
     * 操作状态: 成功, 失败, 异常
     */
    private String remoteStatus;

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
    private String method;

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
    private String remark;

    /**
     * 服务端处理耗时
     */
    private String executeTime;

    /**
     * 返回消息: 操作成功, 操作失败等
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

    public ApiLogClient() {
    }

    public ApiLogClient(Builder builder) {
        this.id = builder.id;
        this.tenantId = builder.tenantId;
        this.serviceId = builder.serviceId;
        this.clientId = builder.clientId;
        this.requestId = builder.requestId;
        this.title = builder.title;
        this.type = builder.type;
        this.remoteType = builder.remoteType;
        this.remoteIp = builder.remoteIp;
        this.remoteAddress = builder.remoteAddress;
        this.remoteStatus = builder.remoteStatus;
        this.userAgent = builder.userAgent;
        this.requestUri = builder.requestUri;
        this.method = builder.method;
        this.params = builder.params;
        this.jsonResult = builder.jsonResult;
        this.delFlag = builder.delFlag;
        this.exception = builder.exception;
        this.remark = builder.remark;
        this.executeTime = builder.executeTime;
        this.msg = builder.msg;
        this.createTime = builder.createTime;
        this.createUser = builder.createUser;
        this.createUserId = builder.createUserId;
        this.updateTime = builder.updateTime;
        this.updateUser = builder.updateUser;
        this.updateUserId = builder.updateUserId;
    }

    public static class Builder {
        private String id;
        private String tenantId;
        private String serviceId;
        private String clientId;
        private String requestId;
        private String title;
        private String type;
        private String remoteType;
        private String remoteIp;
        private String remoteAddress;
        private String remoteStatus;
        private String userAgent;
        private String requestUri;
        private String method;
        private String params;
        private String jsonResult;
        private String delFlag;
        private String exception;
        private String remark;
        private String executeTime;
        private String msg;
        private LocalDateTime createTime;
        private String createUser;
        private String createUserId;
        private LocalDateTime updateTime;
        private String updateUser;
        private String updateUserId;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder remoteType(String remoteType) {
            this.remoteType = remoteType;
            return this;
        }

        public Builder remoteIp(String remoteIp) {
            this.remoteIp = remoteIp;
            return this;
        }

        public Builder remoteAddress(String remoteAddress) {
            this.remoteAddress = remoteAddress;
            return this;
        }

        public Builder remoteStatus(String remoteStatus) {
            this.remoteStatus = remoteStatus;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder requestUri(String requestUri) {
            this.requestUri = requestUri;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder params(String params) {
            this.params = params;
            return this;
        }

        public Builder jsonResult(String jsonResult) {
            this.jsonResult = jsonResult;
            return this;
        }

        public Builder delFlag(String delFlag) {
            this.delFlag = delFlag;
            return this;
        }

        public Builder exception(String exception) {
            this.exception = exception;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public Builder executeTime(String executeTime) {
            this.executeTime = executeTime;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder createUser(String createUser) {
            this.createUser = createUser;
            return this;
        }

        public Builder createUserId(String createUserId) {
            this.createUserId = createUserId;
            return this;
        }

        public Builder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Builder updateUser(String updateUser) {
            this.updateUser = updateUser;
            return this;
        }

        public Builder updateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
            return this;
        }

        public ApiLogClient build() {
            return new ApiLogClient(this);
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRemoteType(String remoteType) {
        this.remoteType = remoteType;
    }

    public String getRemoteType() {
        return remoteType;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteStatus(String remoteStatus) {
        this.remoteStatus = remoteStatus;
    }

    public String getRemoteStatus() {
        return remoteStatus;
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

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
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

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("serviceId", getServiceId())
                .append("clientId", getClientId())
                .append("requestId", getRequestId())
                .append("title", getTitle())
                .append("type", getType())
                .append("remoteType", getRemoteType())
                .append("remoteIp", getRemoteIp())
                .append("remoteAddress", getRemoteAddress())
                .append("remoteStatus", getRemoteStatus())
                .append("userAgent", getUserAgent())
                .append("requestUri", getRequestUri())
                .append("method", getMethod())
                .append("params", getParams())
                .append("jsonResult", getJsonResult())
                .append("delFlag", getDelFlag())
                .append("exception", getException())
                .append("remark", getRemark())
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

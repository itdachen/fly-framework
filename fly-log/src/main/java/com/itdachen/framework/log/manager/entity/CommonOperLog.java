package com.itdachen.framework.log.manager.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description: 操作日志统一管理类
 * Created by 王大宸 on 2021-12-01 16:39
 * Created with IntelliJ IDEA.
 */
public class CommonOperLog implements Serializable {
    private static final long serialVersionUID = 1462930294235720571L;

    private String id;


    /**
     * 模块标题
     */
    private String title;


    /**
     * 操作客户端
     */
    private String clientId;


    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private String businessType;


    /**
     * 方法名称
     */
    private String method;


    /**
     * 请求方式
     */
    private String requestMethod;


    /**
     * 请求URL
     */
    private String operUrl;


    /**
     * 主机地址
     */
    private String operIp;


    /**
     * 操作地点
     */
    private String operLocation;


    /**
     * 请求参数
     */
    private String operParam;


    /**
     * 返回参数
     */
    private String jsonResult;


    /**
     * 操作状态（0正常 1异常）
     */
    private String status;


    /**
     * 错误消息
     */
    private String errorMsg;


    /**
     * 操作时间
     */
    private LocalDateTime createTime;


    /**
     * 操作人
     */
    private String createUser;


    /**
     * 操作人id
     */
    private String createUserId;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    public String getOperLocation() {
        return operLocation;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("clientId", getClientId())
                .append("businessType", getBusinessType())
                .append("method", getMethod())
                .append("requestMethod", getRequestMethod())
                .append("operUrl", getOperUrl())
                .append("operIp", getOperIp())
                .append("operLocation", getOperLocation())
                .append("operParam", getOperParam())
                .append("jsonResult", getJsonResult())
                .append("status", getStatus())
                .append("errorMsg", getErrorMsg())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("createUserId", getCreateUserId())
                .toString();
    }


}

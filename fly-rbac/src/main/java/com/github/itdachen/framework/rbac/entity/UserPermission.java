package com.github.itdachen.framework.rbac.entity;

import java.io.Serializable;

/**
 * Description: 权限校验参数
 * Created by 王大宸 on 2023/05/06 17:20
 * Created with IntelliJ IDEA.
 */
public class UserPermission implements Serializable {
    private static final long serialVersionUID = 9121270258166152467L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户身份ID
     */
    private String identityId;

    /**
     * 岗位ID
     */
    private String jobId;

    /**
     * 访问IP
     */
    private String hostIp;

    /**
     * 请求地址
     */
    private String requestUri;

    /**
     * 请求方式: POST,GET,PUT,DELETE
     */
    private String requestMethod;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}

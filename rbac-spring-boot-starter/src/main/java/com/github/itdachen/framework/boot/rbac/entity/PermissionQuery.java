package com.github.itdachen.framework.boot.rbac.entity;

import java.io.Serializable;

/**
 * Description: 权限查询参数
 * Created by 剑鸣秋朔 on 2023/05/06 17:19
 * Created with IntelliJ IDEA.
 */
public class PermissionQuery implements Serializable {
    private static final long serialVersionUID = 2886612401065015443L;

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
}

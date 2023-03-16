package com.github.itdachen.framework.context.permission;

import java.io.Serializable;

/**
 * Description:
 * Created by 王大宸 on 2022-12-13 11:28
 * Created with IntelliJ IDEA.
 */
public class CheckPermissionInfo implements Serializable {
    private static final long serialVersionUID = 2793041525021259426L;

    // 请求权限资源
    private PermissionInfo permissionInfo;

    // 是否有权限
    private Boolean isAuth = true;


    public PermissionInfo getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(PermissionInfo permissionInfo) {
        this.permissionInfo = permissionInfo;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean auth) {
        isAuth = auth;
    }

}

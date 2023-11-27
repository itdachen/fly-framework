package com.github.itdachen.framework.security.rbac;

import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/***
 * 初始化系统鉴权
 *
 * @author 王大宸
 * @date 2023/11/27 20:25
 */
public interface IRequestAuthorizationHandler {

    List<PermissionInfo> init();


}

package com.github.itdachen.framework.boot.security.rbac;

import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/***
 * 初始化系统鉴权
 *
 * @author 剑鸣秋朔
 * @date 2023/11/27 20:25
 */
public interface IRequestAuthorizationHandler {

    List<PermissionInfo> init();


}

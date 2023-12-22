package com.github.itdachen.boot.security.rbac;

import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.ArrayList;
import java.util.List;

/***
 * 默认加载系统权限
 *
 * @author 王大宸
 * @date 2023/11/27 20:31
 */
public class DefaultRequestAuthorizationHandler implements IRequestAuthorizationHandler {

    @Override
    public List<PermissionInfo> init() {
        return new ArrayList<>();
    }

}

package com.github.itdachen.boot.rbac.jdbc;

import com.github.itdachen.boot.rbac.constants.ElementTypeConstant;
import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/**
 * Description: 菜单权限处理
 * Created by 王大宸 on 2022-12-13 15:16
 * Created with IntelliJ IDEA.
 */
public class HandlerMenuPermission {

    public static List<PermissionInfo> handlerMenu(List<PermissionInfo> list) {
        for (PermissionInfo info : list) {
            info.setType(ElementTypeConstant.RESOURCE_TYPE_MENU);
            info.setName(ElementTypeConstant.RESOURCE_ACTION_VISIT);
            if (!info.getUri().startsWith("/")) {
                info.setUri("/" + info.getUri());
            }
            info.setMethod(ElementTypeConstant.RESOURCE_REQUEST_METHOD_GET);
        }
        return list;
    }

}

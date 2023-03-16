package com.github.itdachen.framework.rbac.jdbc;

import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/**
 * Description: 从数据库获取权限抽象类 METHOD_GET
 * Created by 王大宸 on 2022-12-13 14:51
 * Created with IntelliJ IDEA.
 */
public abstract class AbstractPermissionJdbcService implements IPermissionJdbcService {

    /***
     * 查询所有权限接口
     *
     * @author 王大宸
     * @date 2022/12/13 15:09
     * @return java.util.List<cn.edu.hubu.framework.context.permission.PermissionInfo>
     */
    @Override
    public List<PermissionInfo> findAllPermission() throws Exception {
        List<PermissionInfo> permissionInfos = HandlerMenuPermission.handlerMenu(findMenuPermission());
        permissionInfos.addAll(findElementPermission());
        return permissionInfos;
    }

    /***
     * 菜单查询接口
     *
     * @author 王大宸
     * @date 2022/12/13 15:07
     * @return java.util.List<cn.edu.hubu.framework.context.permission.PermissionInfo>
     */
    protected abstract List<PermissionInfo> findMenuPermission();

    /***
     * 查询所有的按钮权限接口
     *
     * @author 王大宸
     * @date 2022/12/13 15:08
     * @return java.util.List<cn.edu.hubu.framework.context.permission.PermissionInfo>
     */
    protected abstract List<PermissionInfo> findElementPermission();


}

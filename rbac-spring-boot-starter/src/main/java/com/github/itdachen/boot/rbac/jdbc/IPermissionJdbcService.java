package com.github.itdachen.boot.rbac.jdbc;


import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/**
 * Description: 所有权限持久化(从数据库查询权限)
 * Created by 王大宸 on 2022-12-13 14:30
 * Created with IntelliJ IDEA.
 */
public interface IPermissionJdbcService {

    /***
     * 获取所有的权限信息
     *
     * @author 王大宸
     * @date 2023/4/11 21:13
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    List<PermissionInfo> findAllPermission() throws Exception;



}

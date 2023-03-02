package com.itdachen.framework.rbac.jdbc;


import com.itdachen.framework.context.permission.PermissionInfo;

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
     * @date 2022/12/13 14:09
     * @return java.util.List<cn.edu.hubu.framework.context.permission.PermissionInfo>
     */
    List<PermissionInfo> findAllPermission() throws Exception;



}

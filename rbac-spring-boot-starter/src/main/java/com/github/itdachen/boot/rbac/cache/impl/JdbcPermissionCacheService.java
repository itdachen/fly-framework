package com.github.itdachen.boot.rbac.cache.impl;

import com.github.itdachen.boot.rbac.cache.IPermissionCacheService;
import com.github.itdachen.boot.rbac.jdbc.IPermissionJdbcService;
import com.github.itdachen.boot.rbac.jdbc.IPermissionUserJdbcService;
import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-12-13 14:33
 * Created with IntelliJ IDEA.
 */
public class JdbcPermissionCacheService implements IPermissionCacheService {

    private final IPermissionJdbcService permissionJdbcService;
    private final IPermissionUserJdbcService permissionUserJdbcService;

    public JdbcPermissionCacheService(IPermissionJdbcService permissionJdbcService,
                                      IPermissionUserJdbcService permissionUserJdbcService) {
        this.permissionJdbcService = permissionJdbcService;
        this.permissionUserJdbcService = permissionUserJdbcService;
    }

    @Override
    public List<PermissionInfo> findAllPermission() throws Exception {
        return permissionJdbcService.findAllPermission();
    }

    @Override
    public List<PermissionInfo> findUserJobPermission(String userId, String hostIp, String jobId) throws Exception {
        return permissionUserJdbcService.findUserJobPermission(userId, hostIp, jobId);
    }

    @Override
    public List<PermissionInfo> findUserPermission(String userId, String hostIp) throws Exception {
        return permissionUserJdbcService.findUserPermission(userId, hostIp);
    }
}

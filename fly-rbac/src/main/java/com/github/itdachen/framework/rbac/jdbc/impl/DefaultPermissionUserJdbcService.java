package com.github.itdachen.framework.rbac.jdbc.impl;


import com.github.itdachen.framework.context.permission.PermissionInfo;
import com.github.itdachen.framework.rbac.jdbc.AbstractPermissionUserJdbcService;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-12-13 15:19
 * Created with IntelliJ IDEA.
 */
public class DefaultPermissionUserJdbcService extends AbstractPermissionUserJdbcService {

    @Override
    protected List<PermissionInfo> findUserJobMenuPermission(String userId,
                                                             String hostIp,
                                                             String jobId) throws Exception {
        return new ArrayList<>();
    }

    @Override
    protected List<PermissionInfo> findUserJobElementPermission(String userId,
                                                                String hostIp,
                                                                String jobId) throws Exception {
        return new ArrayList<>();
    }

    @Override
    protected List<PermissionInfo> findUserMenuPermission(String userId,
                                                          String hostIp) throws Exception {
        return new ArrayList<>();
    }

    @Override
    protected List<PermissionInfo> findUserElementPermission(String userId,
                                                             String hostIp) throws Exception {
        return new ArrayList<>();
    }

}

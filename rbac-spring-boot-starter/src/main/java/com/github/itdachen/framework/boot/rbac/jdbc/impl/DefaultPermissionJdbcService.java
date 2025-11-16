package com.github.itdachen.framework.boot.rbac.jdbc.impl;

import com.github.itdachen.framework.boot.rbac.jdbc.AbstractPermissionJdbcService;
import com.github.itdachen.framework.context.permission.PermissionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 查询权限默认接口
 * Created by 剑鸣秋朔 on 2022-12-13 14:34
 * Created with IntelliJ IDEA.
 */
public class DefaultPermissionJdbcService extends AbstractPermissionJdbcService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPermissionJdbcService.class);

    @Override
    protected List<PermissionInfo> findMenuPermission() {
        return new ArrayList<>();
    }

    @Override
    protected List<PermissionInfo> findElementPermission() {
        return new ArrayList<>();
    }


}

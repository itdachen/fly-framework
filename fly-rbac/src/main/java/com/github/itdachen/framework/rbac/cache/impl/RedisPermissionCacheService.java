package com.github.itdachen.framework.rbac.cache.impl;


import com.alibaba.fastjson2.JSON;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.UserTypeConstant;
import com.github.itdachen.framework.context.permission.PermissionInfo;
import com.github.itdachen.framework.rbac.cache.IPermissionCacheService;
import com.github.itdachen.framework.rbac.constants.RedisKeyConstant;
import com.github.itdachen.framework.rbac.jdbc.IPermissionJdbcService;
import com.github.itdachen.framework.rbac.jdbc.IPermissionUserJdbcService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by 王大宸 on 2022-12-13 14:14
 * Created with IntelliJ IDEA.
 */
public class RedisPermissionCacheService implements IPermissionCacheService {
    private static final Logger logger = LoggerFactory.getLogger(RedisPermissionCacheService.class);
    private final IPermissionJdbcService permissionJdbcService;
    private final IPermissionUserJdbcService permissionUserJdbcService;
    private final StringRedisTemplate stringRedisTemplate;

    public RedisPermissionCacheService(IPermissionJdbcService permissionJdbcService,
                                       StringRedisTemplate stringRedisTemplate,
                                       IPermissionUserJdbcService permissionUserJdbcService) {
        this.permissionJdbcService = permissionJdbcService;
        this.stringRedisTemplate = stringRedisTemplate;
        this.permissionUserJdbcService = permissionUserJdbcService;
    }


    @Override
    public List<PermissionInfo> findAllPermission() throws Exception {
        String key = RedisKeyConstant.REDIS_KEY_ALL_PERMISSIONS;
        // 从 Redis 中获取菜单信息
        String s = stringRedisTemplate.opsForValue().get(key);
        if (s == null || StringUtils.isBlank(s)) {
            List<PermissionInfo> allPermission = permissionJdbcService.findAllPermission();
            s = JSON.toJSONString(allPermission);
            stringRedisTemplate.opsForValue().set(key, s, RedisKeyConstant.SYS_PERMISSIONS_TIME_OUT, TimeUnit.HOURS);
        }
        return JSON.parseArray(s, PermissionInfo.class);
    }

    @Override
    public List<PermissionInfo> findUserJobPermission(String userId, String hostIp, String jobId) throws Exception {
        String key = String.format(RedisKeyConstant.REDIS_KEY_USER_PERMISSIONS, userId);
        String s = stringRedisTemplate.opsForValue().get(key);
        if (s == null || StringUtils.isBlank(s) || "[]".equals(s)) {
            List<PermissionInfo> list = null;
            if (UserTypeConstant.SUPER_ADMINISTRATOR.equals(BizContextHandler.getUserType())
                    || UserTypeConstant.SUPER_ADMINISTRATOR_USERNAME.equals(BizContextHandler.getAccount())) {
                list = permissionJdbcService.findAllPermission();
            } else {
                list = permissionUserJdbcService.findUserJobPermission(userId, hostIp, jobId);
            }
            // 保存20分钟, 20分钟后自动删除
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(list), RedisKeyConstant.USER_PERMISSIONS_TIME_OUT, TimeUnit.MINUTES);
            return list;
        }
        return JSON.parseArray(s, PermissionInfo.class);
    }

    @Override
    public List<PermissionInfo> findUserPermission(String userId, String hostIp) throws Exception {
        String key = String.format(RedisKeyConstant.REDIS_KEY_USER_PERMISSIONS, userId);
        String s = stringRedisTemplate.opsForValue().get(key);
        if (s == null || StringUtils.isBlank(s) || "[]".equals(s)) {
            List<PermissionInfo> list = null;
            if (UserTypeConstant.SUPER_ADMINISTRATOR.equals(BizContextHandler.getUserType())
                    || UserTypeConstant.SUPER_ADMINISTRATOR_USERNAME.equals(BizContextHandler.getAccount())) {
                list = permissionJdbcService.findAllPermission();
            } else {
                permissionUserJdbcService.findUserPermission(userId, hostIp);
            }
            // 保存20分钟, 20分钟后自动删除
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(list), RedisKeyConstant.USER_PERMISSIONS_TIME_OUT, TimeUnit.MINUTES);
            return list;
        }
        return JSON.parseArray(s, PermissionInfo.class);
    }
}

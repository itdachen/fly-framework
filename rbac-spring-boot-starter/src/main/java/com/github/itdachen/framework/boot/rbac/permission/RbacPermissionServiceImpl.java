package com.github.itdachen.framework.boot.rbac.permission;


import com.github.itdachen.framework.boot.rbac.IRbacPermissionService;
import com.github.itdachen.framework.boot.rbac.cache.IPermissionCacheService;
import com.github.itdachen.framework.context.permission.CheckPermissionInfo;
import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Description:
 * Created by 王大宸 on 2022-12-13 11:26
 * Created with IntelliJ IDEA.
 */
public class RbacPermissionServiceImpl implements IRbacPermissionService {
    private final IPermissionCacheService permissionCacheService;

    public RbacPermissionServiceImpl(IPermissionCacheService permissionCacheService) {
        this.permissionCacheService = permissionCacheService;
    }

    /***
     * Rbac 鉴权
     *
     * @author 王大宸
     * @date 2022/12/13 14:42
     * @param userId        当前登录用户ID
     * @param jobId         当前工作岗位
     * @param hostIp        当前登录IP
     * @param requestUri    请求地址
     * @param requestMethod 请求方法
     * @return com.github.itdachen.framework.context.permission.CheckPermissionInfo
     */
    @Override
    public CheckPermissionInfo checkUserPermission(String userId,
                                                   String jobId,
                                                   String hostIp,
                                                   String requestUri,
                                                   String requestMethod) throws Exception {
        CheckPermissionInfo checkPermissionInfo = new CheckPermissionInfo();
        List<PermissionInfo> allPermission = permissionCacheService.findAllPermission();
        // 判断当前访问资源是否有权限控制
        List<PermissionInfo> matchPermission = allPermission.parallelStream().filter(info -> {
            if (info.getUri().indexOf("{") > 0) {
                info.setUri(info.getUri().replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+"));
            }
            return Pattern.compile("^" + info.getUri() + "$").matcher(requestUri).find() && requestMethod.equals(info.getMethod());
        }).collect(Collectors.toList());

        // 说明当前访问资源不做权限控制
        if (0 == matchPermission.size()) {
            checkPermissionInfo.setIsAuth(true);
            return checkPermissionInfo;
        }

        // 判断当前用户是否拥有该访问资源的权限
        List<PermissionInfo> permissions;
        if (null == jobId) {
            permissions = permissionCacheService.findUserPermission(userId, hostIp);
        } else {
            permissions = permissionCacheService.findUserJobPermission(userId, hostIp, jobId);
        }

        PermissionInfo current = null;
        boolean anyMatch;
        for (PermissionInfo info : permissions) {
            anyMatch = matchPermission.parallelStream().anyMatch(permissionInfo ->
                    permissionInfo.getPermission().equals(info.getPermission()));
            if (anyMatch) {
                current = info;
                break;
            }
        }

        // 当前用户不拥有该权限
        if (null == current) {
            checkPermissionInfo.setIsAuth(false);
            return checkPermissionInfo;
        }
        // 当前用户拥有该资源的访问权限
        checkPermissionInfo.setIsAuth(true);
        checkPermissionInfo.setPermissionInfo(current);
        return checkPermissionInfo;
        // return Mono.just(checkPermissionInfo);
    }

    @Override
    public CheckPermissionInfo checkUserPermission(String userId, String requestUri, String requestMethod) throws Exception {
        return null;
    }

}

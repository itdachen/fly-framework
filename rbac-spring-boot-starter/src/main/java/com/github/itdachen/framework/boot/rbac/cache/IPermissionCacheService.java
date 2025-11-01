package com.github.itdachen.framework.boot.rbac.cache;

import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/**
 * Description: 权限缓存接口
 * Created by 王大宸 on 2022-12-13 11:25
 * Created with IntelliJ IDEA.
 */
public interface IPermissionCacheService {

    /***
     * 获取所有的权限信息
     *
     * @author 王大宸
     * @date 2022/12/13 14:09
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    List<PermissionInfo> findAllPermission() throws Exception;

    /***
     * 获取个人岗位权限
     *
     * @author 王大宸
     * @date 2022/12/13 14:12
     * @param userId 当前登录用户ID
     * @param jobId  当前岗位ID
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    List<PermissionInfo> findUserJobPermission(String userId, String hostIp, String jobId) throws Exception;

    /***
     * 获取当前用户所有菜单
     *
     * @author 王大宸
     * @date 2022/12/13 14:13
     * @param userId 当前登录用户ID
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    List<PermissionInfo> findUserPermission(String userId, String hostIp) throws Exception;

}

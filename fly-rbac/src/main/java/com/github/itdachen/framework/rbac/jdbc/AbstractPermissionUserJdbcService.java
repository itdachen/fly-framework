package com.github.itdachen.framework.rbac.jdbc;

import com.github.itdachen.framework.context.permission.PermissionInfo;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-12-13 15:14
 * Created with IntelliJ IDEA.
 */
public abstract class AbstractPermissionUserJdbcService implements IPermissionUserJdbcService {

    /***
     * 获取个人岗位权限
     *
     * @author 王大宸
     * @date 2022/12/13 14:12
     * @param userId 当前登录用户ID
     * @param jobId  当前岗位ID
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    @Override
    public List<PermissionInfo> findUserJobPermission(String userId,
                                                      String hostIp,
                                                      String jobId) throws Exception {
        List<PermissionInfo> permissionInfos = HandlerMenuPermission.handlerMenu(findUserJobMenuPermission(userId, hostIp, jobId));
        permissionInfos.addAll(findUserJobElementPermission(userId, hostIp, jobId));
        return permissionInfos;
    }

    /***
     * 获取当前用户所有菜单
     *
     * @author 王大宸
     * @date 2022/12/13 14:13
     * @param userId 当前登录用户ID
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    @Override
    public List<PermissionInfo> findUserPermission(String userId,
                                                   String hostIp) throws Exception {
        List<PermissionInfo> permissionInfos = HandlerMenuPermission.handlerMenu(findUserMenuPermission(userId, hostIp));
        permissionInfos.addAll(findUserElementPermission(userId, hostIp));
        return permissionInfos;
    }

    /***
     * 根据ip和岗位查询角色拥有菜单权限
     *
     * @author 王大宸
     * @date 2022/12/13 15:30
     * @param userId 当前用户id
     * @param hostIp 当前登录系统ip
     * @param jobId 当前岗位
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    protected abstract List<PermissionInfo> findUserJobMenuPermission(String userId,
                                                                      String hostIp,
                                                                      String jobId) throws Exception;

    /***
     * 根据ip和岗位查询角色拥有按钮权限
     *
     * @author 王大宸
     * @date 2022/12/13 15:31
     * @param userId 当前用户id
     * @param hostIp 当前登录系统ip
     * @param jobId 当前岗位
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    protected abstract List<PermissionInfo> findUserJobElementPermission(String userId,
                                                                         String hostIp,
                                                                         String jobId) throws Exception;

    /***
     * 根据ip查询角色拥有菜单权限
     *
     * @author 王大宸
     * @date 2022/12/13 15:29
     * @param userId 用户id
     * @param hostIp 当前系统 ip
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    protected abstract List<PermissionInfo> findUserMenuPermission(String userId,
                                                                   String hostIp) throws Exception;

    /***
     * 根据ip查询用户按钮权限
     *
     * @author 王大宸
     * @date 2022/12/13 15:30
     * @param userId 用户id
     * @param hostIp 当前系统 ip
     * @return java.util.List<com.github.itdachen.framework.context.permission.PermissionInfo>
     */
    protected abstract List<PermissionInfo> findUserElementPermission(String userId,
                                                                      String hostIp) throws Exception;


}

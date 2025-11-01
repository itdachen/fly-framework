package com.github.itdachen.framework.boot.rbac;

import com.github.itdachen.framework.context.permission.CheckPermissionInfo;

/**
 * Description: Rbac 鉴权
 * Created by 王大宸 on 2022-12-13 11:25
 * Created with IntelliJ IDEA.
 */
public interface IRbacPermissionService {

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
    CheckPermissionInfo checkUserPermission(String userId,
                                            String jobId,
                                            String hostIp,
                                            String requestUri,
                                            String requestMethod) throws Exception;


    CheckPermissionInfo checkUserPermission(String userId,
                                            String requestUri,
                                            String requestMethod) throws Exception;

}

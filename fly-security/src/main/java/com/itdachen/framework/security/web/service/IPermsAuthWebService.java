package com.itdachen.framework.security.web.service;

import com.itdachen.framework.context.permission.PermissionInfo;
import com.itdachen.framework.security.user.LYearAdminMenu;
import com.itdachen.framework.security.user.LayuiAdminMenu;
import com.itdachen.framework.security.user.OkAdminMenu;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2022-09-25 15:14
 * Created with IntelliJ IDEA.
 */
public interface IPermsAuthWebService {

    /***
     * 根据用户信息查询可用菜单
     *
     * @author 王大宸
     * @date 2021/11/28 11:36
     * @param client     客户点id(系统编码)
     * @param userType   用户类型
     * @param userId     用户id
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     */
    List<LayuiAdminMenu> findPermsAuthMenu(String client, String userType, String userId);


    /***
     * ok-admin 后台获取用户菜单
     *
     * @author 王大宸
     * @date 2022/2/25 21:26
     * @param client     客户点id
     * @param userType   用户类型
     * @param userId     用户id
     * @return java.util.List<com.itdachen.admin.entity.OkAdminMenu>
     */
    List<OkAdminMenu> getUserOkAdminMenu(String client, String userType, String userId);

    /***
     * 查询首页
     *
     * @author 王大宸
     * @date 2022/9/25 15:25
     * @param client client
     * @return java.lang.String
     */
    PermissionInfo getHomeUri(String client);

    /***
     * 获取光年后台管理模板菜单
     *
     * @author 王大宸
     * @date 2023/2/3 9:17
     * @param client client
     * @param userType userType
     * @param userId userId
     * @return java.util.List<com.itdachen.framework.user.LYearAdminMenu>
     */
    List<LYearAdminMenu> findLYearAdminMenu(String client, String userType, String userId) throws Exception;

}

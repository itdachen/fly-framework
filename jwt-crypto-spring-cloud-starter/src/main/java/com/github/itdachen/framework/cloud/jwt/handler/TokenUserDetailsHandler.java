package com.github.itdachen.framework.cloud.jwt.handler;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import com.github.itdachen.framework.core.utils.LocalDateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息处理
 *
 * @author 剑鸣秋朔
 * @date 2024-09-03 16:19
 */
public class TokenUserDetailsHandler {

    /***
     * 将用户信息转成 Map
     *
     * @author 剑鸣秋朔
     * @date 2024/9/3 16:31
     * @param userDetails userDetails
     * @param tokenType tokenType
     * @return java.util.Map<java.lang.String, java.lang.String>
     */
    public static Map<String, String> setUserDetailMap(UserInfoDetails userDetails, String tokenType) {
        /* 存放在 token 中的信息 */
        Map<String, String> otherInfo = new HashMap<>();
        otherInfo.put(UserInfoConstant.ID, userDetails.getId());
        otherInfo.put(UserInfoConstant.ACCOUNT, userDetails.getUsername());
        otherInfo.put(UserInfoConstant.LOGIN_METHOD, userDetails.getLoginMethod());

        /* 平台/应用信息 */
        otherInfo.put(UserInfoConstant.PLAT_ID, userDetails.getPlatId());
        otherInfo.put(UserInfoConstant.PLAT_NAME, userDetails.getPlatName());
        otherInfo.put(UserInfoConstant.APP_ID, userDetails.getAppId());
        otherInfo.put(UserInfoConstant.APP_NAME, userDetails.getAppName());
        otherInfo.put(UserInfoConstant.APP_CONTEXT_PATH, userDetails.getAppContextPath());
        otherInfo.put(UserInfoConstant.APP_VERSION, userDetails.getAppVersion());

        /* 租户信息 */
        otherInfo.put(UserInfoConstant.TENANT_ID, userDetails.getTenantId());
        otherInfo.put(UserInfoConstant.TENANT_TITLE, userDetails.getTenantTitle());

        /* 基础信息 */
        otherInfo.put(UserInfoConstant.USER_ID, userDetails.getId());
        otherInfo.put(UserInfoConstant.NICK_NAME, userDetails.getNickName());
        otherInfo.put(UserInfoConstant.AVATAR, userDetails.getAvatar());
        otherInfo.put(UserInfoConstant.E_MAIL, userDetails.getEmail());
        otherInfo.put(UserInfoConstant.SEX, userDetails.getSex());
        otherInfo.put(UserInfoConstant.TELEPHONE, userDetails.getTelephone());
        otherInfo.put(UserInfoConstant.USER_TYPE, userDetails.getUserType());
        otherInfo.put(UserInfoConstant.VALID_FLAG, userDetails.getValidFlag());

        /* 身份信息 */
        otherInfo.put(UserInfoConstant.ROLE_ID, userDetails.getRoleId());
        otherInfo.put(UserInfoConstant.ROLE_NAME, userDetails.getRoleName());
        otherInfo.put(UserInfoConstant.ROLE_FLAG, userDetails.getRoleFlag());

        /* 部门信息 */
        otherInfo.put(UserInfoConstant.DEPT_ID, userDetails.getDeptId());
        otherInfo.put(UserInfoConstant.DEPT_TITLE, userDetails.getDeptTitle());
        otherInfo.put(UserInfoConstant.DEPT_LEVEL, userDetails.getDeptLevel());
        otherInfo.put(UserInfoConstant.DEPT_TYPE, userDetails.getDeptType());
        otherInfo.put(UserInfoConstant.DEPT_PARENT_ID, userDetails.getDeptParentId());

        /* 省市区县 */
        otherInfo.put(UserInfoConstant.PROV_ID, userDetails.getProvCode());
        otherInfo.put(UserInfoConstant.PROV_NAME, userDetails.getProvName());
        otherInfo.put(UserInfoConstant.CITY_ID, userDetails.getCityCode());
        otherInfo.put(UserInfoConstant.CITY_NAME, userDetails.getCityName());
        otherInfo.put(UserInfoConstant.COUNTY_ID, userDetails.getCountyCode());
        otherInfo.put(UserInfoConstant.COUNTY_NAME, userDetails.getCountyName());
        otherInfo.put(UserInfoConstant.TOWN_CODE, userDetails.getTownCode());
        otherInfo.put(UserInfoConstant.TOWN_NAME, userDetails.getTownName());

        /* 访问主机信息 */
        otherInfo.put(UserInfoConstant.HOST_IP, userDetails.getHostIp());
        otherInfo.put(UserInfoConstant.HOST_OS, userDetails.getHostOs());
        otherInfo.put(UserInfoConstant.HOST_BROWSER, userDetails.getHostBrowser());
        otherInfo.put(UserInfoConstant.HOST_USER_AGENT, userDetails.getHostUserAgent());
        otherInfo.put(UserInfoConstant.HOST_ADDR, userDetails.getHostAddr());
        otherInfo.put(UserInfoConstant.HOST_PROV, userDetails.getHostProv());
        otherInfo.put(UserInfoConstant.HOST_CITY, userDetails.getHostCity());
        otherInfo.put(UserInfoConstant.EXP_TIME, LocalDateUtils.toLocalDateTime(userDetails.getExpTime()));
        otherInfo.put(UserInfoConstant.LAST_TIME, LocalDateUtils.toLocalDateTime(userDetails.getLastTime()));

        otherInfo.put(UserInfoConstant.TOKEN_TICKET, tokenType);
        return otherInfo;
    }


    /***
     * 返回给前端的信息
     *
     * @author 剑鸣秋朔
     * @date 2024/9/3 16:30
     * @param userDetails userDetails
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    public static Map<String, Object> toFrontEndInfo(UserInfoDetails userDetails) {
        /* 返回给前端的信息 */
        Map<String, Object> otherInfo = new HashMap<>();
        //  otherInfo.put(UserInfoConstant.ID, userDetails.getId());
        // otherInfo.put(UserInfoConstant.LOGIN_METHOD, userDetails.getLoginMethod());
        // otherInfo.put(UserInfoConstant.ACCOUNT, userDetails.getUsername());

        /* 平台/应用信息 */
//        otherInfo.put(UserInfoConstant.PLAT_ID, userDetails.getPlatId());
//        otherInfo.put(UserInfoConstant.PLAT_NAME, userDetails.getPlatName());
//        otherInfo.put(UserInfoConstant.APP_ID, userDetails.getAppId());
//        otherInfo.put(UserInfoConstant.APP_NAME, userDetails.getAppName());
//        otherInfo.put(UserInfoConstant.APP_CONTEXT_PATH, userDetails.getAppContextPath());
//        otherInfo.put(UserInfoConstant.APP_VERSION, userDetails.getAppVersion());

        /* 租户信息 */
        otherInfo.put(UserInfoConstant.TENANT_ID, userDetails.getTenantId());
        otherInfo.put(UserInfoConstant.TENANT_TITLE, userDetails.getTenantTitle());

        /* 基础信息 */
        otherInfo.put(UserInfoConstant.USER_ID, userDetails.getId());
        otherInfo.put(UserInfoConstant.NICK_NAME, userDetails.getNickName());
        otherInfo.put(UserInfoConstant.AVATAR, userDetails.getAvatar());
        otherInfo.put(UserInfoConstant.E_MAIL, userDetails.getEmail());
        otherInfo.put(UserInfoConstant.SEX, userDetails.getSex());
        otherInfo.put(UserInfoConstant.TELEPHONE, userDetails.getTelephone());
        otherInfo.put(UserInfoConstant.USER_TYPE, userDetails.getUserType());
        otherInfo.put(UserInfoConstant.VALID_FLAG, userDetails.getValidFlag());

        /* 身份信息 */
        otherInfo.put(UserInfoConstant.ROLE_ID, userDetails.getRoleId());
        otherInfo.put(UserInfoConstant.ROLE_NAME, userDetails.getRoleName());
        otherInfo.put(UserInfoConstant.ROLE_FLAG, userDetails.getRoleFlag());

        /* 部门信息 */
        otherInfo.put(UserInfoConstant.DEPT_ID, userDetails.getDeptId());
        otherInfo.put(UserInfoConstant.DEPT_TITLE, userDetails.getDeptTitle());
        otherInfo.put(UserInfoConstant.DEPT_LEVEL, userDetails.getDeptLevel());
        otherInfo.put(UserInfoConstant.DEPT_TYPE, userDetails.getDeptType());
        otherInfo.put(UserInfoConstant.DEPT_PARENT_ID, userDetails.getDeptParentId());

        /* 省市区县 */
//        otherInfo.put(UserInfoConstant.PROV_ID, userDetails.getProvCode());
//        otherInfo.put(UserInfoConstant.PROV_NAME, userDetails.getProvName());
//        otherInfo.put(UserInfoConstant.CITY_ID, userDetails.getCityCode());
//        otherInfo.put(UserInfoConstant.CITY_NAME, userDetails.getCityName());
//        otherInfo.put(UserInfoConstant.COUNTY_ID, userDetails.getCountyCode());
//        otherInfo.put(UserInfoConstant.COUNTY_NAME, userDetails.getCountyName());
//        otherInfo.put(UserInfoConstant.STREET_CODE, userDetails.getStreetCode());
//        otherInfo.put(UserInfoConstant.STREET_NAME, userDetails.getStreetName());

        /* 访问主机信息 */
//        otherInfo.put(UserInfoConstant.HOST_IP, userDetails.getHostIp());
//        otherInfo.put(UserInfoConstant.HOST_OS, userDetails.getHostOs());
//        otherInfo.put(UserInfoConstant.HOST_BROWSER, userDetails.getHostBrowser());
//        otherInfo.put(UserInfoConstant.HOST_USER_AGENT, userDetails.getHostUserAgent());
//        otherInfo.put(UserInfoConstant.HOST_ADDR, userDetails.getHostAddr());
//        otherInfo.put(UserInfoConstant.HOST_PROV, userDetails.getHostProv());
//        otherInfo.put(UserInfoConstant.HOST_CITY, userDetails.getHostCity());
//        otherInfo.put(UserInfoConstant.EXP_TIME, LocalDateUtils.toLocalDateTime(userDetails.getExpTime()));
//        otherInfo.put(UserInfoConstant.LAST_TIME, LocalDateUtils.toLocalDateTime(userDetails.getLastTime()));
        return otherInfo;
    }


}

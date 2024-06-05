package com.github.itdachen.framework.context.handler;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.DateFormatConstants;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;

import java.time.LocalDateTime;
import java.util.Map;

/***
 * 全局获取用户信息处理
 *
 * @author 王大宸
 * @date 2023/11/27 21:26
 */
public class GlobalContextUserDetailsHandler {


    /***
     * 将用户信息添加到当前线程中
     *
     * @author 王大宸
     * @date 2024/4/13 23:37
     * @param userDetails userDetails
     * @return void
     */
    public static void contextUserHandler(UserInfoDetails userDetails) {
        BizContextHandler.setId(userDetails.getId());
        BizContextHandler.setUsername(userDetails.getUsername());
        BizContextHandler.setLoginMethod(userDetails.getLoginMethod());

        /* 平台/应用信息 */
        BizContextHandler.setPlatId(userDetails.getPlatId());
        BizContextHandler.setPlatName(userDetails.getPlatName());
        BizContextHandler.setAppId(userDetails.getAppId());
        BizContextHandler.setAppName(userDetails.getAppName());
        BizContextHandler.setAppVersion(userDetails.getAppVersion());
        BizContextHandler.setAppContextPath(userDetails.getAppContextPath());

        /* 租户信息 */
        BizContextHandler.setTenantId(userDetails.getTenantId());
        BizContextHandler.setTenantTitle(userDetails.getTenantTitle());

        /* 用户信息 */
        BizContextHandler.setUserId(userDetails.getId());
        BizContextHandler.setNickName(userDetails.getNickName());
        BizContextHandler.setAvatar(userDetails.getAvatar());
        BizContextHandler.setEmail(userDetails.getEmail());
        BizContextHandler.setSex(userDetails.getSex());
        BizContextHandler.setUserType(userDetails.getUserType());
        BizContextHandler.setTelephone(userDetails.getTelephone());
        BizContextHandler.setValidFlag(userDetails.getValidFlag());

        /* 身份信息 */
        BizContextHandler.setRoleId(userDetails.getRoleId());
        BizContextHandler.setRoleName(userDetails.getRoleName());
        BizContextHandler.setRoleFlag(userDetails.getRoleFlag());

        /* 区域信息 */
        BizContextHandler.setProvId(userDetails.getProvId());
        BizContextHandler.setCityId(userDetails.getCityId());
        BizContextHandler.setCountyId(userDetails.getCountyId());

        /* 部门信息 */
        BizContextHandler.setDeptId(userDetails.getDeptId());
        BizContextHandler.setDeptTitle(userDetails.getDeptTitle());
        BizContextHandler.setDeptType(userDetails.getDeptType());
        BizContextHandler.setDeptLevel(userDetails.getDeptLevel());
        BizContextHandler.setDeptParentId(userDetails.getDeptParentId());

        /* 操作主机信息 */
        BizContextHandler.setHostIp(userDetails.getHostIp());
        BizContextHandler.setHostOs(userDetails.getHostOs());
        BizContextHandler.setHostBrowser(userDetails.getHostBrowser());
        BizContextHandler.setHostAddr(userDetails.getHostAddr());
        BizContextHandler.setHostUserAgent(userDetails.getHostUserAgent());
        BizContextHandler.setHostProv(userDetails.getHostProv());
        BizContextHandler.setHostCity(userDetails.getHostCity());

        /* 密码更新时间 */
        BizContextHandler.setExpTime(userDetails.getExpTime());
        BizContextHandler.setLastTime(userDetails.getLastTime());
    }

    /***
     * 将用户信息添加到当前线程中
     *
     * @author 王大宸
     * @date 2024/4/13 23:37
     * @param jwtInfo jwtInfo
     * @return void
     */
    public static void contextUserHandler(IJwtInfo jwtInfo) {
        BizContextHandler.setTokenId(jwtInfo.getTokenId());

        BizContextHandler.setId(jwtInfo.getUserId());
        BizContextHandler.setUsername(jwtInfo.getUniqueName());
        BizContextHandler.setTenantId(jwtInfo.getTenantId());
        BizContextHandler.setUserId(jwtInfo.getUserId());
        BizContextHandler.setNickName(jwtInfo.getNickName());

        final Map<String, String> otherInfo = jwtInfo.getOtherInfo();

        if (null == otherInfo || otherInfo.isEmpty()){
            return;
        }

        /* 登录信息 */
        BizContextHandler.setId(jwtInfo.getUserId());
        BizContextHandler.setUsername(jwtInfo.getUniqueName());
        BizContextHandler.setLoginMethod(otherInfo.get(UserInfoConstant.LOGIN_METHOD));

        /* 平台/应用信息 */
        BizContextHandler.setPlatId(otherInfo.get(UserInfoConstant.PLAT_ID));
        BizContextHandler.setPlatName(otherInfo.get(UserInfoConstant.PLAT_NAME));
        BizContextHandler.setAppId(otherInfo.get(UserInfoConstant.APP_ID));
        BizContextHandler.setAppName(otherInfo.get(UserInfoConstant.APP_NAME));
        BizContextHandler.setAppVersion(otherInfo.get(UserInfoConstant.APP_VERSION));
        BizContextHandler.setAppContextPath(otherInfo.get(UserInfoConstant.APP_CONTEXT_PATH));

        /* 租户信息 */
        BizContextHandler.setTenantId(jwtInfo.getTenantId());
        BizContextHandler.setTenantTitle(otherInfo.get(UserInfoConstant.TENANT_TITLE));

        /* 基础信息 */
        BizContextHandler.setUserId(jwtInfo.getUserId());
        BizContextHandler.setNickName(jwtInfo.getNickName());
        BizContextHandler.setAvatar(otherInfo.get(UserInfoConstant.AVATAR));
        BizContextHandler.setEmail(otherInfo.get(UserInfoConstant.E_MAIL));
        BizContextHandler.setSex(otherInfo.get(UserInfoConstant.SEX));
        BizContextHandler.setUserType(otherInfo.get(UserInfoConstant.USER_TYPE));
        BizContextHandler.setTelephone(otherInfo.get(UserInfoConstant.TELEPHONE));
        BizContextHandler.setValidFlag(otherInfo.get(UserInfoConstant.VALID_FLAG));

        /* 身份信息 */
        BizContextHandler.setRoleId(otherInfo.get(UserInfoConstant.ROLE_ID));
        BizContextHandler.setRoleName(otherInfo.get(UserInfoConstant.ROLE_NAME));
        BizContextHandler.setRoleFlag(otherInfo.get(UserInfoConstant.ROLE_FLAG));

        /* 部门信息 */
        BizContextHandler.setDeptId(otherInfo.get(UserInfoConstant.DEPT_ID));
        BizContextHandler.setDeptTitle(otherInfo.get(UserInfoConstant.DEPT_TITLE));
        BizContextHandler.setDeptParentId(otherInfo.get(UserInfoConstant.DEPT_PARENT_ID));
        BizContextHandler.setDeptLevel(otherInfo.get(UserInfoConstant.DEPT_LEVEL));
        BizContextHandler.setDeptType(otherInfo.get(UserInfoConstant.DEPT_TYPE));

        /* 账号行政区域信息 */
        BizContextHandler.setProvId(otherInfo.get(UserInfoConstant.PROV_ID));
        BizContextHandler.setCityId(otherInfo.get(UserInfoConstant.CITY_ID));
        BizContextHandler.setCountyId(otherInfo.get(UserInfoConstant.COUNTY_ID));

        /* 登录主机信息 */
        BizContextHandler.setHostIp(otherInfo.get(UserInfoConstant.HOST_IP));
        BizContextHandler.setHostProv(otherInfo.get(UserInfoConstant.HOST_PROV));
        BizContextHandler.setHostCity(otherInfo.get(UserInfoConstant.HOST_CITY));
        BizContextHandler.setHostAddr(otherInfo.get(UserInfoConstant.HOST_ADDR));
        BizContextHandler.setHostUserAgent(otherInfo.get(UserInfoConstant.HOST_USER_AGENT));
        BizContextHandler.setHostOs(otherInfo.get(UserInfoConstant.HOST_OS));
        BizContextHandler.setHostBrowser(otherInfo.get(UserInfoConstant.HOST_BROWSER));

        /* 密码更新时间 */
        if (null != otherInfo.get(UserInfoConstant.EXP_TIME)) {
            BizContextHandler.setExpTime(LocalDateTime.parse(otherInfo.get(UserInfoConstant.EXP_TIME), DateFormatConstants.DATE_TIME_FORMATTER));
        }
        if (null != otherInfo.get(UserInfoConstant.LAST_TIME)) {
            BizContextHandler.setLastTime(LocalDateTime.parse(otherInfo.get(UserInfoConstant.LAST_TIME), DateFormatConstants.DATE_TIME_FORMATTER));
        }

    }


    /***
     * 获取当前线程中的用户所有信息
     *
     * @author 王大宸
     * @date 2024/4/13 23:37
     * @return com.github.itdachen.framework.context.userdetails.UserInfoDetails
     */
    public static UserInfoDetails getUserDetails() {
        UserInfoDetails userInfoDetails = new UserInfoDetails();
        userInfoDetails.setId(BizContextHandler.getId());
        userInfoDetails.setUsername(BizContextHandler.getUsername());
        userInfoDetails.setLoginMethod(BizContextHandler.getLoginMethod());

        /* 平台/应用信息 */
        userInfoDetails.setPlatId(BizContextHandler.getPlatId());
        userInfoDetails.setPlatName(BizContextHandler.getPlatName());
        userInfoDetails.setAppId(BizContextHandler.getAppId());
        userInfoDetails.setAppName(BizContextHandler.getAppName());
        userInfoDetails.setAppVersion(BizContextHandler.getAppVersion());
        userInfoDetails.setAppContextPath(BizContextHandler.getAppContextPath());

        /* 租户信息 */
        userInfoDetails.setTenantId(BizContextHandler.getTenantId());
        userInfoDetails.setTenantTitle(BizContextHandler.getTenantTitle());

        /* 用户信息 */
        userInfoDetails.setNickName(BizContextHandler.getNickName());
        userInfoDetails.setAvatar(BizContextHandler.getAvatar());
        userInfoDetails.setEmail(BizContextHandler.getEmail());
        userInfoDetails.setSex(BizContextHandler.getSex());
        userInfoDetails.setUserType(BizContextHandler.getUserType());
        userInfoDetails.setTelephone(BizContextHandler.getTelephone());
        userInfoDetails.setValidFlag(BizContextHandler.getValidFlag());

        /* 身份信息 */
        userInfoDetails.setRoleId(BizContextHandler.getRoleId());
        userInfoDetails.setRoleName(BizContextHandler.getRoleName());
        userInfoDetails.setRoleFlag(BizContextHandler.getRoleFlag());

        /* 区域信息 */
        userInfoDetails.setProvId(BizContextHandler.getProvId());
        userInfoDetails.setCityId(BizContextHandler.getCityId());
        userInfoDetails.setCountyId(BizContextHandler.getCountyId());

        /* 部门信息 */
        userInfoDetails.setDeptId(BizContextHandler.getDeptId());
        userInfoDetails.setDeptTitle(BizContextHandler.getDeptTitle());
        userInfoDetails.setDeptType(BizContextHandler.getDeptType());
        userInfoDetails.setDeptLevel(BizContextHandler.getDeptLevel());
        userInfoDetails.setDeptParentId(BizContextHandler.getDeptParentId());

        /* 操作主机信息 */
        userInfoDetails.setHostIp(BizContextHandler.getHostIp());
        userInfoDetails.setHostOs(BizContextHandler.getHostOs());
        userInfoDetails.setHostBrowser(BizContextHandler.getHostBrowser());
        userInfoDetails.setHostAddr(BizContextHandler.getHostAddr());
        userInfoDetails.setHostUserAgent(BizContextHandler.getHostUserAgent());
        userInfoDetails.setHostProv(BizContextHandler.getHostProv());
        userInfoDetails.setHostCity(BizContextHandler.getHostCity());

        /* 密码更新时间 */
        userInfoDetails.setExpTime(BizContextHandler.getExpTime());
        userInfoDetails.setLastTime(BizContextHandler.getLastTime());
        return userInfoDetails;

    }

    /***
     * 重新 set 值(用处不大, 解决在其他地方被继承时, 无法序列化, 需要重新 new 一个对象)
     *
     * @author 王大宸
     * @date 2024/4/13 23:36
     * @param userDetails userDetails
     * @return com.github.itdachen.framework.context.userdetails.UserInfoDetails
     */
    public static UserInfoDetails setUserDetails(UserInfoDetails userDetails) {
        UserInfoDetails userInfoDetails = new UserInfoDetails();
        userInfoDetails.setId(userDetails.getId());
        userInfoDetails.setUsername(userDetails.getUsername());
        userInfoDetails.setLoginMethod(userDetails.getLoginMethod());

        /* 平台/应用信息 */
        userInfoDetails.setPlatId(userDetails.getPlatId());
        userInfoDetails.setPlatName(userDetails.getPlatName());
        userInfoDetails.setAppId(userDetails.getAppId());
        userInfoDetails.setAppName(userDetails.getAppName());
        userInfoDetails.setAppVersion(userDetails.getAppVersion());
        userInfoDetails.setAppContextPath(userDetails.getAppContextPath());

        /* 租户信息 */
        userInfoDetails.setTenantId(userDetails.getTenantId());
        userInfoDetails.setTenantTitle(userDetails.getTenantTitle());

        /* 用户信息 */
        userInfoDetails.setNickName(userDetails.getNickName());
        userInfoDetails.setAvatar(userDetails.getAvatar());
        userInfoDetails.setEmail(userDetails.getEmail());
        userInfoDetails.setSex(userDetails.getSex());
        userInfoDetails.setUserType(userDetails.getUserType());
        userInfoDetails.setTelephone(userDetails.getTelephone());
        userInfoDetails.setValidFlag(userDetails.getValidFlag());

        /* 身份信息 */
        userInfoDetails.setRoleId(userDetails.getRoleId());
        userInfoDetails.setRoleName(userDetails.getRoleName());
        userInfoDetails.setRoleFlag(userDetails.getRoleFlag());

        /* 区域信息 */
        userInfoDetails.setProvId(userDetails.getProvId());
        userInfoDetails.setCityId(userDetails.getCityId());
        userInfoDetails.setCountyId(userDetails.getCountyId());

        /* 部门信息 */
        userInfoDetails.setDeptId(userDetails.getDeptId());
        userInfoDetails.setDeptTitle(userDetails.getDeptTitle());
        userInfoDetails.setDeptType(userDetails.getDeptType());
        userInfoDetails.setDeptLevel(userDetails.getDeptLevel());
        userInfoDetails.setDeptParentId(userDetails.getDeptParentId());

        /* 操作主机信息 */
        userInfoDetails.setHostIp(userDetails.getHostIp());
        userInfoDetails.setHostOs(userDetails.getHostOs());
        userInfoDetails.setHostBrowser(userDetails.getHostBrowser());
        userInfoDetails.setHostAddr(userDetails.getHostAddr());
        userInfoDetails.setHostUserAgent(userDetails.getHostUserAgent());
        userInfoDetails.setHostProv(userDetails.getHostProv());
        userInfoDetails.setHostCity(userDetails.getHostCity());

        /* 密码更新时间 */
        userInfoDetails.setExpTime(userDetails.getExpTime());
        userInfoDetails.setLastTime(userDetails.getLastTime());
        return userInfoDetails;
    }


}

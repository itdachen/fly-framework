package com.github.itdachen.framework.context.handler;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;

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
        BizContextHandler.setUserId(userDetails.getId());
        BizContextHandler.setUsername(userDetails.getUsername());
        BizContextHandler.setTenantId(userDetails.getTenantId());

        /* 平台/应用信息 */
        BizContextHandler.setPlatId(userDetails.getPlatId());
        BizContextHandler.setPlatName(userDetails.getPlatName());
        BizContextHandler.setAppId(userDetails.getAppId());
        BizContextHandler.setAppName(userDetails.getAppName());
        BizContextHandler.setAppVersion(userDetails.getAppVersion());
        BizContextHandler.setAppContextPath(userDetails.getAppContextPath());

        /* 登录方式 */
        BizContextHandler.setSignMethod(userDetails.getSignMethod());

        /* 基础信息 */
        BizContextHandler.setNickName(userDetails.getNickName());
        BizContextHandler.setAvatar(userDetails.getAvatar());
        BizContextHandler.setEmail(userDetails.getEmail());
        BizContextHandler.setSex(userDetails.getSex());
        BizContextHandler.setUserType(userDetails.getUserType());
        BizContextHandler.setTelephone(userDetails.getTelephone());

        /* 身份信息 */
        BizContextHandler.setRoleId(userDetails.getRoleId());
        BizContextHandler.setRoleName(userDetails.getRoleName());
        BizContextHandler.setRoleFlag(userDetails.getRoleFlag());

        /* 部门信息 */
        BizContextHandler.setDeptId(userDetails.getDeptId());
        BizContextHandler.setDeptTitle(userDetails.getDeptTitle());
        BizContextHandler.setParentDeptId(userDetails.getParentDeptId());
        BizContextHandler.setDeptLevel(userDetails.getDeptLevel());

        /* 账号行政区域信息 */
        BizContextHandler.setProvId(userDetails.getProvId());
        BizContextHandler.setCityId(userDetails.getCityId());
        BizContextHandler.setCountyId(userDetails.getCountyId());

        /* 登录主机信息 */
        BizContextHandler.setHostIp(userDetails.getHostIp());
        BizContextHandler.setHostProv(userDetails.getHostProv());
        BizContextHandler.setHostCity(userDetails.getHostCity());
        BizContextHandler.setHostAddr(userDetails.getHostAddr());
        BizContextHandler.setUserAgent(userDetails.getUserAgent());

        /* 其他 */
        BizContextHandler.setExpTime(userDetails.getExpTime());

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
        BizContextHandler.setUsername(jwtInfo.getUniqueName());
        BizContextHandler.setUserId(jwtInfo.getUserId());
        BizContextHandler.setId(jwtInfo.getUserId());
        BizContextHandler.setNickName(jwtInfo.getNickName());
        BizContextHandler.setTenantId(jwtInfo.getTenantId());

        final Map<String, String> otherInfo = jwtInfo.getOtherInfo();


        /* 平台/应用信息 */
        BizContextHandler.setPlatId(otherInfo.get(UserInfoConstant.PLAT_ID));
        BizContextHandler.setPlatName(otherInfo.get(UserInfoConstant.PLAT_NAME));
        BizContextHandler.setAppId(otherInfo.get(UserInfoConstant.APP_ID));
        BizContextHandler.setAppName(otherInfo.get(UserInfoConstant.APP_NAME));
        BizContextHandler.setAppVersion(otherInfo.get(UserInfoConstant.APP_VERSION));
        BizContextHandler.setAppContextPath(otherInfo.get(UserInfoConstant.APP_CONTEXT_PATH));

        /* 登录方式 */
        BizContextHandler.setSignMethod(otherInfo.get(UserInfoConstant.SIGN_METHOD));

        /* 基础信息 */
        BizContextHandler.setAvatar(otherInfo.get(UserInfoConstant.AVATAR));
        BizContextHandler.setEmail(otherInfo.get(UserInfoConstant.E_MAIL));
        BizContextHandler.setSex(otherInfo.get(UserInfoConstant.SEX));
        BizContextHandler.setUserType(otherInfo.get(UserInfoConstant.USER_TYPE));
        BizContextHandler.setTelephone(otherInfo.get(UserInfoConstant.TELEPHONE));

        /* 身份信息 */
        BizContextHandler.setRoleId(otherInfo.get(UserInfoConstant.ROLE_ID));
        BizContextHandler.setRoleName(otherInfo.get(UserInfoConstant.ROLE_NAME));
        BizContextHandler.setRoleFlag(otherInfo.get(UserInfoConstant.ROLE_FLAG));

        /* 部门信息 */
        BizContextHandler.setDeptId(otherInfo.get(UserInfoConstant.DEPT_ID));
        BizContextHandler.setDeptTitle(otherInfo.get(UserInfoConstant.DEPT_TITLE));
        BizContextHandler.setParentDeptId(otherInfo.get(UserInfoConstant.PARENT_DEPT_ID));
        BizContextHandler.setDeptLevel(otherInfo.get(UserInfoConstant.DEPT_LEVEL));

        /* 账号行政区域信息 */
        BizContextHandler.setProvId(otherInfo.get(UserInfoConstant.PROV_ID));
        BizContextHandler.setCityId(otherInfo.get(UserInfoConstant.CITY_ID));
        BizContextHandler.setCountyId(otherInfo.get(UserInfoConstant.COUNTY_ID));

        /* 登录主机信息 */
        BizContextHandler.setHostIp(otherInfo.get(UserInfoConstant.HOST_IP));
        BizContextHandler.setHostProv(otherInfo.get(UserInfoConstant.HOST_PROV));
        BizContextHandler.setHostCity(otherInfo.get(UserInfoConstant.HOST_CITY));
        BizContextHandler.setHostAddr(otherInfo.get(UserInfoConstant.HOST_ADDR));
        BizContextHandler.setUserAgent(otherInfo.get(UserInfoConstant.USER_AGENT));

    }


    /***
     * 获取当前线程中的用户所有信息
     *
     * @author 王大宸
     * @date 2024/4/13 23:37
     * @return com.github.itdachen.framework.context.userdetails.UserInfoDetails
     */
    public static UserInfoDetails getUserDetails() {
        return UserInfoDetails.builder()
                .id(BizContextHandler.getId())
                .username(BizContextHandler.getUsername())
                .tenantId(BizContextHandler.getTenantId())
                .platId(BizContextHandler.getPlatId())
                .platName(BizContextHandler.getPlatName())
                .appId(BizContextHandler.getAppId())
                .appName(BizContextHandler.getAppName())
                .appVersion(BizContextHandler.getAppVersion())
                .appContextPath(BizContextHandler.getAppContextPath())
                .signMethod(BizContextHandler.getSignMethod())
                .nickName(BizContextHandler.getNickName())
                .avatar(BizContextHandler.getAvatar())
                .email(BizContextHandler.getEmail())
                .sex(BizContextHandler.getSex())
                .userType(BizContextHandler.getUserType())
                .telephone(BizContextHandler.getTelephone())
                .roleId(BizContextHandler.getRoleId())
                .roleName(BizContextHandler.getRoleName())
                .roleFlag(BizContextHandler.getRoleFlag())
                .deptId(BizContextHandler.getDeptId())
                .deptTitle(BizContextHandler.getDeptTitle())
                .parentDeptId(BizContextHandler.getParentDeptId())
                .deptLevel(BizContextHandler.getDeptLevel())
                .provId(BizContextHandler.getProvId())
                .cityId(BizContextHandler.getCityId())
                .countyId(BizContextHandler.getCountyId())
                .hostIp(BizContextHandler.getHostIp())
                .hostProv(BizContextHandler.getHostProv())
                .hostCity(BizContextHandler.getHostCity())
                .hostAddr(BizContextHandler.getHostAddr())
                .userAgent(BizContextHandler.getUserAgent())
                .build();

    }

    /***
     * 重新 set 值(用处不大, 怕其他地方被继承时, 无法序列化)
     *
     * @author 王大宸
     * @date 2024/4/13 23:36
     * @param userDetails userDetails
     * @return com.github.itdachen.framework.context.userdetails.UserInfoDetails
     */
    public static UserInfoDetails setUserDetails(UserInfoDetails userDetails) {
        return UserInfoDetails.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .tenantId(userDetails.getTenantId())
                .platId(userDetails.getPlatId())
                .platName(userDetails.getPlatName())
                .appId(userDetails.getAppId())
                .appName(userDetails.getAppName())
                .appVersion(userDetails.getAppVersion())
                .appContextPath(userDetails.getAppContextPath())
                .signMethod(userDetails.getSignMethod())
                .nickName(userDetails.getNickName())
                .avatar(userDetails.getAvatar())
                .email(userDetails.getEmail())
                .sex(userDetails.getSex())
                .userType(userDetails.getUserType())
                .telephone(userDetails.getTelephone())
                .roleId(userDetails.getRoleId())
                .roleName(userDetails.getRoleName())
                .roleFlag(userDetails.getRoleFlag())
                .deptId(userDetails.getDeptId())
                .deptTitle(userDetails.getDeptTitle())
                .parentDeptId(userDetails.getParentDeptId())
                .deptLevel(userDetails.getDeptLevel())
                .provId(userDetails.getProvId())
                .cityId(userDetails.getCityId())
                .countyId(userDetails.getCountyId())
                .hostIp(userDetails.getHostIp())
                .hostProv(userDetails.getHostProv())
                .hostCity(userDetails.getHostCity())
                .hostAddr(userDetails.getHostAddr())
                .userAgent(userDetails.getUserAgent())
                .expTime(userDetails.getExpTime())
                .build();

    }


}

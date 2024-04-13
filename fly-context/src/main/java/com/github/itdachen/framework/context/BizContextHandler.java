package com.github.itdachen.framework.context;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.handler.GlobalContextThreadLocalHandler;
import com.github.itdachen.framework.context.handler.GlobalContextUserDetailsHandler;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Description: 当前线程中全局获取用户信息
 * Created by 王大宸 on 2022-06-28 15:34
 * Created with IntelliJ IDEA.
 */
public class BizContextHandler {

    /**
     * 用户ID
     */
    public static String getId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.ID));
    }

    public static void setId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.ID, value);
    }


    /**
     * 用户ID(与上面ID差不多一样, 看具体用那个, 用户 userId 好理解一点)
     */
    public static String getUserId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.USER_ID));
    }

    public static void setUserId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.USER_ID, value);
    }

    /**
     * 昵称/姓名
     */
    public static String getNickName() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.NICK_NAME));
    }

    public static void setNickName(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.NICK_NAME, value);
    }

    /**
     * 登录账号
     */
    public static String getUsername() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.ACCOUNT));
    }

    public static void setUsername(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.ACCOUNT, value);
    }


    /**
     * 租户ID/公司ID
     */
    public static String getTenantId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.TENANT_ID));
    }

    public static void setTenantId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.TENANT_ID, value);
    }


    /**
     * 登录平台ID
     */
    public static String getPlatId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.PLAT_ID));
    }

    public static void setPlatId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.PLAT_ID, value);
    }

    /**
     * 登录平台名称
     */
    public static String getPlatName() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.PLAT_NAME));
    }

    public static void setPlatName(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.PLAT_NAME, value);
    }

    /**
     * 登录平台名称
     */
    public static String getAppId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.APP_ID));
    }

    public static void setAppId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.APP_ID, value);
    }


    /**
     * 登录平台名称
     */
    public static String getAppName() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.APP_NAME));
    }

    public static void setAppName(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.APP_NAME, value);
    }

    /**
     * 应用版本号
     */
    public static String getAppVersion() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.APP_VERSION));
    }

    public static void setAppVersion(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.APP_VERSION, value);
    }

    /**
     * 应用版本号
     */
    public static String getAppContextPath() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.APP_CONTEXT_PATH));
    }

    public static void setAppContextPath(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.APP_CONTEXT_PATH, value);
    }

    /**
     * 登录方式
     */
    public static String getSignMethod() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.SIGN_METHOD));
    }

    public static void setSignMethod(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.SIGN_METHOD, value);
    }

    /**
     * 头像
     */
    public static String getAvatar() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.AVATAR));
    }

    public static void setAvatar(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.AVATAR, value);
    }


    /**
     * 电子邮箱
     */
    public static String getEmail() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.E_MAIL));
    }

    public static void setEmail(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.E_MAIL, value);
    }

    /**
     * 性别
     */
    public static String getSex() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.SEX));
    }

    public static void setSex(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.SEX, value);
    }

    /**
     * 性别
     */
    public static String getUserType() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.USER_TYPE));
    }

    public static void setUserType(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.USER_TYPE, value);
    }

    /**
     * 电话号码
     */
    public static String getTelephone() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.TELEPHONE));
    }

    public static void setTelephone(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.TELEPHONE, value);
    }


    /**
     * 身份ID/身份代码
     */
    public static String getRoleId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.ROLE_ID));
    }

    public static void setRoleId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.ROLE_ID, value);
    }


    /**
     * 身份名称
     */
    public static String getRoleName() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.ROLE_NAME));
    }

    public static void setRoleName(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.ROLE_NAME, value);
    }

    /**
     * 主身份标识: Y-主身份;N-非主身份标识
     */
    public static String getRoleFlag() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.ROLE_FLAG));
    }

    public static void setRoleFlag(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.ROLE_FLAG, value);
    }


    /**
     * 部门代码(不同身份可能会不一样, 跟着不同身份信息绑定)
     */
    public static String getDeptId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.DEPT_ID));
    }

    public static void setDeptId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.DEPT_ID, value);
    }


    /**
     * 部门名称
     */
    public static String getDeptTitle() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.DEPT_TITLE));
    }

    public static void setDeptTitle(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.DEPT_TITLE, value);
    }

    /**
     * 部门名称
     */
    public static String getParentDeptId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.PARENT_DEPT_ID));
    }

    public static void setParentDeptId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.PARENT_DEPT_ID, value);
    }


    /**
     * 部门等级
     */
    public static String getDeptLevel() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.DEPT_LEVEL));
    }

    public static void setDeptLevel(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.DEPT_LEVEL, value);
    }


    /**
     * 身份所属省份代码, 例如: 贵州-52
     */
    public static String getProvId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.PROV_ID));
    }

    public static void setProvId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.PROV_ID, value);
    }

    /**
     * 身份所属市州代码, 例如: 贵阳-5201
     */
    public static String getCityId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.CITY_ID));
    }

    public static void setCityId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.CITY_ID, value);
    }

    /**
     * 身份所属区县代码, 例如: 贵阳南明-520101
     */
    public static String getCountyId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.COUNTY_ID));
    }

    public static void setCountyId(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.COUNTY_ID, value);
    }

    /**
     * 登录主机IP
     */
    public static String getHostIp() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.HOST_IP));
    }

    public static void setHostIp(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.HOST_IP, value);
    }


    /**
     * 登录主机地址代码, 例如: 贵州-52
     */
    public static String getHostProv() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.HOST_PROV));
    }

    public static void setHostProv(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.HOST_PROV, value);
    }


    /**
     * 登录主机地址代码, 例如: 贵阳-5201
     */
    public static String getHostCity() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.HOST_CITY));
    }

    public static void setHostCity(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.HOST_CITY, value);
    }

    /**
     * 登录主机详细地址
     */
    public static String getHostAddr() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.HOST_ADDR));
    }

    public static void setHostAddr(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.HOST_ADDR, value);
    }

    /**
     * 代理信息
     */
    public static String getUserAgent() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.USER_AGENT));
    }

    public static void setUserAgent(String value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.USER_AGENT, value);
    }


    /**
     * 过期时间(密码过期时间, 有些系统需要定期更新账号的密码)
     */
    public static String getExpTime() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.EXP_TIME));
    }

    public static void setExpTime(LocalDateTime value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.EXP_TIME, value);
    }

    /**
     * 其他信息
     */
    public static String getOther() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.OTHER));
    }

    public static void setOther(Map<String, String> value) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.OTHER, value);
    }

    /**
     * 用户 token/session
     */
    public static String getToken() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.TOKEN));
    }

    public static void setToken(String token) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.TOKEN, token);
    }

    /**
     * 用户 tokenId
     */
    public static String getTokenId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.TOKEN_ID));
    }

    public static void setTokenId(String tokenId) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.TOKEN_ID, tokenId);
    }

    /**
     * 日志id
     */
    public static String getLogId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(
                GlobalContextThreadLocalHandler.get(UserInfoConstant.LOG_ID)
        );
    }

    public static void setLogId(String token) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.LOG_ID, token);
    }


    /***
     * 获取当前线程中的用户所有信息
     *
     * @author 王大宸
     * @date 2024/4/13 23:37
     * @return com.github.itdachen.framework.context.userdetails.UserInfoDetails
     */
    public static UserInfoDetails getUserDetails() {
        return GlobalContextUserDetailsHandler.getUserDetails();
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
        return GlobalContextUserDetailsHandler.setUserDetails(userDetails);
    }

    /***
     * 将用户信息添加到当前线程中
     *
     * @author 王大宸
     * @date 2024/4/13 23:37
     * @param userDetails userDetails
     * @return void
     */
    public static void setContextHandler(UserInfoDetails userDetails) {
        GlobalContextUserDetailsHandler.contextUserHandler(userDetails);
    }


    /***
     * 将用户信息添加到当前线程中
     *
     * @author 王大宸
     * @date 2024/4/13 23:37
     * @param jwtInfo jwtInfo
     * @return void
     */
    public static void setContextHandler(IJwtInfo jwtInfo) {
        GlobalContextUserDetailsHandler.contextUserHandler(jwtInfo);
    }


    /**
     * 删除当前线程信息
     */
    public static void remove() {
        GlobalContextThreadLocalHandler.remove();
    }

}

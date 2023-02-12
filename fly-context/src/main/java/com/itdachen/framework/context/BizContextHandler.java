package com.itdachen.framework.context;

import com.itdachen.framework.context.constants.UserInfoConstant;
import com.itdachen.framework.context.handler.GlobalContextThreadLocalHandler;

/**
 * Description: 当前线程中全局获取用户信息
 * Created by 王大宸 on 2022-06-28 15:34
 * Created with IntelliJ IDEA.
 */
public class BizContextHandler {

    /**
     * 获取用户id
     */
    public static String getUserId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.USER_ID));
    }

    public static void setUserId(String userId) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.USER_ID, userId);
    }

    /**
     * 获取登录账号
     */
    public static String getAccount() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.USER_ACCOUNT));
    }

    public static void setAccount(String username) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.USER_ACCOUNT, username);
    }

    /**
     * 获取用户姓名
     */
    public static String getUserName() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.USER_NAME));
    }

    public static void setUserName(String name) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.USER_NAME, name);
    }

    /**
     * 获取头像
     */
    public static String getAvatar() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.AVATAR));
    }

    public static void setAvatar(String avatar) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.AVATAR, avatar);
    }

    /**
     * 获取部门id
     */
    public static String getDepartId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.DEPART_ID));
    }

    public static void setDepartId(String depart) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.DEPART_ID, depart);
    }

    /**
     * 获取用户类型
     */
    public static String getUserType() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.USER_TYPE));
    }

    public static void setUserType(String type) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.USER_TYPE, type);
    }

    /**
     * 租户id
     */
    public static String getTenantId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.TENANT_ID));
    }

    public static void setTenantId(String tenantId) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.TENANT_ID, tenantId);
    }

    /**
     * 获取用户联系电话
     */
    public static String getTelephone() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.TELEPHONE));
    }

    public static void setTelephone(String telephone) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.TELEPHONE, telephone);
    }

    /**
     * 获取用户性别
     */
    public static String getSex() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.SEX));
    }

    public static void setSex(String sex) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.SEX, sex);
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


    /**
     * 删除当前线程信息
     */
    public static void remove() {
        GlobalContextThreadLocalHandler.remove();
    }

}

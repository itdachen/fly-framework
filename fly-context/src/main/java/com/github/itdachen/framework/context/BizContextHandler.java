package com.github.itdachen.framework.context;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.handler.GlobalContextThreadLocalHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 当前线程中全局获取用户信息
 * Created by 王大宸 on 2022-06-28 15:34
 * Created with IntelliJ IDEA.
 */
public class BizContextHandler {

    /**
     * 登录客户端
     */
    public static String getClientId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.CLIENT_ID));
    }

    public static void setClientId(String clientId) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.CLIENT_ID, clientId);
    }

    /**
     * 登录方式
     */
    public static String getSignMethod() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.SIGN_METHOD));
    }

    public static void setSignMethod(String signMethod) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.SIGN_METHOD, signMethod);
    }

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
     * 获取用户姓名
     */
    public static String getNickName() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.NICK_NAME));
    }

    public static void setNickName(String name) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.NICK_NAME, name);
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
     * 租户id
     */
    public static String getTenantId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.TENANT_ID));
    }

    public static void setTenantId(String tenantId) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.TENANT_ID, tenantId);
    }


    /**
     * 获取登录账号
     */
    public static String getAccount() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.ACCOUNT));
    }

    public static void setAccount(String username) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.ACCOUNT, username);
    }

    /**
     * 服务提供商ID
     */
    public static String getAppId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.APP_ID));
    }

    public static void setAppId(String avatar) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.APP_ID, avatar);
    }

    /**
     * 服务提供商ID
     */
    public static String getOpenId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.OPEN_ID));
    }

    public static void setOpenId(String avatar) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.OPEN_ID, avatar);
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
     * 获取用户性别
     */
    public static String getSex() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.SEX));
    }

    public static void setSex(String sex) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.SEX, sex);
    }

    /**
     * 获取部门id
     */
    public static String getDeptId() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.DEPT_ID));
    }

    public static void setDeptId(String depart) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.DEPT_ID, depart);
    }

    /**
     * 获取部门名称
     */
    public static String getDeptTitle() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.DEPT_TITLE));
    }

    public static void setDeptTitle(String depart) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.DEPT_TITLE, depart);
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
     * 获取用户邮箱
     */
    public static String getEmail() {
        return GlobalContextThreadLocalHandler.returnObjectValue(GlobalContextThreadLocalHandler.get(UserInfoConstant.E_MAIL));
    }

    public static void setEmail(String telephone) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.E_MAIL, telephone);
    }

    /**
     * 获取用户邮箱
     */
    public static Map<String, String> getOther() {
        return (Map<String, String>) GlobalContextThreadLocalHandler.get(UserInfoConstant.OTHER);
    }

    public static void setOther(Map<String, String> other) {
        GlobalContextThreadLocalHandler.set(UserInfoConstant.OTHER, other);
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


    /**
     * 删除当前线程信息
     */
    public static void remove() {
        GlobalContextThreadLocalHandler.remove();
    }

}

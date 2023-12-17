package com.github.itdachen.framework.context.handler;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;

/***
 * 全局获取用户信息处理
 *
 * @author 王大宸
 * @date 2023/11/27 21:26
 */
public class GlobalContextUserDetailsHandler {


    public static void contextUserHandler(CurrentUserDetails userDetails) {

        /* 账号/客户端 */
        BizContextHandler.setAccount(userDetails.getAccount());
        BizContextHandler.setClientId(userDetails.getClientId());
        BizContextHandler.setSignMethod(userDetails.getSignMethod());

        /* 基础信息 */
        BizContextHandler.setUserId(userDetails.getId());
        BizContextHandler.setNickName(userDetails.getNickName());
        BizContextHandler.setSex(userDetails.getSex());
        BizContextHandler.setAvatar(userDetails.getAvatar());
        BizContextHandler.setTelephone(userDetails.getTelephone());
        BizContextHandler.setEmail(userDetails.getEmail());
        BizContextHandler.setUserType(userDetails.getUserType());
        BizContextHandler.setTenantId(userDetails.getTenantId());

        /* 应用 */
        BizContextHandler.setAppId(userDetails.getAppId());
        BizContextHandler.setOpenId(userDetails.getOpenId());

        /* 身份 */
        BizContextHandler.setAnId(userDetails.getAnId());
        BizContextHandler.setAnTitle(userDetails.getAnTitle());

        /* 部门 */
        BizContextHandler.setDeptId(userDetails.getDeptId());
        BizContextHandler.setDeptTitle(userDetails.getDeptTitle());
        BizContextHandler.setParentDeptId(userDetails.getParentDeptId());
        BizContextHandler.setDeptLevel(userDetails.getDeptLevel());

        BizContextHandler.setOther(userDetails.getOther());
    }


    public static CurrentUserDetails contextUserDetails() {
        CurrentUserDetails userDetails = new CurrentUserDetails();

        /* 账号/客户端 */
        userDetails.setAccount(BizContextHandler.getAccount());
        userDetails.setClientId(BizContextHandler.getClientId());
        userDetails.setSignMethod(BizContextHandler.getSignMethod());

        /* 基础信息 */
        userDetails.setId(BizContextHandler.getUserId());
        userDetails.setNickName(BizContextHandler.getNickName());
        userDetails.setAvatar(BizContextHandler.getAvatar());
        userDetails.setUserType(BizContextHandler.getUserType());
        userDetails.setSex(BizContextHandler.getSex());
        userDetails.setTelephone(BizContextHandler.getTelephone());
        userDetails.setEmail(BizContextHandler.getEmail());
        userDetails.setTenantId(BizContextHandler.getTenantId());

        /* 应用信息 */
        userDetails.setAppId(BizContextHandler.getAppId());
        userDetails.setOpenId(BizContextHandler.getOpenId());

        /* 身份信息 */
        userDetails.setAnId(BizContextHandler.getAnId());
        userDetails.setAnTitle(BizContextHandler.getAnTitle());

        /* 部门信息 */
        userDetails.setDeptId(BizContextHandler.getDeptId());
        userDetails.setDeptTitle(BizContextHandler.getDeptTitle());
        userDetails.setParentDeptId(BizContextHandler.getParentDeptId());
        userDetails.setDeptLevel(BizContextHandler.getDeptLevel());

        userDetails.setOther(BizContextHandler.getOther());
        return userDetails;
    }


}

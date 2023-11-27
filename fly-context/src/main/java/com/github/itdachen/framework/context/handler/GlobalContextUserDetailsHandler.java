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
        BizContextHandler.setAccount(userDetails.getAccount());
        BizContextHandler.setNickName(userDetails.getNickName());
        BizContextHandler.setClientId(userDetails.getClientId());
        BizContextHandler.setSignMethod(userDetails.getSignMethod());
        BizContextHandler.setUserId(userDetails.getId());
        BizContextHandler.setAnId(userDetails.getAnId());
        BizContextHandler.setAnTitle(userDetails.getAnTitle());
        BizContextHandler.setAvatar(userDetails.getAvatar());
        BizContextHandler.setTenantId(userDetails.getTenantId());
        BizContextHandler.setAppId(userDetails.getAppId());
        BizContextHandler.setOpenId(userDetails.getOpenId());
        BizContextHandler.setUserType(userDetails.getUserType());
        BizContextHandler.setSex(userDetails.getSex());
        BizContextHandler.setDeptId(userDetails.getDeptId());
        BizContextHandler.setDeptTitle(userDetails.getDeptTitle());
        BizContextHandler.setTelephone(userDetails.getTelephone());
        BizContextHandler.setEmail(userDetails.getEmail());
        BizContextHandler.setOther(userDetails.getOther());
    }


    public static CurrentUserDetails contextUserDetails() {
        CurrentUserDetails userDetails = new CurrentUserDetails();
        userDetails.setAccount(BizContextHandler.getAccount());
        userDetails.setAccount(BizContextHandler.getAccount());
        userDetails.setNickName(BizContextHandler.getNickName());
        userDetails.setClientId(BizContextHandler.getClientId());
        userDetails.setSignMethod(BizContextHandler.getSignMethod());
        userDetails.setId(BizContextHandler.getUserId());
        userDetails.setAnId(BizContextHandler.getAnId());
        userDetails.setAnTitle(BizContextHandler.getAnTitle());
        userDetails.setAvatar(BizContextHandler.getAvatar());
        userDetails.setTenantId(BizContextHandler.getTenantId());
        userDetails.setAppId(BizContextHandler.getAppId());
        userDetails.setOpenId(BizContextHandler.getOpenId());
        userDetails.setUserType(BizContextHandler.getUserType());
        userDetails.setSex(BizContextHandler.getSex());
        userDetails.setDeptId(BizContextHandler.getDeptId());
        userDetails.setDeptTitle(BizContextHandler.getDeptTitle());
        userDetails.setTelephone(BizContextHandler.getTelephone());
        userDetails.setEmail(BizContextHandler.getEmail());
        userDetails.setOther(BizContextHandler.getOther());
        return userDetails;
    }


}

package com.github.itdachen.framework.cloud.jwt.core;

import java.util.Date;
import java.util.Map;

/**
 * Description: 用户信息
 * Created by 王大宸 on 2023/04/30 14:48
 * Created with IntelliJ IDEA.
 */
public interface IJwtInfo {

    /**
     * 获取用户名/登录账号
     */
    String getUniqueName();

    /**
     * 获取用户ID
     */
    String getUserId();

    /**
     * 获取名称
     */
    String getNickName();

    /**
     * 租户ID
     */
    String getTenantId();

    /**
     * 主体
     */
    String getSubject();

    /**
     * tokenId
     */
    String getTokenId();

    /**
     * 获取 token
     */
    String getToken();

    /**
     * 获取过期时间
     */
    Date getExpireTime();

    /**
     * 获取其他信息
     */
    Map<String, String> getOtherInfo();

}

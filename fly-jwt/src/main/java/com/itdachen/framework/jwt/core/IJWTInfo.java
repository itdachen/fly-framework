package com.itdachen.framework.jwt.core;

import java.util.Date;
import java.util.Map;

/**
 * Description: 从 JWT 中获取用户信息
 * Created by 王大宸 on 2022-06-29 9:54
 * Created with IntelliJ IDEA.
 */
public interface IJWTInfo {

    /**
     * 获取用户名/登录账号
     */
    String getUniqueName();

    /**
     * 获取用户ID
     */
    String getId();

    /**
     * 获取名称
     */
    String getName();

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

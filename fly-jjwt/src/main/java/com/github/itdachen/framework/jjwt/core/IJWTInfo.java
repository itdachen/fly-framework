package com.github.itdachen.framework.jjwt.core;

import java.util.Date;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2023/04/12 22:44
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

    String getTenantId();

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

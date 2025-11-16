package com.github.itdachen.framework.cloud.jwt;

import com.github.itdachen.framework.context.jwt.IJwtInfo;

/**
 * 创建 token
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 21:45
 */
public interface ICryptoTokenHandler {


    /***
     * 创建 token
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 21:48
     * @param jwtInfo    用户信息
     * @return java.lang.String
     */
    String accessToken(IJwtInfo jwtInfo) throws Exception;

    String refreshToken(IJwtInfo jwtInfo) throws Exception;


}

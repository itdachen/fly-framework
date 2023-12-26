package com.github.itdachen.boot.jwt;

import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PrivateKey;

/**
 * 创建 token
 *
 * @author 王大宸
 * @date 2023-12-23 21:45
 */
public interface ICryptoTokenHandler {


    /***
     * 创建 token
     *
     * @author 王大宸
     * @date 2023/12/23 21:48
     * @param jwtInfo    用户信息
     * @param privateKey 私钥
     * @param expires    过期时间
     * @param issuer     发行人
     * @return java.lang.String
     */
    String token(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) throws Exception;


}

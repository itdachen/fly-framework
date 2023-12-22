package com.github.itdachen.framework.cloud.jwt.core.token;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;

import java.security.PrivateKey;

/**
 * 创建 token
 *
 * @author 王大宸
 * @date 2023-12-17 19:31
 */
public interface ICreateTokenHandler {


    String createToken(IJwtInfo jwtInfo,
                       PrivateKey privateKey,
                       Long expires,
                       String issuer);


}

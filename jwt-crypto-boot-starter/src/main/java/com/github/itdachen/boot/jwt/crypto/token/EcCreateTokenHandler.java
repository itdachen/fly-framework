package com.github.itdachen.boot.jwt.crypto.token;

import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PrivateKey;

/**
 * EcCreateTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-23 23:06
 */
public class EcCreateTokenHandler extends AbstractCreateTokenHandler {

    @Override
    public String createToken(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) throws Exception {
        return null;
    }

}

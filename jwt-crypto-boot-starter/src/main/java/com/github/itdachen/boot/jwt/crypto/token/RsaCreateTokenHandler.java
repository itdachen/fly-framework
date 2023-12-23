package com.github.itdachen.boot.jwt.crypto.token;

import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PrivateKey;

/**
 * RsaCreateTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-23 23:07
 */
public class RsaCreateTokenHandler extends AbstractCreateTokenHandler {

    @Override
    public String createToken(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) throws Exception {
        return null;
    }

}

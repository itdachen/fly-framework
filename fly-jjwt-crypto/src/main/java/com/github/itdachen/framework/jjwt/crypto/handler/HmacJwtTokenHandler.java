package com.github.itdachen.framework.jjwt.crypto.handler;

import com.github.itdachen.framework.jjwt.core.IJwtInfo;
import com.github.itdachen.framework.jjwt.core.key.JwtSecretKey;

import java.security.KeyPair;
import java.security.PrivateKey;

/**
 * HmacJwtTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-17 21:08
 */
public class HmacJwtTokenHandler extends AbstractJwtTokenHandler {


    @Override
    protected String createToken(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) {
        return null;
    }

    @Override
    protected JwtSecretKey getJwtSecretKey() throws Exception {
        return null;
    }

}

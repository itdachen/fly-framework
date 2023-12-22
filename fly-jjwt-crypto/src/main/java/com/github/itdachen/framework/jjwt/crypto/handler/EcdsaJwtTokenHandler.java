package com.github.itdachen.framework.jjwt.crypto.handler;

import com.github.itdachen.framework.jjwt.core.IJwtInfo;
import com.github.itdachen.framework.jjwt.core.key.JwtSecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;

/**
 * EcdsaJwtTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-17 21:07
 */
public class EcdsaJwtTokenHandler extends AbstractJwtTokenHandler {
    private final static SignatureAlgorithm ES_ALG = Jwts.SIG.ES512;

    @Override
    protected String createToken(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) {
        return null;
    }

    @Override
    protected JwtSecretKey getJwtSecretKey() throws Exception {
        KeyPair keyPair = ES_ALG.keyPair().build();
        return jwtSecretKey(keyPair);
    }
}

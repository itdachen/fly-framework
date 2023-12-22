package com.github.itdachen.framework.jjwt.crypto.handler;

import com.github.itdachen.framework.jjwt.core.IJwtInfo;
import com.github.itdachen.framework.jjwt.core.key.JwtSecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;

/**
 * RsaJwtTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-17 21:06
 */
public class RsaJwtTokenHandler extends AbstractJwtTokenHandler {

    private final static SignatureAlgorithm RSA_ALG = Jwts.SIG.RS512;//or PS512, RS256, etc...

    @Override
    protected String createToken(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) throws Exception {
        return null;
    }

    @Override
    protected JwtSecretKey getJwtSecretKey() throws Exception {
        KeyPair keyPair = RSA_ALG.keyPair().build();
        return jwtSecretKey(keyPair);
    }



}

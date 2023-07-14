package com.github.itdachen.framework.cloud.jwt.parse.factory.handler;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;

import java.security.PublicKey;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:45
 * Created with IntelliJ IDEA.
 */
public class RsaParseJwtsTokenHandler extends AbstractParseJwtsTokenHandler{
    public static final String ALGORITHM = "RSA";

    @Override
    public IJwtInfo parseToken(String token, String signingKey) throws Exception {
        PublicKey publicKey = generalPublicKey(signingKey);
        return parseIJWTInfoToken(token, publicKey);
    }

    @Override
    protected PublicKey generalPublicKey(String secretKey) throws Exception {
        return generalPublicKey(secretKey, ALGORITHM);
    }
}

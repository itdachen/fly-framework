package com.github.itdachen.boot.jwt.crypto.factory;

import com.github.itdachen.boot.jwt.ICryptoTokenHandler;
import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PrivateKey;

/**
 * CryptoTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-23 23:14
 */
public class CryptoTokenHandler implements ICryptoTokenHandler {

    private final TokenFactory tokenFactory;

    public CryptoTokenHandler(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }


    @Override
    public String createToken(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) throws Exception {
        return tokenFactory.build().createToken(
                jwtInfo,
                privateKey,
                expires,
                issuer
        );
    }
}

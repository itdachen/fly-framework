package com.github.itdachen.framework.cloud.jwt.crypto.factory;

import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties.CloudTokenProperties;
import com.github.itdachen.framework.cloud.jwt.ICryptoTokenHandler;
import com.github.itdachen.framework.cloud.jwt.crypto.AuthTokenSecretKey;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CryptoTokenHandler
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 23:14
 */
public class CryptoTokenHandler implements ICryptoTokenHandler {

    private final TokenFactory tokenFactory;
    private final CloudTokenProperties cloudTokenProperties;

    public CryptoTokenHandler(TokenFactory tokenFactory,
                              CloudTokenProperties cloudTokenProperties) {
        this.tokenFactory = tokenFactory;
        this.cloudTokenProperties = cloudTokenProperties;
    }

    @Autowired
    private AuthTokenSecretKey authTokenSecretKey;


    @Override
    public String accessToken(IJwtInfo jwtInfo) throws Exception {
        String accessToken = tokenFactory.build().token(
                jwtInfo,
                authTokenSecretKey.getPrivateKey(),
                cloudTokenProperties.getExpires(),
                cloudTokenProperties.getIssuer()
        );
        return accessToken;
    }

    @Override
    public String refreshToken(IJwtInfo jwtInfo) throws Exception {
        return tokenFactory.build().token(
                jwtInfo,
                authTokenSecretKey.getPrivateKey(),
                cloudTokenProperties.getExpires() * 5,
                cloudTokenProperties.getIssuer()
        );
    }
}

package com.github.itdachen.cloud.jwt.crypto.factory;

import com.github.itdachen.boot.autoconfigure.cloud.auth.properties.CloudTokenProperties;
import com.github.itdachen.cloud.jwt.ICryptoTokenHandler;
import com.github.itdachen.cloud.jwt.crypto.AuthTokenSecretKey;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CryptoTokenHandler
 *
 * @author 王大宸
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
    public String token(IJwtInfo jwtInfo) throws Exception {
        return tokenFactory.build().token(
                jwtInfo,
                authTokenSecretKey.getPrivateKey(),
                cloudTokenProperties.getExpires(),
                cloudTokenProperties.getIssuer()
        );
    }
}

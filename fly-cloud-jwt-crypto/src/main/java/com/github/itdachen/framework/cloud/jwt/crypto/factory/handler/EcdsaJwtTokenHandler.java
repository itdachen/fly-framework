package com.github.itdachen.framework.cloud.jwt.crypto.factory.handler;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.crypto.key.JwtSecretKey;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:26
 * Created with IntelliJ IDEA.
 */
public class EcdsaJwtTokenHandler extends AbstractJwtTokenHandler {
    // 签名算法
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES512;
    public static final String ALGORITHM = "EC";

    @Override
    public String createToken(IJwtInfo jwtInfo, String signingKey, Long expires, String issuer) throws Exception {
        final PrivateKey privateKey = generalPrivateKey(signingKey);
        return createJwtToken(jwtInfo, privateKey, expires, issuer, signatureAlgorithm);
    }

    @Override
    public JwtSecretKey generationKey() {
        return generationSecretKey(signatureAlgorithm);
    }

    @Override
    protected PrivateKey generalPrivateKey(String secretKey) throws Exception {
        return generalPrivateKey(secretKey, ALGORITHM);
    }

}

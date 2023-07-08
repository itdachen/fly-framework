package com.github.itdachen.framework.jwt.factory.ecdsa;

import com.github.itdachen.framework.autoconfigure.jwt.properties.FlyJwtProperties;
import com.github.itdachen.framework.jwt.core.IJWTInfo;
import com.github.itdachen.framework.jwt.core.JwtSecretKey;
import com.github.itdachen.framework.jwt.factory.handler.JwtTokenHandler;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Description:
 * Created by 王大宸 on 2023/04/23 23:25
 * Created with IntelliJ IDEA.
 */
public class EcdsaJwtTokenHandler extends JwtTokenHandler {
    // 签名算法
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES512;
    public static final String ALGORITHM = "EC";

    public EcdsaJwtTokenHandler(FlyJwtProperties properties) {
        this.properties = properties;
    }


    @Override
    public String createToken(IJWTInfo jwtInfo, String signingKey, Long expires) throws Exception {
        final PrivateKey privateKey = generalPrivateKey(signingKey);
        return createJwtToken(jwtInfo, privateKey, expires, signatureAlgorithm);
    }

    @Override
    public IJWTInfo parseToken(String token, String signingKey) throws Exception {
        PublicKey publicKey = generalPublicKey(signingKey);
        return parseIJWTInfoToken(token, publicKey);
    }

    @Override
    public JwtSecretKey generationKey() {
        return generationSecretKey(signatureAlgorithm);
    }

    @Override
    protected PrivateKey generalPrivateKey(String secretKey) throws Exception {
        return generalPrivateKey(secretKey, ALGORITHM);
    }

    @Override
    protected PublicKey generalPublicKey(String secretKey) throws Exception {
        return generalPublicKey(secretKey, ALGORITHM);
    }

}

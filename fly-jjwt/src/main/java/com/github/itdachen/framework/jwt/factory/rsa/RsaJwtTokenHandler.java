package com.github.itdachen.framework.jwt.factory.rsa;

import com.github.itdachen.framework.autoconfigure.jwt.FlyJwtAutoconfigureProperties;
import com.github.itdachen.framework.jwt.core.IJWTInfo;
import com.github.itdachen.framework.jwt.core.JwtSecretKey;
import com.github.itdachen.framework.jwt.factory.handler.JwtTokenHandler;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Description:
 * Created by 王大宸 on 2023/04/23 23:22
 * Created with IntelliJ IDEA.
 */
public class RsaJwtTokenHandler extends JwtTokenHandler {
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.PS512;
    public static final String ALGORITHM = "RSA";

    public RsaJwtTokenHandler(FlyJwtAutoconfigureProperties properties) {
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

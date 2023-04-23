package com.github.itdachen.framework.jjwt.factory.rsa;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.jjwt.constants.JWTHeader;
import com.github.itdachen.framework.jjwt.core.IJWTInfo;
import com.github.itdachen.framework.jjwt.core.JWTInfo;
import com.github.itdachen.framework.jjwt.core.JwtSecretKey;
import com.github.itdachen.framework.jjwt.factory.handler.JwtTokenHandler;
import com.github.itdachen.framework.jjwt.properties.JJwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2023/04/23 23:22
 * Created with IntelliJ IDEA.
 */
public class RsaJwtTokenHandler extends JwtTokenHandler {
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.PS512;
    public static final String ALGORITHM = "RSA";

    public RsaJwtTokenHandler(JJwtProperties properties) {
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

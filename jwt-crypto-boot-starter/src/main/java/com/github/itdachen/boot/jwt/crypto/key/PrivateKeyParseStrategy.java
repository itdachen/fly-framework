package com.github.itdachen.boot.jwt.crypto.key;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import com.github.itdachen.boot.jwt.ITokenSecretKeyStrategy;
import com.github.itdachen.framework.context.jwt.key.JwtSecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;

/**
 * PrivateKeyParseHandler
 *
 * @author 王大宸
 * @date 2023-12-23 22:18
 */
public class PrivateKeyParseStrategy implements ITokenSecretKeyStrategy {

    private final CloudTokenProperties tokenProperties;

    public PrivateKeyParseStrategy(CloudTokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Override
    public JwtSecretKey getJwtSecretKey() throws Exception {
        SignatureAlgorithm signatureAlgorithm;
        if (JwtTokenEnumType.ECDSA.equals(tokenProperties.getType())) {
            signatureAlgorithm = Jwts.SIG.ES512;
        } else {
            signatureAlgorithm = Jwts.SIG.RS512;
        }

        KeyPair keyPair = signatureAlgorithm.keyPair().build();

        return PrivateKeyUtils.jwtSecretKey(keyPair);
    }

    /***
     * 根据字符串获取私钥
     *
     * @author 王大宸
     * @date 2023/12/23 23:11
     * @param privateKey privateKey
     * @param algorithm algorithm
     * @return java.security.PrivateKey
     */
    @Override
    public PrivateKey privateKey(String privateKey, String algorithm) throws Exception {
        return PrivateKeyUtils.privateKey(privateKey, algorithm);
    }


}

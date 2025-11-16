package com.github.itdachen.framework.cloud.jwt.crypto.key;

import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.enums.JwtTokenEnumType;
import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties.CloudTokenProperties;
import com.github.itdachen.framework.cloud.jwt.ITokenSecretKeyHandler;
import com.github.itdachen.framework.cloud.jwt.contents.SignatureAlgorithmContents;
import com.github.itdachen.framework.context.jwt.key.JwtSecretKey;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;

/**
 * PrivateKeyParseHandler
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 22:18
 */
public class PrivateKeyParseHandler implements ITokenSecretKeyHandler {

    private final CloudTokenProperties tokenProperties;

    public PrivateKeyParseHandler(CloudTokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Override
    public JwtSecretKey getJwtSecretKey() throws Exception {
        SignatureAlgorithm signatureAlgorithm;
        if (JwtTokenEnumType.ECDSA.equals(tokenProperties.getType())) {
            signatureAlgorithm = SignatureAlgorithmContents.ES512;
        } else {
            signatureAlgorithm = SignatureAlgorithmContents.RS512;
        }
        KeyPair keyPair = signatureAlgorithm.keyPair().build();
        return PrivateKeyUtils.jwtSecretKey(keyPair);
    }

    /***
     * 根据字符串获取私钥
     *
     * @author 剑鸣秋朔
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

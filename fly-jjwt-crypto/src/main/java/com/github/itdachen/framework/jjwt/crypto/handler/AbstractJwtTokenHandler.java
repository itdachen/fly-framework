package com.github.itdachen.framework.jjwt.crypto.handler;


import com.github.itdachen.framework.jjwt.core.key.JwtSecretKey;
import com.github.itdachen.framework.jjwt.core.token.AbstractCreateTokenHandler;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * token 加密抽象类
 *
 * @author 王大宸
 * @date 2023-12-17 21:04
 */
public abstract class AbstractJwtTokenHandler extends AbstractCreateTokenHandler {


    /***
     * 秘钥转换
     *
     * @author 王大宸
     * @date 2023/12/17 21:29
     * @param keyPair keyPair
     * @return com.github.itdachen.framework.jjwt.core.key.JwtSecretKey
     */
    protected JwtSecretKey jwtSecretKey(KeyPair keyPair) {
        final PrivateKey privateKey = keyPair.getPrivate();
        final PublicKey publicKey = keyPair.getPublic();

        final String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        final String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return new JwtSecretKey(privateKeyStr, publicKeyStr, privateKey.getAlgorithm());
    }

}

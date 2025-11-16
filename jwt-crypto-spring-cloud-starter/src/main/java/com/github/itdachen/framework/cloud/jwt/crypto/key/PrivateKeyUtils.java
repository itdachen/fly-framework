package com.github.itdachen.framework.cloud.jwt.crypto.key;

import com.github.itdachen.framework.context.jwt.key.JwtSecretKey;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * 私钥工具类
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 22:11
 */
public class PrivateKeyUtils {


    public static PrivateKey privateKey(String privateKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] clear = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(algorithm);
        return fact.generatePrivate(keySpec);
    }


    public static JwtSecretKey jwtSecretKey(KeyPair keyPair) {
        final PrivateKey privateKey = keyPair.getPrivate();
        final PublicKey publicKey = keyPair.getPublic();

        final String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        final String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return new JwtSecretKey(privateKeyStr, publicKeyStr, privateKey.getAlgorithm());
    }

}

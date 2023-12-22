package com.github.itdachen.framework.jjwt.core.token;

import com.github.itdachen.framework.jjwt.core.IJwtInfo;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 解析 token
 *
 * @author 王大宸
 * @date 2023-12-17 19:31
 */
public abstract class AbstractParseTokenHandler {

    /***
     * token 解析
     *
     * @author 王大宸
     * @date 2023/12/17 21:14
     * @param token token
     * @param publicKey publicKey
     * @return com.github.itdachen.framework.jjwt.core.IJwtInfo
     */
    protected abstract IJwtInfo parseToken(String token, PublicKey publicKey) throws Exception;

    /***
     * 获取公钥
     *
     * @author 王大宸
     * @date 2023/12/17 21:14
     * @param key key
     * @param algorithm algorithm
     * @return java.security.PublicKey
     */
    protected PublicKey publicKey(String key, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] clear = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(algorithm);
        return fact.generatePublic(keySpec);
    }

}

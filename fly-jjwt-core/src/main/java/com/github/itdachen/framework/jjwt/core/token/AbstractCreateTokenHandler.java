package com.github.itdachen.framework.jjwt.core.token;

import com.github.itdachen.framework.jjwt.core.IJwtInfo;
import com.github.itdachen.framework.jjwt.core.key.JwtSecretKey;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * 创建 token
 *
 * @author 王大宸
 * @date 2023-12-17 19:31
 */
public abstract class AbstractCreateTokenHandler {

    /***
     * 生成 token
     *
     * @author 王大宸
     * @date 2023/12/17 21:14
     * @param jwtInfo jwtInfo
     * @param privateKey privateKey
     * @param expires expires
     * @param issuer issuer
     * @return java.lang.String
     */
    protected abstract String createToken(IJwtInfo jwtInfo,
                                          PrivateKey privateKey,
                                          Long expires,
                                          String issuer) throws Exception;

    /***
     * 生成 token 加密秘钥
     *
     * @author 王大宸
     * @date 2023/12/17 21:15
     * @return java.security.KeyPair
     */
    protected abstract JwtSecretKey getJwtSecretKey() throws Exception;

    /***
     * 获取私钥
     *
     * @author 王大宸
     * @date 2023/12/17 21:11
     * @param key key
     * @param algorithm algorithm
     * @return java.security.PrivateKey
     */
    public PrivateKey privateKey(String key, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] clear = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(algorithm);
        return fact.generatePrivate(keySpec);
    }


}

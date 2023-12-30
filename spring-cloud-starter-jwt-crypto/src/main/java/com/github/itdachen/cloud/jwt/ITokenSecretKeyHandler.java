package com.github.itdachen.cloud.jwt;

import com.github.itdachen.framework.context.jwt.key.JwtSecretKey;

import java.security.PrivateKey;

/**
 * 初始化公钥和私钥
 *
 * @author 王大宸
 * @date 2023-12-23 21:46
 */
public interface ITokenSecretKeyHandler {

    /***
     * 获取公钥和私钥
     *
     * @author 王大宸
     * @date 2023/12/23 21:49
     * @return com.github.itdachen.framework.context.jwt.key.JwtSecretKey
     */
    JwtSecretKey getJwtSecretKey() throws Exception;

    /***
     * 根据字符串获取私钥
     *
     * @author 王大宸
     * @date 2023/12/23 23:10
     * @param privateKey privateKey
     * @param algorithm algorithm
     * @return java.security.PrivateKey
     */
    PrivateKey privateKey(String privateKey, String algorithm) throws Exception;


}

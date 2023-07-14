package com.github.itdachen.framework.cloud.jwt.parse.factory.handler;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.core.JwtTokenInfo;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:36
 * Created with IntelliJ IDEA.
 */
public abstract class AbstractParseJwtsTokenHandler {

    /***
     * 解析 token
     *
     * @author 王大宸
     * @date 2023/4/30 15:37
     * @param token token
     * @param signingKey 加密 key
     * @return com.github.itdachen.auth.jwts.core.IJwtsInfo
     */
    public abstract IJwtInfo parseToken(String token, String signingKey) throws Exception;


    protected static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /***
     * 加载公钥
     *
     * @author 王大宸
     * @date 2023/4/24 0:22
     * @return java.security.PublicKey
     */
    protected abstract PublicKey generalPublicKey(String secretKey) throws Exception;

    /***
     * 获取公钥
     *
     * @author 王大宸
     * @date 2023/4/30 15:38
     * @param secretKey secretKey
     * @param algorithm algorithm
     * @return java.security.PublicKey
     */
    protected PublicKey generalPublicKey(final String secretKey, final String algorithm) throws Exception {
        byte[] data = Base64.getDecoder().decode(secretKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance(algorithm);
        return fact.generatePublic(spec);
    }

    /***
     * 根据 token 和公钥, 解析用户信息
     *
     * @author 王大宸
     * @date 2023/4/24 0:43
     * @param token token
     * @param publicKey publicKey
     * @return com.github.itdachen.framework.jjwt.core.IJWTInfo
     */
    protected IJwtInfo parseIJWTInfoToken(String token, PublicKey publicKey) {
        Claims body = Jwts.parserBuilder()  // 获取jwts的解析器
                .setSigningKey(publicKey)    // 设置加密后的密钥进行比对
                .build()
                .parseClaimsJws(token) // 解析传入的jwt字符串
                .getBody();  // 拿到claims对象返回

        return parseJWTInfo(body);
    }

    /***
     * 从 claims 返回用户信息
     *
     * @author 王大宸
     * @date 2023/4/24 0:44
     * @param body body
     * @return com.github.itdachen.framework.jjwt.core.IJWTInfo
     */
    protected IJwtInfo parseJWTInfo(Claims body) {
        Map<String, String> otherInfo = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : body.entrySet()) {
            if (Claims.SUBJECT.equals(entry.getKey())
                    || UserInfoConstant.USER_ID.equals(entry.getKey())
                    || UserInfoConstant.NICK_NAME.equals(entry.getKey())
                    || UserInfoConstant.ACCOUNT.equals(entry.getKey())) {
                continue;
            }
            otherInfo.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        otherInfo.put(UserInfoConstant.TOKEN_ID, body.getId());

        return new JwtTokenInfo(getObjectValue(body.get(UserInfoConstant.ACCOUNT)),
                getObjectValue(body.get(UserInfoConstant.USER_ID)),
                getObjectValue(body.get(UserInfoConstant.NICK_NAME)),
                body.getSubject(),
                body.getExpiration(),
                otherInfo);
    }


}

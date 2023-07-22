package com.github.itdachen.framework.jwt.utils;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.core.JwtTokenInfo;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.jwt.utils.RsaKeyHelper;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 非对称性加密(私钥加密 token,公钥解析 token)
 * Created by 王大宸 on 2023-07-22 13:25
 * Created with IntelliJ IDEA.
 */
public class JwtTokenHelper {

    /***
     * 密钥加密token
     *
     * @author 王大宸
     * @date 2022/6/29 11:01
     * @param jwtInfo jwtInfo
     * @param priKey priKey
     * @param expire expire
     * @param otherInfo otherInfo
     * @return java.lang.String
     */
    public static String createToken(IJwtInfo jwtInfo,
                                       byte[] priKey,
                                       Date expire,
                                       String issuer,
                                       Map<String, String> otherInfo) throws Exception {
        JwtBuilder builder = Jwts.builder()
                .setId(jwtInfo.getTokenId())
                .setIssuer(issuer)
                .setExpiration(expire)
                .setSubject(jwtInfo.getUniqueName())
                .claim(UserInfoConstant.USER_ID, jwtInfo.getUserId())
                .claim(UserInfoConstant.NICK_NAME, jwtInfo.getNickName())
                .claim(UserInfoConstant.ACCOUNT, jwtInfo.getUniqueName())
                .claim(UserInfoConstant.TENANT_ID, jwtInfo.getTenantId())
                .claim(UserInfoConstant.EXPIRES_IN, expire.getTime());
        if (null != otherInfo) {
            for (Map.Entry<String, String> entry : otherInfo.entrySet()) {
                builder.claim(entry.getKey(), entry.getValue());
            }
        }

        return builder.signWith(SignatureAlgorithm.RS256, RsaKeyHelper.getPrivateKey(priKey)).compact();
    }

    /***
     * 获取token中的用户信息
     *
     * @author 王大宸
     * @date 2023/7/22 13:44
     * @param token token
     * @param pubKey pubKey
     * @return com.github.itdachen.framework.cloud.jwt.core.IJwtInfo
     */
    public static IJwtInfo parseToken(String token, byte[] pubKey) throws Exception {
        if (token.startsWith(UserInfoConstant.TOKEN_TYPE)) {
            token = token.replace(UserInfoConstant.TOKEN_TYPE, "");
        }
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        Map<String, String> otherInfo = new HashMap<String, String>();
        for (Map.Entry entry : body.entrySet()) {
            if (Claims.SUBJECT.equals(entry.getKey())
                    || UserInfoConstant.USER_ID.equals(entry.getKey())
                    || UserInfoConstant.NICK_NAME.equals(entry.getKey())
                    || UserInfoConstant.EXPIRES_IN.equals(entry.getKey())) {
                continue;
            }
            otherInfo.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        otherInfo.put(UserInfoConstant.TOKEN_ID, body.getId());

        return new JwtTokenInfo(
                getObjectValue(body.get(UserInfoConstant.ACCOUNT)),
                getObjectValue(body.get(UserInfoConstant.USER_ID)),
                getObjectValue(body.get(UserInfoConstant.NICK_NAME)),
                body.getId(),
                body.getSubject(),
                body.getExpiration(),
                otherInfo
        );

    }

    /**
     * 公钥解析token
     *
     * @param token
     * @param pubKey
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
    }

    private static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }


}

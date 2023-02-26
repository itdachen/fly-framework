package com.itdachen.framework.jwt;

import com.itdachen.framework.context.constants.UserInfoConstant;
import com.itdachen.framework.jwt.core.IJWTInfo;
import com.itdachen.framework.jwt.core.JWTInfo;
import com.itdachen.framework.jwt.core.RsaKeyHelper;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 非对称性加密(私钥加密 token,公钥解析 token)
 * Created by 王大宸 on 2022-06-29 10:56
 * Created with IntelliJ IDEA.
 */
public class JWTHelper {

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
    public static String generateToken(IJWTInfo jwtInfo,
                                       byte[] priKey,
                                       Date expire,
                                       Map<String, String> otherInfo) throws Exception {
        JwtBuilder builder = Jwts.builder()
                .setSubject(jwtInfo.getUniqueName())
                .claim(UserInfoConstant.USER_ID, jwtInfo.getId())
                .claim(UserInfoConstant.USER_NAME, jwtInfo.getName())
                .claim(UserInfoConstant.JWT_KEY_EXPIRE, expire.getTime());
        if (null != otherInfo) {
            for (Map.Entry<String, String> entry : otherInfo.entrySet()) {
                builder.claim(entry.getKey(), entry.getValue());
            }
        }
        return builder.signWith(SignatureAlgorithm.RS256,
                        RsaKeyHelper.getPrivateKey(priKey))
                .compact();
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        if (token.startsWith(UserInfoConstant.JWT_TOKEN_TYPE)) {
            token = token.replace(UserInfoConstant.JWT_TOKEN_TYPE, "");
        }
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        Map<String, String> otherInfo = new HashMap<String, String>();
        for (Map.Entry entry : body.entrySet()) {
            if (Claims.SUBJECT.equals(entry.getKey())
                    || UserInfoConstant.USER_ID.equals(entry.getKey())
                    || UserInfoConstant.USER_NAME.equals(entry.getKey())
                    || UserInfoConstant.JWT_KEY_EXPIRE.equals(entry.getKey())) {
                continue;
            }
            otherInfo.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return new JWTInfo(
                body.getSubject(),
                getObjectValue(body.get(UserInfoConstant.USER_ID)),
                getObjectValue(body.get(UserInfoConstant.USER_NAME)),
                new DateTime(body.get(UserInfoConstant.JWT_KEY_EXPIRE)).toDate(),
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

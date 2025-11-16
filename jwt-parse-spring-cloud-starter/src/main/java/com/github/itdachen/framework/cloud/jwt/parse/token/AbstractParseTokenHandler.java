package com.github.itdachen.framework.cloud.jwt.parse.token;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import com.github.itdachen.framework.context.jwt.JwtTokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.PublicKey;
import java.util.*;

/**
 * AbstractParseJwtsTokenHandler
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 21:59
 */
public abstract class AbstractParseTokenHandler {


    public abstract IJwtInfo parse(String token, PublicKey publicKey) throws Exception;


    protected static String getObjectValue(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /***
     * 默认解析token方法
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 22:46
     * @param token token
     * @param publicKey publicKey
     * @return com.github.itdachen.framework.context.jwt.IJwtInfo
     */
    protected IJwtInfo parseToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(publicKey) // <-- 公钥
                .build()
                .parseSignedClaims(token);
        return parseJWTInfo(claimsJws);
    }

    /***
     * 解析 token
     *
     * @author 剑鸣秋朔
     * @date 2023/12/26 22:06
     * @param claimsJws claimsJws
     * @return com.github.itdachen.framework.context.jwt.IJwtInfo
     */
    protected IJwtInfo parseJWTInfo(Jws<Claims> claimsJws) {
        return parseJWTInfo(claimsJws.getPayload());
    }

    /***
     * 解析 token
     *
     * @author 剑鸣秋朔
     * @date 2023/12/26 22:08
     * @param claims claims
     * @return com.github.itdachen.framework.context.jwt.IJwtInfo
     */
    protected IJwtInfo parseJWTInfo(Claims claims) {
        Set<Map.Entry<String, Object>> entries = claims.entrySet();
        Map<String, String> otherInfo = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : entries) {
            if (Claims.SUBJECT.equals(entry.getKey())
                    || Claims.ISSUER.equals(entry.getKey())
                    || Claims.ISSUED_AT.equals(entry.getKey())
                    || Claims.AUDIENCE.equals(entry.getKey())
                    || Claims.EXPIRATION.equals(entry.getKey())
                    || Claims.NOT_BEFORE.equals(entry.getKey())
                    || Claims.ID.equals(entry.getKey())
                    || UserInfoConstant.USER_ID.equals(entry.getKey())
                    || UserInfoConstant.NICK_NAME.equals(entry.getKey())
                    || UserInfoConstant.ACCOUNT.equals(entry.getKey())
                    || UserInfoConstant.TOKEN_ID.equals(entry.getKey())) {
                continue;
            }
            otherInfo.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

//        Date expTime = claims.getExpiration();
//        String subject = claims.getSubject();
//        String account = claims.get(UserInfoConstant.ACCOUNT, String.class);
//        String userID = claims.get(UserInfoConstant.USER_ID, String.class);
//        String nickName = claims.get(UserInfoConstant.NICK_NAME, String.class);
//        String tokenId = claims.get(UserInfoConstant.TOKEN_ID, String.class);


        return new JwtTokenInfo.Builder()
                .username(getObjectValue(claims.get(UserInfoConstant.ACCOUNT)))
                .userId(getObjectValue(claims.get(UserInfoConstant.USER_ID)))
                .nickName(getObjectValue(claims.get(UserInfoConstant.NICK_NAME)))
                .subject(claims.getSubject())
                .tenantId(getObjectValue(claims.get(UserInfoConstant.TENANT_ID)))
                .tokenId(claims.getId())
                .token("")
                .expireTime(claims.getExpiration())
                .otherInfo(otherInfo)
                .build();

//        return new JwtTokenInfo(
//                getObjectValue(claims.get(UserInfoConstant.ACCOUNT)),
//                getObjectValue(claims.get(UserInfoConstant.USER_ID)),
//                getObjectValue(claims.get(UserInfoConstant.NICK_NAME)),
//                claims.getId(),
//                claims.getSubject(),
//                claims.getExpiration(),
//                otherInfo
//        );
    }


}

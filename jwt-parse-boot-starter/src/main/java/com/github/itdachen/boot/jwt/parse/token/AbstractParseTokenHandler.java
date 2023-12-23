package com.github.itdachen.boot.jwt.parse.token;

import com.github.itdachen.boot.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.PublicKey;

/**
 * AbstractParseJwtsTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-23 21:59
 */
public abstract class AbstractParseTokenHandler {


    public abstract IJwtInfo parse(String token, PublicKey publicKey) throws Exception;


    protected static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /***
     * 默认解析token方法
     *
     * @author 王大宸
     * @date 2023/12/23 22:46
     * @param token token
     * @param publicKey publicKey
     * @return com.github.itdachen.framework.context.jwt.IJwtInfo
     */
    protected IJwtInfo parseToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(publicKey) // <-- 公钥
                .build().parseSignedClaims(token);

        return null;
    }

}

package com.github.itdachen.framework.cloud.jwt.parse.factory.handler;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:44
 * Created with IntelliJ IDEA.
 */
public class HmacParseJwtsTokenHandler extends AbstractParseJwtsTokenHandler{

    @Override
    public IJwtInfo parseToken(String token, String signingKey) throws Exception {
        Claims body = Jwts.parserBuilder()//获取jwts的解析器
                .setSigningKey(generalKey(signingKey))    //设置加密后的密钥进行比对
                .build()
                .parseClaimsJws(token) //解析传入的jwt字符串
                .getBody();  // 拿到claims对象返回
        return parseJWTInfo(body);
    }

    @Override
    protected PublicKey generalPublicKey(String secretKey) throws Exception {
        return null;
    }

    public static SecretKey generalKey(String secretKey) {
        //调用base64中的getDecoder方法获取解码器，调用解码器中的decode方法将明文密钥进行编码
        final byte[] decodeKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decodeKey);
    }

}

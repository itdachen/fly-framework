package com.github.itdachen.framework.jjwt.factory.hmac;

import com.github.itdachen.framework.autoconfigure.properties.FlyAutoconfigureProperties;
import com.github.itdachen.framework.jjwt.constants.JWTHeader;
import com.github.itdachen.framework.jjwt.core.IJWTInfo;
import com.github.itdachen.framework.jjwt.core.JwtSecretKey;
import com.github.itdachen.framework.jjwt.factory.handler.JwtTokenHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

/**
 * Description:
 * Created by 王大宸 on 2023/04/23 23:24
 * Created with IntelliJ IDEA.
 */
public class HmacJwtTokenHandler extends JwtTokenHandler {
    // 签名算法
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    public HmacJwtTokenHandler(FlyAutoconfigureProperties properties) {
        this.properties = properties;
    }

    @Override
    public String createToken(IJWTInfo jwtInfo, String signingKey, Long expires) {
        SecretKey secretKey = generalKey(signingKey);

        long nowMillis = System.currentTimeMillis();
        //签发时间
        Date now = new Date(nowMillis);
        if (expires == null) {
            expires = JWT_Default_Expires;
        }
        long expiresTime = nowMillis + expires;
        Date date = new Date(expiresTime);

        Map<String, Object> claimsMap = createClaimsMap(jwtInfo);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam(JWTHeader.TYPE, "JWT") //一下两行就是Header
                .setHeaderParam(JWTHeader.ALGORITHM, signatureAlgorithm.getValue())
                .setClaims(claimsMap)
                .setId(UUID.randomUUID().toString())
                .setSubject(jwtInfo.getSubject()) //主题，可以是json数据
                .setIssuer(properties.getJjwt().getIssuer())   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(secretKey, signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(date);   //设置过期时间
        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    @Override
    public IJWTInfo parseToken(String token, String signingKey) {
        Claims body = Jwts.parserBuilder()//获取jwts的解析器
                .setSigningKey(generalKey(signingKey))    //设置加密后的密钥进行比对
                .build()
                .parseClaimsJws(token) //解析传入的jwt字符串
                .getBody();  // 拿到claims对象返回
        return parseJWTInfo(body);
    }

    @Override
    public JwtSecretKey generationKey() {
        final SecretKey secretKey = io.jsonwebtoken.security.Keys.secretKeyFor(signatureAlgorithm);
        final String secretKeyStr = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return new JwtSecretKey(secretKeyStr, secretKeyStr);
    }

    @Override
    protected PrivateKey generalPrivateKey(String secretKey) throws Exception {
        return null;
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

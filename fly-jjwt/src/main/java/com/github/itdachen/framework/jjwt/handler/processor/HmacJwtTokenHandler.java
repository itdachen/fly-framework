package com.github.itdachen.framework.jjwt.handler.processor;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.jjwt.constants.JWTHeader;
import com.github.itdachen.framework.jjwt.core.IJWTInfo;
import com.github.itdachen.framework.jjwt.handler.JwtTokenHandler;
import com.github.itdachen.framework.jjwt.properties.JJwtProperties;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * Description: Hmac token 生成方式
 * Created by 王大宸 on 2023/04/13 0:33
 * Created with IntelliJ IDEA.
 */
public class HmacJwtTokenHandler extends JwtTokenHandler {
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    public HmacJwtTokenHandler(JJwtProperties properties) {
        this.properties = properties;
    }

    @Override
    public String createToken(IJWTInfo jwtInfo,
                              String signingKey,
                              Long expires) {
        //创建我们将要使用的JWT签名算法对（token）令牌进行签名
        //获取当前时间并转换为date对象
        long nowMillis = System.currentTimeMillis();

        Map<String, String> claimsMap = jwtInfo.getOtherInfo();
        claimsMap.put(UserInfoConstant.USER_ID ,jwtInfo.getId());
        claimsMap.put(UserInfoConstant.USER_NAME ,jwtInfo.getName());
        claimsMap.put(UserInfoConstant.USER_ACCOUNT ,jwtInfo.getUniqueName());
        claimsMap.put(UserInfoConstant.TENANT_ID ,jwtInfo.getUniqueName());

        //签发时间
        Date now = new Date(nowMillis);

        //exprieTime判断用户是否需要设置token有效时间

        //结束时间 = 当前时间 + 设定的有效期时间
        final long expiresTime = nowMillis + expires;
        //转换为date数据类型
        Date date = new Date(expiresTime);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam(JWTHeader.TYPE, "JWT")    //一下两行就是Header
                .setHeaderParam(JWTHeader.ALGORITHM, signatureAlgorithm.getValue())
                .setClaims(claimsMap)
                .setIssuer(properties.getIssuer())   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(generalKey(properties.getSecretKey()), signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(date);   //设置过期时间

        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    @Override
    public IJWTInfo parseToken(String token, String signingKey) {
        return null;
    }


    private SecretKey generalKey(String signingKey) {
        //调用base64中的getDecoder方法获取解码器，调用解码器中的decode方法将明文密钥进行编码
        byte[] decodeKey = Base64.getDecoder().decode(signingKey);
        SecretKey secretKey = Keys.hmacShaKeyFor(decodeKey);
        //返回加密后的密钥
        return secretKey;
    }

}

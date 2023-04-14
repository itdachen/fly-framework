package com.github.itdachen.framework.jjwt.utils;

import com.github.itdachen.framework.jjwt.constants.JWTHeader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: Rsa方式 加密
 * Created by 王大宸 on 2023/04/12 22:40
 * Created with IntelliJ IDEA.
 */
public class JwtRsaUtils {


    //定义默认有效期为10分钟 单位：毫秒
    public static final Long JWT_Default_Expires = 10 * 60 * 1000L;

    //签发者
    public static final String Issuer = "ykq";

    // 签名算法 family为RSA的
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.PS512;

    // 设置RS256私钥
    public static final String JWT_PRIVATE_KEY = "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";

    // 设置RS256公钥
    public static final String JWT_PUBLIC_KEY = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAm5gNfg6RX8Yg6S0Vx+ACc73a5Fi98LbDOAYJWpK+wQgYsPmRq5jqGGmTm+fu17c9u+rQt6jaDgKuxtD2hRAVqFaCxixtBIfb07KPcZ63c5FlRwHCSFR42uFfNzWYf2KWyVUb51CyQus9lKtOqkwJWbBCB6IwPJlCV++39dZIqoGXla89d9UTAjyDo9Hn1h8LrEVnvYqMvCrU3qIHuQAeTnU4+rBxPf/y+sh6rAF1ED1X568R3jCKHcSLNOga8ZrwnQ7on+GZU3YZ8OfceHJiphsNp4J1+rg5xDlz7mgRbigF4SMB0Q2rWC2F95HMEeDzHqdjK8csjYxbGb3OIjc7lEoOrtoeNOlNkow/XOaP/iFqvKK1PVg4ZdxpQZlIPMblgNmQMrWJT8P8JqHKZKVAVxHOXkrhTpAHHNJxCwXd8gGhUjKl1UoufkKWlrfgfYRQ2RBNco0j088ea1pLf8i8I+7a1RCCakEiJV1ZYsTKLJ3Vv09q15hDKtjRFdTf0er9KNwjn4oADFCWN7F5tdSt8QNo60NpSypXvMrHCAbOXLbIq5InT+2Gle80xaHFMs4IL5Ob7elg+tGJhpM9JDZNbjjuGt0KPSbv7gXiR3C/ZY32ShaIIJohoAUwU5aPabDS3B/e+PhIfxCRUf7Pyq70p0UTkcpyk3ptvnC2cI3licECAwEAAQ==";

    /**
     * 创建一个token
     */
    public static String createRsaJWT(String id, String subject, Long exprieTime) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //创建我们将要使用的JWT签名算法对（token）令牌进行签名
        //获取当前时间并转换为date对象
        long nowMillis = System.currentTimeMillis();

        //签发时间
        Date now = new Date(nowMillis);

        //exprieTime判断用户是否需要设置token有效时间
        if (exprieTime == null) {
            //如果为空，将默认有效期赋值给exprieTime
            exprieTime = JWT_Default_Expires;
        }

        //结束时间 = 当前时间 + 设定的有效期时间
        final long expiresTime = nowMillis + exprieTime;
        //转换为date数据类型
        Date date = new Date(expiresTime);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam(JWTHeader.TYPE, "JWT")    //一下两行就是Header
                .setHeaderParam(JWTHeader.ALGORITHM, signatureAlgorithm.getValue())
                .setId(id)
                .setSubject(subject) //主题，可以是json数据
                .setIssuer(Issuer)   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(generalPrivateKey(), signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(date);   //设置过期时间

        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    /**
     * 创建一个token
     */
    public static String createRsaJWT(Map<String, Object> claimsMap, Long exprieTime) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //创建我们将要使用的JWT签名算法对（token）令牌进行签名
        //获取当前时间并转换为date对象
        long nowMillis = System.currentTimeMillis();

        //签发时间
        Date now = new Date(nowMillis);

        //exprieTime判断用户是否需要设置token有效时间
        if (exprieTime == null) {
            //如果为空，将默认有效期赋值给exprieTime
            exprieTime = JWT_Default_Expires;
        }

        //结束时间 = 当前时间 + 设定的有效期时间
        Long expiresTime = nowMillis + exprieTime;
        //转换为date数据类型
        Date date = new Date(expiresTime);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam(JWTHeader.TYPE, "JWT")    //一下两行就是Header
                .setHeaderParam(JWTHeader.ALGORITHM, signatureAlgorithm.getValue())
                .setClaims(claimsMap)
                .setIssuer(Issuer)   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(generalPrivateKey(), signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(date);   //设置过期时间

        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    /**
     * 加载私钥
     */
    public static PrivateKey generalPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] clear = Base64.getDecoder().decode(JWT_PRIVATE_KEY);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        return fact.generatePrivate(keySpec);
    }

    /**
     * 加载公钥
     */
    public static PublicKey generalPublicKey() throws GeneralSecurityException {
        byte[] data = Base64.getDecoder().decode(JWT_PUBLIC_KEY);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        return fact.generatePublic(spec);
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return
     */
    //此处当解析不出的时候会抛出异常
    public static Claims parseRsaJWT(String jwt) throws GeneralSecurityException {
        return Jwts.parserBuilder()//获取jwts的解析器
                .setSigningKey(generalPublicKey())    //设置加密后的密钥进行比对
                .build()
                .parseClaimsJws(jwt)// (jwt)    //解析传入的jwt字符串
                .getBody();     // 拿到claims对象返回
    }

    public static void main(String[] args) throws GeneralSecurityException {

        // RSA算法生成秘钥对的方式
/*      KeyPair keyPair = io.jsonwebtoken.security.Keys.keyPairFor(signatureAlgorithm);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println(privateKeyStr);
        System.out.println("-------------");
        System.out.println(publicKeyStr);*/

        String jwtStr = createRsaJWT("123456", "123456", null);
        Claims claims = parseRsaJWT(jwtStr);
        System.out.println(claims);
        String subject = claims.getSubject();
        System.out.println("token : " + jwtStr);
        System.out.println("subject : " + subject);

        System.out.println("-------------");
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("userId", "123456");
        claimMap.put("userName", "王大宸");
        claimMap.put(Claims.SUBJECT, "sub123");
        String jwtStr2 = createRsaJWT(claimMap, null);
        System.out.println("token : " + jwtStr2);

        Claims claims2 = parseRsaJWT(jwtStr2);
        String subject2 = claims2.getSubject();
        System.out.println(claims2);
        System.out.println("getSubject : " + subject2);
        System.out.println("memId : " + claims2.get("memId"));
        System.out.println("userName : " + claims2.get("userName"));
    }

}

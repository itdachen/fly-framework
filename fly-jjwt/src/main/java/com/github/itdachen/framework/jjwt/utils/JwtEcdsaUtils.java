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
 * Description: Ecdsa方式
 * Created by 王大宸 on 2023/04/12 22:29
 * Created with IntelliJ IDEA.
 */
public class JwtEcdsaUtils {


    //定义默认有效期为10分钟 单位：毫秒
    public static final Long JWT_Default_Expires = 10 * 60 * 1000L;

    //签发者
    public static final String Issuer = "ykq";

    // 签名算法
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES512;

    // 设置RS256私钥
    public static final String JWT_PRIVATE_KEY = "MGACAQAwEAYHKoZIzj0CAQYFK4EEACMESTBHAgEBBEIBDQorF/iUFxmGaBF5ywq6qyFDUT3mEXqob4aETRgQrDa4avs1ollZaeY4lm9L3+e2XcT6lP88UZBgJ98rR8iK2lQ=";

    // 设置RS256公钥
    public static final String JWT_PUBLIC_KEY = "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBkbf4McjTalaTV43KVsFNSXhhxtc69IIzFV7WSdnxZYU5MXwe9uFCR1RGZMgb4W7FmgjUj5oEOqTX4eMhmryEi+wBi8Ha0zugexJYPZOmArjlZeed+lsIyfGuS2B/Yh67DS2ZYSV0oOvAq6eZ9OGp2Vv/aPtn9w2YPyYvHNEeKjLzP1U=";

    /**
     * 创建一个token
     */
    public static String createEcdsaJWT(String id, String subject, Long exprieTime) throws InvalidKeySpecException, NoSuchAlgorithmException {
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
        long expiresTime = nowMillis + exprieTime;
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
    public static String createEcdsaJWT(Map<String, Object> claimsMap, Long exprieTime) throws InvalidKeySpecException, NoSuchAlgorithmException {
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
        long expiresTime = nowMillis + exprieTime;
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
        KeyFactory fact = KeyFactory.getInstance("EC");
        return fact.generatePrivate(keySpec);
    }

    /**
     * 加载公钥
     */
    public static PublicKey generalPublicKey() throws GeneralSecurityException {
        byte[] data = Base64.getDecoder().decode(JWT_PUBLIC_KEY);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance("EC");
        return fact.generatePublic(spec);
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return
     */
    //此处当解析不出的时候会抛出异常
    public static Claims parseEcdsaJWT(String jwt) throws GeneralSecurityException {
        return Jwts.parserBuilder()//获取jwts的解析器
                .setSigningKey(generalPublicKey())    //设置加密后的密钥进行比对
                .build()
                .parseClaimsJws(jwt)// (jwt)    //解析传入的jwt字符串
                .getBody();     // 拿到claims对象返回
    }

    public static void main(String[] args) throws GeneralSecurityException {

        // Ecdsa算法生成秘钥对的方式
/*        KeyPair keyPair = io.jsonwebtoken.security.Keys.keyPairFor(signatureAlgorithm);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println(privateKeyStr);
        System.out.println("-------------");
        System.out.println(publicKeyStr);*/

        String jwtStr = createEcdsaJWT("123456", "123456", null);
        Claims claims = parseEcdsaJWT(jwtStr);
        String subject = claims.getSubject();
        System.out.println(jwtStr + " : " + subject);

        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("memId", "123456");
        claimMap.put(Claims.SUBJECT, "sub123");
        String jwtStr2 = createEcdsaJWT(claimMap, null);
        Claims claims2 = parseEcdsaJWT(jwtStr2);
        String subject2 = claims2.getSubject();
        System.out.println(jwtStr2 + " : " + subject2);
    }


}

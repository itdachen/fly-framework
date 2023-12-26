package com.github.itdachen.boot.jwt;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import com.github.itdachen.framework.context.jwt.JwtTokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * TestJwtUtils
 *
 * @author 王大宸
 * @date 2023-12-17 19:58
 */
public class TestJwtUtils {


    /**
     * 过期时间(单位:秒)
     */
    public static final int ACCESS_EXPIRE = 60;
    /**
     * 加密算法
     */
    private final static MacAlgorithm ALGORITHM = Jwts.SIG.HS256;

    private final static SignatureAlgorithm RSA_ALG = Jwts.SIG.RS512;//or PS512, RS256, etc...

    private final static SignatureAlgorithm ES_ALG = Jwts.SIG.ES512;


    /**
     * 私钥 / 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取，切记这个秘钥不能外露，只在服务端使用，在任何场景都不应该流露出去。
     * 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
     * 应该大于等于 256位(长度32及以上的字符串)，并且是随机的字符串
     */
    private final static String SECRET = "secretK4343543423234459865344457634253252ey";
    /**
     * 秘钥实例
     */
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    /**
     * jwt签发者
     */
    private final static String JWT_ISS = "Tiam";
    /**
     * jwt主题
     */
    private final static String SUBJECT = "Peripherals";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //创建秘钥
        KeyPair keyPair = RSA_ALG.keyPair().build();

        //   KeyPair keyPair =   ES_ALG.keyPair().build();

        final PrivateKey privateKey = keyPair.getPrivate();
        final PublicKey publicKey = keyPair.getPublic();
        final String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        final String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        String algorithm = privateKey.getAlgorithm();


        byte[] clear = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(algorithm);
        PrivateKey privateKey1 = fact.generatePrivate(keySpec);

        System.err.println("privateKey: " + privateKey.equals(privateKey1));

        byte[] clear2 = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec2 = new X509EncodedKeySpec(clear2);
        KeyFactory fact2 = KeyFactory.getInstance(algorithm);
        PublicKey publicKey1 = fact2.generatePublic(keySpec2);

        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("name", "张三");
        claimsMap.put("age", "12");
        claimsMap.put("id", "123456");


        // 使用私钥加密
        String token = Jwts.builder()
                .subject("Alice")
                .issuer("com.github.itdachen")
                .claims(claimsMap)
                .signWith(privateKey1, RSA_ALG) // <-- 私钥
                .compact();

        System.err.println("token: " + token);

        // 使用公钥解密
        String subject = Jwts.parser()
                .verifyWith(publicKey1) // <-- 公钥
                .build().parseSignedClaims(token).getPayload().getSubject();
        System.out.println("Alice".equals(subject));


        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(publicKey1) // <-- 公钥
                .build()
                .parseSignedClaims(token);

        Claims claims = claimsJws.getPayload();

        parseJWTInfo(claims);


//        String name = claims.get("name", String.class);
//        String age = claims.get("age", String.class);
//        String id = claims.get("id", String.class);
//
//
//        System.err.println("name: " + name);
//        System.err.println("age: " + age);
//        System.err.println("id: " + id);


    }


    protected static IJwtInfo parseJWTInfo(Claims claims) {
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

        return new JwtTokenInfo(
                getObjectValue(claims.get(UserInfoConstant.ACCOUNT)),
                getObjectValue(claims.get(UserInfoConstant.USER_ID)),
                getObjectValue(claims.get(UserInfoConstant.NICK_NAME)),
                claims.getId(),
                claims.getSubject(),
                claims.getExpiration(),
                otherInfo
        );
    }

    protected static String getObjectValue(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }


}

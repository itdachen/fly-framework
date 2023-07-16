package com.github.itdachen.framework.cloud.jwt.crypto.factory.handler;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.crypto.constants.JWTHeader;
import com.github.itdachen.framework.cloud.jwt.crypto.key.JwtSecretKey;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.joda.time.DateTime;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:18
 * Created with IntelliJ IDEA.
 */
public abstract class AbstractJwtTokenHandler {
    //定义默认有效期为10分钟 单位：毫秒
    protected static final Long JWT_Default_Expires = 10 * 60 * 1000L;

    /***
     * 创建 token
     *
     * @author 王大宸
     * @date 2023/4/12 23:17
     * @param jwtInfo     jwtInfo
     * @param signingKey  加密 key
     * @param expires     有效时间, 单位: 毫秒
     * @param issuer      签发者
     * @return java.lang.String
     */
    public abstract String createToken(IJwtInfo jwtInfo,
                                       String signingKey,
                                       Long expires,
                                       String issuer) throws Exception;


    /***
     * 生成秘钥
     *
     * @author 王大宸
     * @date 2023/4/23 23:29
     * @return com.github.itdachen.framework.jjwt.core.JwtSecretKey
     */
    public abstract JwtSecretKey generationKey();

    /***
     * 默认生成私钥/公钥方式
     *
     * @author 王大宸
     * @date 2023/4/23 23:36
     * @param signatureAlgorithm signatureAlgorithm
     * @return com.github.itdachen.framework.jjwt.core.JwtSecretKey
     */
    protected JwtSecretKey generationSecretKey(SignatureAlgorithm signatureAlgorithm) {
        final KeyPair keyPair = Keys.keyPairFor(signatureAlgorithm);
        final PrivateKey privateKey = keyPair.getPrivate();
        final PublicKey publicKey = keyPair.getPublic();
        final String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        final String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return new JwtSecretKey(privateKeyStr, publicKeyStr);
    }

    /***
     * 过期时间处理
     *
     * @author 王大宸
     * @date 2023/4/12 23:14
     * @param expires 过期时间, 单位: 毫秒(1000 毫秒等于一秒)
     * @return java.util.Date
     */
    protected Date expireTime(Integer expires) {
        return DateTime.now().plusSeconds(expires).toDate();
    }

    protected static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /***
     *  加载私钥
     *
     * @author 王大宸
     * @date 2023/4/24 0:22
     * @return java.security.PrivateKey
     */
    protected abstract PrivateKey generalPrivateKey(String secretKey) throws Exception;


    /***
     * 获取私钥
     *
     * @author 王大宸
     * @date 2023/4/24 1:00
     * @param secretKey secretKey
     * @param algorithm algorithm
     * @return java.security.PrivateKey
     */
    protected PrivateKey generalPrivateKey(final String secretKey, final String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] clear = Base64.getDecoder().decode(secretKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(algorithm);
        return fact.generatePrivate(keySpec);
    }


    /***
     * 根据用户信息和私钥, 加密 token
     *
     * @author 王大宸
     * @date 2023/4/24 0:43
     * @param jwtInfo jwtInfo
     * @param privateKey privateKey
     * @param expires expires
     * @param signatureAlgorithm signatureAlgorithm
     * @return java.lang.String
     */
    protected String createJwtToken(IJwtInfo jwtInfo,
                                    PrivateKey privateKey,
                                    Long expires,
                                    String issuer,
                                    SignatureAlgorithm signatureAlgorithm) {
        long nowMillis = System.currentTimeMillis();
        //签发时间
        Date now = new Date(nowMillis);
        if (null == expires) {
            expires = JWT_Default_Expires;
        }
        long expiresTime = nowMillis + expires;
        Date date = new Date(expiresTime);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam(JWTHeader.TYPE, "JWT")    //一下两行就是Header
                .setHeaderParam(JWTHeader.ALGORITHM, signatureAlgorithm.getValue())
                .setClaims(createClaimsMap(jwtInfo))
                .setIssuer(issuer)   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(privateKey, signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(date);   //设置过期时间

        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    /***
     * 将用户信息封装成 Map
     *
     * @author 王大宸
     * @date 2023/4/24 0:45
     * @param jwtInfo jwtInfo
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    protected Map<String, Object> createClaimsMap(IJwtInfo jwtInfo) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(UserInfoConstant.USER_ID, jwtInfo.getUserId());
        claimsMap.put(UserInfoConstant.NICK_NAME, jwtInfo.getNickName());
        claimsMap.put(UserInfoConstant.ACCOUNT, jwtInfo.getUniqueName());
        claimsMap.put(UserInfoConstant.TENANT_ID, jwtInfo.getTenantId());

        Map<String, String> otherInfo = jwtInfo.getOtherInfo();

        claimsMap.put(UserInfoConstant.USER_TYPE, otherInfo.get(UserInfoConstant.USER_TYPE));

        claimsMap.putAll(jwtInfo.getOtherInfo());
        return claimsMap;
    }


}

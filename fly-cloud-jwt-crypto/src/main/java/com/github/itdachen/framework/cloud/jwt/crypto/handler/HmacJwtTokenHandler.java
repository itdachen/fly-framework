package com.github.itdachen.framework.cloud.jwt.crypto.handler;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.crypto.constants.JWTHeader;
import com.github.itdachen.framework.cloud.jwt.crypto.key.JwtSecretKey;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:27
 * Created with IntelliJ IDEA.
 */
public class HmacJwtTokenHandler extends AbstractJwtTokenHandler {
    // 签名算法
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    @Override
    public String createToken(IJwtInfo jwtInfo, String signingKey, Long expires, String issuer) {
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
                .setIssuer(issuer)   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(secretKey, signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(date);   //设置过期时间
        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    @Override
    public JwtSecretKey generationKey() {
        return null;
    }

    @Override
    protected PrivateKey generalPrivateKey(String secretKey) throws Exception {
        return null;
    }

    public static SecretKey generalKey(String secretKey) {
        //调用base64中的getDecoder方法获取解码器，调用解码器中的decode方法将明文密钥进行编码
        final byte[] decodeKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decodeKey);
    }

}

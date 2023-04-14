package com.github.itdachen.framework.jjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Description: JWT 工具类, 适合单体项目
 * Created by 王大宸 on 2023/04/13 0:17
 * Created with IntelliJ IDEA.
 */
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    /***
     * 生成token
     *
     * @author 王大宸
     * @date 2023/4/13 0:20
     * @param secretKey secretKey
     * @param header header
     * @param claims claims
     * @param expireDate expireDate
     * @return java.lang.String
     */
    public static String createToken(String secretKey,
                                     Map<String, Object> header,
                                     Map<String, Object> claims,
                                     Date expireDate) {
        // Date expireDate = DateUtils.addHours(new Date(), 24);
        if (Objects.isNull(claims)) {
            logger.info("载荷内容 [claims] 不能为空");
            return null;
        }

        try {
            Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
            JwtBuilder builder = Jwts.builder().addClaims(claims);
            if (Objects.nonNull(header)) {
                builder.setHeader(header);
            }
            if (Objects.nonNull(expireDate)) {
                builder.setExpiration(expireDate);
            }
            return builder.signWith(key).compact();
        } catch (Exception ex) {
            logger.error("生成 JWT Token 错误", ex);
        }
        return null;
    }

    /***
     * 解析token
     *
     * @author 王大宸
     * @date 2023/4/13 0:21
     * @param secretKey secretKey
     * @param token token
     * @return io.jsonwebtoken.Claims
     */
    public static Claims decodeToken(String secretKey,
                                     String token) {
        try {
            Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            logger.error("解析 JWT Token 错误", ex);
        }
        return null;
    }


}

package com.github.itdachen.framework.jwt.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * Created by 王大宸 on 2023/01/05 14:06
 * Created with IntelliJ IDEA.
 */
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 生成token
     *
     * @param secretKey
     * @param header
     * @param claims
     * @param expireDate
     * @return
     */
//    public static String createToken(String secretKey,
//                                     Map<String, Object> header,
//                                     Map<String, Object> claims,
//                                     Date expireDate) {
//        if (Objects.isNull(claims)) {
//            logger.info("载荷内容 [claims] 不能为空");
//            return null;
//        }
//
//        try {
//            Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
//            JwtBuilder builder = Jwts.builder()
//                    .addClaims(claims);
//            if (Objects.nonNull(header)) {
//                builder.setHeader(header);
//            }
//            if (Objects.nonNull(expireDate)) {
//                builder.setExpiration(expireDate);
//            }
//            return builder.signWith(key).compact();
//        } catch (Exception ex) {
//            logger.error("生成 JWT Token 错误", ex);
//            System.out.println(ex);
//        }
//        return null;
//    }

    /**
     * 解析token
     *
     * @param secretKey
     * @param token
     * @return
     */
//    public static Claims decodeToken(String secretKey,
//                                     String token) {
//        try {
//            Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
//            return Jwts.parserBuilder().setSigningKey(key)
//                    .build().parseClaimsJws(token).getBody();
//        } catch (Exception ex) {
//            logger.error("解析 JWT Token 错误", ex);
//        }
//        return null;
//    }


}

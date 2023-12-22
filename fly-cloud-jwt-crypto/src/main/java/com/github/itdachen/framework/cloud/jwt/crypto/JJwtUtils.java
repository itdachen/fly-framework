package com.github.itdachen.framework.cloud.jwt.crypto;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import javax.crypto.SecretKey;
import java.util.Date;


/**
 * JJwtUtils
 *
 * @author 王大宸
 * @date 2023-12-17 19:11
 */
public class JJwtUtils {

    // 创建token密钥的key,并且使用 HMAC-SHA-256 加密算法
    private static SecretKey key = Jwts.SIG.HS256.key().build();

    /**
     * 生成token
     * @return
     */
    public static String genToken(){
        // 2. 构建jwt,将签发人设置为joe,并且使用密钥将签名jwt生成为jws
        String jws =  Jwts.builder().
                subject("Joe").  // setSubject 设置jwt针对的用户
                        issuer("张三").   // issuer 签发人
                        claim("name","zhangsan"). // 自定义载荷数据
                        claim("role","admin"). // 自定义载荷数据
                        signWith(key).   // token加签认证
                        expiration(new Date(System.currentTimeMillis()+7200*1000)). // 设置token过期时间为2H
                        compact();  // 生成token字符串
        return jws;
    }

    /**
     * token 校验
     * @param key  密钥
     * @param token jws
     * @return
     */
    public static String checkToken(SecretKey key,String token ){
        String msg = null;
        try {
            Jws<Claims> claimsJws = Jwts.parser().
                    verifyWith(key).
                    build().
                    parseSignedClaims(token);
            // 获取载荷的一些数据信息
            Claims payload = claimsJws.getPayload(); // payload 为一个map对象
            String issuer = payload.getIssuer();
            Date expiration = payload.getExpiration();
            String name = (String)payload.get("name");
            String role = (String)payload.get("role");
            


            // 测试就直接打印出去了...
            System.out.println("标准载荷：issuer===>"+issuer+"\texpiration=>"+expiration + "自定义载荷数据："+name+"\t"+role);
            return "token正确";
        } catch (SignatureException se) {
            msg = "密钥错误";
            return  msg;
        }catch (MalformedJwtException me) {
            msg = "密钥算法或者密钥转换错误";
            return  msg;
        }catch (MissingClaimException mce) {
            msg = "密钥缺少校验数据";
            return  msg;
        }catch (ExpiredJwtException mce) {
            msg = "密钥已过期";
            return  msg;
        }catch (JwtException jwte) {
            msg = "密钥解析错误";
            return  msg;
        }
    }


    /**
     * 	测试
     */
    public static void main(String[] args) {
        String token = genToken();
        System.out.println(token);
        // 断言测试
        // assert Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject().equals("Joe");
        String res = checkToken(key, token);
        System.out.println(res);
    }

}

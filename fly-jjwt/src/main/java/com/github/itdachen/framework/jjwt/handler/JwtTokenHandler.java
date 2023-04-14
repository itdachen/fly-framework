package com.github.itdachen.framework.jjwt.handler;

import com.github.itdachen.framework.jjwt.core.IJWTInfo;
import com.github.itdachen.framework.jjwt.properties.JJwtProperties;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;

/**
 * Description: 生成 token ,解析 token
 * Created by 王大宸 on 2023/04/12 23:06
 * Created with IntelliJ IDEA.
 */
public abstract class JwtTokenHandler {
    protected JJwtProperties properties;
    protected String issuer;

    /***
     * 创建 token
     *
     * @author 王大宸
     * @date 2023/4/12 23:17
     * @param jwtInfo     jwtInfo
     * @param signingKey  加密 key
     * @param expires     有效时间, 单位: 毫秒
     * @return java.lang.String
     */
    public abstract String createToken(IJWTInfo jwtInfo,
                                       String signingKey,
                                       Long expires);


    /***
     * 解析 token
     *
     * @author 王大宸
     * @date 2023/4/12 23:17
     * @param token token
     * @param signingKey 加密 key
     * @return com.github.itdachen.framework.jjwt.core.IJWTInfo
     */
    public abstract IJWTInfo parseToken(String token, String signingKey);


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

}

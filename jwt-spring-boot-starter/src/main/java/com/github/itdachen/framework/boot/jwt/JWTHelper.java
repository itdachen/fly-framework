package com.github.itdachen.framework.boot.jwt;

import com.github.itdachen.framework.boot.autoconfigure.jwt.JwtAutoconfigureProperties;
import com.github.itdachen.framework.boot.jwt.core.AuthKeyConfiguration;
import com.github.itdachen.framework.boot.jwt.utils.JwtTokenHelper;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Description: 加密 / 解密处理
 * Created by 王大宸 on 2022-06-29 10:56
 * Created with IntelliJ IDEA.
 */
@Configuration
public class JWTHelper {

    private final AuthKeyConfiguration keyConfiguration;
    private final JwtAutoconfigureProperties properties;

    public JWTHelper(AuthKeyConfiguration keyConfiguration,
                     JwtAutoconfigureProperties properties) {
        this.keyConfiguration = keyConfiguration;
        this.properties = properties;
    }

    /***
     * 生成 token
     *
     * @author 王大宸
     * @date 2023/7/22 13:47
     * @param jwtInfo jwtInfo
     * @return java.lang.String
     */
    public String createToken(IJwtInfo jwtInfo) throws Exception {
        return JwtTokenHelper.createToken(
                jwtInfo,
                keyConfiguration.getUserPriKey(),
                DateTime.now().plusSeconds(properties.getExpire()).toDate(),
                properties.getIssuer(),
                jwtInfo.getOtherInfo()
        );
    }

    public String createToken(IJwtInfo jwtInfo, byte[] priKey) throws Exception {
        return JwtTokenHelper.createToken(
                jwtInfo,
                priKey,
                DateTime.now().plusSeconds(properties.getExpire()).toDate(),
                properties.getIssuer(),
                jwtInfo.getOtherInfo()
        );
    }

    /***
     * 生成 token
     *
     * @author 王大宸
     * @date 2023/7/22 13:47
     * @param jwtInfo jwtInfo
     * @param date date
     * @return java.lang.String
     */
    public String createToken(IJwtInfo jwtInfo, Date date) throws Exception {
        return JwtTokenHelper.createToken(
                jwtInfo,
                keyConfiguration.getUserPriKey(),
                date,
                properties.getIssuer(),
                jwtInfo.getOtherInfo()
        );
    }

    public String createToken(IJwtInfo jwtInfo, byte[] priKey, Date date) throws Exception {
        return JwtTokenHelper.createToken(
                jwtInfo,
                priKey,
                date,
                properties.getIssuer(),
                jwtInfo.getOtherInfo()
        );
    }

    /***
     * 解析 token
     *
     * @author 王大宸
     * @date 2023/7/22 13:47
     * @param token token
     * @return com.github.itdachen.framework.cloud.jwt.core.IJwtInfo
     */
    public IJwtInfo parseToken(String token) throws Exception {
        return JwtTokenHelper.parseToken(token, keyConfiguration.getUserPubKey());
    }

    /***
     * 解析 token
     *
     * @author 王大宸
     * @date 2023/7/22 15:14
     * @param token token
     * @param pubKey pubKey
     * @return com.github.itdachen.framework.cloud.jwt.core.IJwtInfo
     */
    public IJwtInfo parseToken(String token, byte[] pubKey) throws Exception {
        return JwtTokenHelper.parseToken(token, pubKey);
    }


}

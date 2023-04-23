package com.github.itdachen.framework.jjwt;

import com.github.itdachen.framework.jjwt.core.IJWTInfo;
import com.github.itdachen.framework.jjwt.factory.JwtTokenFactory;
import org.springframework.stereotype.Component;

/**
 * Description: 生成 token / 解析 token
 * Created by 王大宸 on 2023/04/12 22:43
 * Created with IntelliJ IDEA.
 */
@Component
public class JWTHelper {

    private final JwtTokenFactory tokenFactory;

    public JWTHelper(JwtTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    /***
     * 加密返回 token
     *
     * @author 王大宸
     * @date 2023/4/12 22:53
     * @param jwtInfo    jwtInfo
     * @param signingKey 加密 key
     * @param expires    有效时间, 单位: 毫秒
     * @return java.lang.String
     */
    public String createToken(IJWTInfo jwtInfo,
                              String signingKey,
                              Long expires) throws Exception {
        return tokenFactory.build().createToken(jwtInfo, signingKey, expires);
    }

    /***
     * 解析 token
     *
     * @author 王大宸
     * @date 2023/4/12 22:55
     * @param token token
     * @param signingKey signingKey
     * @return com.github.itdachen.framework.jjwt.core.IJWTInfo
     */
    public IJWTInfo parseToken(String token, String signingKey) throws Exception {
        return tokenFactory.build().parseToken(token, signingKey);
    }

}

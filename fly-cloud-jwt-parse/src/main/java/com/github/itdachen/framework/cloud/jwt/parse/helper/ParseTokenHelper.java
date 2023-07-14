package com.github.itdachen.framework.cloud.jwt.parse.helper;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.parse.factory.ParseTokenFactory;
import org.springframework.stereotype.Component;

/**
 * Description: token 解析
 * Created by 王大宸 on 2023/04/30 15:34
 * Created with IntelliJ IDEA.
 */
@Component
public class ParseTokenHelper {

    private final ParseTokenFactory tokenFactory;

    public ParseTokenHelper(ParseTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }


    /***
     * 解析 token
     *
     * @author 王大宸
     * @date 2023/4/30 15:34
     * @param token token
     * @param signingKey signingKey
     * @return com.github.itdachen.auth.jwts.core.IJwtsInfo
     */
    public IJwtInfo parseToken(String token, String signingKey) throws Exception {
        return tokenFactory.build().parseToken(token, signingKey);
    }


}

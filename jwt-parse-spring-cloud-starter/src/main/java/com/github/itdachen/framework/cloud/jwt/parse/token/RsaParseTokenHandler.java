package com.github.itdachen.framework.cloud.jwt.parse.token;

import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PublicKey;

/**
 * RsaJwtTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-23 22:16
 */
public class RsaParseTokenHandler extends AbstractParseTokenHandler{


    @Override
    public IJwtInfo parse(String token, PublicKey publicKey) throws Exception {
        return parseToken(token, publicKey);
    }


}

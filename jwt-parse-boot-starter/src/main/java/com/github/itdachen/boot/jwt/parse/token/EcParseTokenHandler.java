package com.github.itdachen.boot.jwt.parse.token;

import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PublicKey;

/**
 * EcdsaJwtTokenHandler
 *
 * @author 王大宸
 * @date 2023-12-23 22:17
 */
public class EcParseTokenHandler extends AbstractParseTokenHandler {


    @Override
    public IJwtInfo parse(String token, PublicKey publicKey) throws Exception {
        IJwtInfo iJwtInfo = parseToken(token, publicKey);
        return null;
    }


}

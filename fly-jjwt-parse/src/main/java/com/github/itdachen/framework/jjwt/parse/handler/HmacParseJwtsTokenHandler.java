package com.github.itdachen.framework.jjwt.parse.handler;

import com.github.itdachen.framework.jjwt.core.IJwtInfo;

import java.security.PublicKey;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:44
 * Created with IntelliJ IDEA.
 */
public class HmacParseJwtsTokenHandler extends AbstractParseJwtsTokenHandler {

    @Override
    protected IJwtInfo parseToken(String token, PublicKey publicKey) throws Exception {
        return null;
    }

}

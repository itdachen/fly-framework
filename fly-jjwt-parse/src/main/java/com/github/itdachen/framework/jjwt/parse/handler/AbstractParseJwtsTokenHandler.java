package com.github.itdachen.framework.jjwt.parse.handler;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.jjwt.core.IJwtInfo;
import com.github.itdachen.framework.jjwt.core.JwtTokenInfo;
import com.github.itdachen.framework.jjwt.core.token.AbstractParseTokenHandler;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:36
 * Created with IntelliJ IDEA.
 */
public abstract class AbstractParseJwtsTokenHandler extends AbstractParseTokenHandler {


    protected static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }





}

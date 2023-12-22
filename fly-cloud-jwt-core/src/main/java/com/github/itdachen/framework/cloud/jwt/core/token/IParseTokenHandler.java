package com.github.itdachen.framework.cloud.jwt.core.token;

import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;

import java.security.PublicKey;

/**
 * 解析 token
 *
 * @author 王大宸
 * @date 2023-12-17 19:31
 */
public interface IParseTokenHandler {

    IJwtInfo parser(String token, PublicKey publicKey) throws Exception;


}

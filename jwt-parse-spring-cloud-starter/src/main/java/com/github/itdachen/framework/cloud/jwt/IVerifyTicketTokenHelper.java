package com.github.itdachen.framework.cloud.jwt;

import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PublicKey;

/**
 * token 解析
 *
 * @author 王大宸
 * @date 2023-12-23 21:56
 */
public interface IVerifyTicketTokenHelper {


    IJwtInfo parseToken(String token) throws Exception;

    void parse(String token) throws Exception;

}

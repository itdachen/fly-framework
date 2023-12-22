package com.github.itdachen.framework.jjwt.parse;

import java.security.PublicKey;

/**
 * IVerifyTicketPublicKeyHelper
 *
 * @author 王大宸
 * @date 2023-12-17 22:14
 */
public interface IVerifyTicketPublicKeyHelper {


    PublicKey publicKey(String key, String algorithm) throws Exception;

}

package com.github.itdachen.framework.cloud.jwt;

import java.security.PublicKey;

/**
 * 解析公钥
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 21:57
 */
public interface IVerifyTicketPublicKeyHelper {

    PublicKey publicKey(String key, String algorithm) throws Exception;

}

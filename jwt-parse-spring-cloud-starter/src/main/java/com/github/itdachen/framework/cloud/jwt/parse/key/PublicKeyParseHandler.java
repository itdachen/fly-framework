package com.github.itdachen.framework.cloud.jwt.parse.key;

import com.github.itdachen.framework.cloud.jwt.IVerifyTicketPublicKeyHelper;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 公钥解析
 *
 * @author 王大宸
 * @date 2023-12-23 22:17
 */
public class PublicKeyParseHandler implements IVerifyTicketPublicKeyHelper {


    @Override
    public PublicKey publicKey(String key, String algorithm) throws Exception {
        byte[] clear = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(algorithm);
        return fact.generatePublic(keySpec);
    }


}

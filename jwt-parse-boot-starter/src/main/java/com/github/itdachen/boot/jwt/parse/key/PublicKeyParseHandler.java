package com.github.itdachen.boot.jwt.parse.key;

import com.github.itdachen.boot.jwt.IVerifyTicketPublicKeyHelper;

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
        byte[] clear2 = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec2 = new X509EncodedKeySpec(clear2);
        KeyFactory fact2 = KeyFactory.getInstance(algorithm);
        return fact2.generatePublic(keySpec2);
    }


}

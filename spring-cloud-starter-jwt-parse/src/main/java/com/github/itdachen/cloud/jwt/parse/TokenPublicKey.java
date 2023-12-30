package com.github.itdachen.cloud.jwt.parse;

import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

/**
 * 存放解析 token 公钥
 *
 * @author 王大宸
 * @date 2023-12-28 20:07
 */
@Configuration
public class TokenPublicKey {


    private PublicKey publicKey;

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

}

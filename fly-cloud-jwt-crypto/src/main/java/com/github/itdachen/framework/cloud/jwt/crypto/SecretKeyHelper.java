package com.github.itdachen.framework.cloud.jwt.crypto;

import com.github.itdachen.framework.cloud.jwt.crypto.factory.JwtsTokenFactory;
import com.github.itdachen.framework.cloud.jwt.crypto.key.JwtSecretKey;
import org.springframework.stereotype.Component;

/**
 * Description: 生成 token 私钥/公钥
 * Created by 王大宸 on 2023/04/30 15:12
 * Created with IntelliJ IDEA.
 */
@Component
public class SecretKeyHelper {
    private final JwtsTokenFactory tokenFactory;

    public SecretKeyHelper(JwtsTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    /***
     * 生成私钥/公钥
     *
     * @author 王大宸
     * @date 2023/4/30 15:31
     * @return com.github.itdachen.auth.jwts.crypto.key.JwtSecretKey
     */
    public JwtSecretKey secretKey() {
        return tokenFactory.build().generationKey();
    }

}

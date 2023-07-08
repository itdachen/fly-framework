package com.github.itdachen.framework.jwt;

import com.github.itdachen.framework.jwt.core.JwtSecretKey;
import com.github.itdachen.framework.jwt.factory.JwtTokenFactory;
import org.springframework.stereotype.Component;

/**
 * Description: 对外加密获取私钥/公钥
 * Created by 王大宸 on 2023/04/23 22:31
 * Created with IntelliJ IDEA.
 */
@Component
public class SecretKeyHelper {

    private final JwtTokenFactory tokenFactory;

    public SecretKeyHelper(JwtTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    /***
     * 生成私钥/公钥
     *
     * @author 王大宸
     * @date 2023/4/23 23:41
     * @return com.github.itdachen.framework.jjwt.core.JwtSecretKey
     */
    public JwtSecretKey secretKey() {
        return tokenFactory.build().generationKey();
    }


}

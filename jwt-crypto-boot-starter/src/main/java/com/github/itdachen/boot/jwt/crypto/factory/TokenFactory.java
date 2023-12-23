package com.github.itdachen.boot.jwt.crypto.factory;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import com.github.itdachen.boot.jwt.crypto.token.AbstractCreateTokenHandler;
import com.github.itdachen.boot.jwt.crypto.token.EcCreateTokenHandler;
import com.github.itdachen.boot.jwt.crypto.token.RsaCreateTokenHandler;

/**
 * 创建 token 工厂
 *
 * @author 王大宸
 * @date 2023-12-23 21:52
 */
public class TokenFactory {

    private final CloudTokenProperties properties;

    public TokenFactory(CloudTokenProperties properties) {
        this.properties = properties;
    }


    public AbstractCreateTokenHandler build() {
        if (JwtTokenEnumType.ECDSA.equals(properties.getType())) {
            return new EcCreateTokenHandler();
        } else {
            return new RsaCreateTokenHandler();
        }
    }


}

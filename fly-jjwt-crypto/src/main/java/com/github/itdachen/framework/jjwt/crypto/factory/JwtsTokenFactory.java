package com.github.itdachen.framework.jjwt.crypto.factory;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import com.github.itdachen.framework.jjwt.crypto.handler.AbstractJwtTokenHandler;
import com.github.itdachen.framework.jjwt.crypto.handler.EcdsaJwtTokenHandler;
import com.github.itdachen.framework.jjwt.crypto.handler.HmacJwtTokenHandler;
import com.github.itdachen.framework.jjwt.crypto.handler.RsaJwtTokenHandler;
import org.springframework.stereotype.Component;

/**
 * Description: token 生成方式
 * Created by 王大宸 on 2023/04/30 15:07
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtsTokenFactory {

    private final CloudTokenProperties properties;

    public JwtsTokenFactory(CloudTokenProperties properties) {
        this.properties = properties;
    }


    public AbstractJwtTokenHandler build() throws Exception {
        if (JwtTokenEnumType.ECDSA.equals(properties.getType())) {
            return new EcdsaJwtTokenHandler();
        } else if (JwtTokenEnumType.HMAC.equals(properties.getType())) {
            return new HmacJwtTokenHandler();
        } else if (JwtTokenEnumType.RSA.equals(properties.getType())) {
            return new RsaJwtTokenHandler();
        } else {
            return new HmacJwtTokenHandler();
        }
    }

}

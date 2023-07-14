package com.github.itdachen.framework.cloud.jwt.crypto.factory;

import com.github.itdachen.framework.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyCloudTokenProperties;
import com.github.itdachen.framework.cloud.jwt.crypto.factory.handler.AbstractJwtTokenHandler;
import com.github.itdachen.framework.cloud.jwt.crypto.factory.handler.EcdsaJwtTokenHandler;
import com.github.itdachen.framework.cloud.jwt.crypto.factory.handler.HmacJwtTokenHandler;
import com.github.itdachen.framework.cloud.jwt.crypto.factory.handler.RsaJwtTokenHandler;
import org.springframework.stereotype.Component;

/**
 * Description: token 生成方式
 * Created by 王大宸 on 2023/04/30 15:07
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtsTokenFactory {


    private final FlyCloudTokenProperties properties;

    public JwtsTokenFactory(FlyCloudTokenProperties properties) {
        this.properties = properties;
    }


    public AbstractJwtTokenHandler build() {
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

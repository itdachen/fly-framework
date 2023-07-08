package com.github.itdachen.framework.jwt.factory;

import com.github.itdachen.framework.autoconfigure.jwt.properties.FlyJwtProperties;
import com.github.itdachen.framework.autoconfigure.jwt.enums.JwtTokenEnumType;
import com.github.itdachen.framework.jwt.factory.ecdsa.EcdsaJwtTokenHandler;
import com.github.itdachen.framework.jwt.factory.handler.JwtTokenHandler;
import com.github.itdachen.framework.jwt.factory.hmac.HmacJwtTokenHandler;
import com.github.itdachen.framework.jwt.factory.rsa.RsaJwtTokenHandler;
import org.springframework.stereotype.Component;

/**
 * Description: token 生成方式
 * Created by 王大宸 on 2023/04/12 23:04
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtTokenFactory {

    private final FlyJwtProperties autoconfigureProperties;

    public JwtTokenFactory(FlyJwtProperties autoconfigureProperties) {
        this.autoconfigureProperties = autoconfigureProperties;
    }


    public JwtTokenHandler build() {
        if (JwtTokenEnumType.ECDSA.equals(autoconfigureProperties.getType())) {
            return new EcdsaJwtTokenHandler(autoconfigureProperties);
        } else if (JwtTokenEnumType.HMAC.equals(autoconfigureProperties.getType())) {
            return new HmacJwtTokenHandler(autoconfigureProperties);
        } else if (JwtTokenEnumType.RSA.equals(autoconfigureProperties.getType())) {
            return new RsaJwtTokenHandler(autoconfigureProperties);
        }
        return new HmacJwtTokenHandler(autoconfigureProperties);
    }

}

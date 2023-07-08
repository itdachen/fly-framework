package com.github.itdachen.framework.jjwt.factory;

import com.github.itdachen.framework.autoconfigure.properties.jjwt.FlyJwtAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.properties.jjwt.enums.JwtTokenEnumType;
import com.github.itdachen.framework.jjwt.factory.ecdsa.EcdsaJwtTokenHandler;
import com.github.itdachen.framework.jjwt.factory.handler.JwtTokenHandler;
import com.github.itdachen.framework.jjwt.factory.hmac.HmacJwtTokenHandler;
import com.github.itdachen.framework.jjwt.factory.rsa.RsaJwtTokenHandler;
import org.springframework.stereotype.Component;

/**
 * Description: token 生成方式
 * Created by 王大宸 on 2023/04/12 23:04
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtTokenFactory {

    private final FlyJwtAutoconfigureProperties autoconfigureProperties;

    public JwtTokenFactory(FlyJwtAutoconfigureProperties autoconfigureProperties) {
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

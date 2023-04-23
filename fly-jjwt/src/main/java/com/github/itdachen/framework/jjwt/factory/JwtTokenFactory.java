package com.github.itdachen.framework.jjwt.factory;

import com.github.itdachen.framework.jjwt.enums.JwtTokenEnumType;
import com.github.itdachen.framework.jjwt.factory.ecdsa.EcdsaJwtTokenHandler;
import com.github.itdachen.framework.jjwt.factory.handler.JwtTokenHandler;
import com.github.itdachen.framework.jjwt.factory.hmac.HmacJwtTokenHandler;
import com.github.itdachen.framework.jjwt.factory.rsa.RsaJwtTokenHandler;
import com.github.itdachen.framework.jjwt.properties.JJwtProperties;
import org.springframework.stereotype.Component;

/**
 * Description: token 生成方式
 * Created by 王大宸 on 2023/04/12 23:04
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtTokenFactory {

    private final JJwtProperties jwtProperties;

    public JwtTokenFactory(JJwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    public JwtTokenHandler build() {
        if (JwtTokenEnumType.ECDSA.equals(jwtProperties.getType())) {
            return new EcdsaJwtTokenHandler(jwtProperties);
        } else if (JwtTokenEnumType.HMAC.equals(jwtProperties.getType())) {
            return new HmacJwtTokenHandler(jwtProperties);
        } else if (JwtTokenEnumType.RSA.equals(jwtProperties.getType())) {
            return new RsaJwtTokenHandler(jwtProperties);
        }
        return new HmacJwtTokenHandler(jwtProperties);
    }

}

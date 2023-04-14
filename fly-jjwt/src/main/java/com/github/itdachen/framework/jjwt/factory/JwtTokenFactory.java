package com.github.itdachen.framework.jjwt.factory;

import com.github.itdachen.framework.jjwt.enums.JwtTokenEnumType;
import com.github.itdachen.framework.jjwt.handler.JwtTokenHandler;
import com.github.itdachen.framework.jjwt.handler.processor.HmacJwtTokenHandler;
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
            return new HmacJwtTokenHandler(jwtProperties);
        } else if (JwtTokenEnumType.HMAC.equals(jwtProperties.getType())) {
            return new HmacJwtTokenHandler(jwtProperties);
        }
        return null;
    }

}

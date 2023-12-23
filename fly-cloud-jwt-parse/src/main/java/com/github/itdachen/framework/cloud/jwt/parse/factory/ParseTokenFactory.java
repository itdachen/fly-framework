package com.github.itdachen.framework.cloud.jwt.parse.factory;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import com.github.itdachen.framework.cloud.jwt.parse.factory.handler.AbstractParseJwtsTokenHandler;
import com.github.itdachen.framework.cloud.jwt.parse.factory.handler.EcdsaParseJwtsTokenHandler;
import com.github.itdachen.framework.cloud.jwt.parse.factory.handler.HmacParseJwtsTokenHandler;
import com.github.itdachen.framework.cloud.jwt.parse.factory.handler.RsaParseJwtsTokenHandler;
import org.springframework.stereotype.Component;

/**
 * Description: token 解析工厂
 * Created by 王大宸 on 2023/04/30 15:35
 * Created with IntelliJ IDEA.
 */
@Component
public class ParseTokenFactory {

    private final CloudTokenProperties properties;

    public ParseTokenFactory(CloudTokenProperties properties) {
        this.properties = properties;
    }

    public AbstractParseJwtsTokenHandler build() {
        if (JwtTokenEnumType.ECDSA.equals(properties.getType())) {
            return new EcdsaParseJwtsTokenHandler();
        } else if (JwtTokenEnumType.RSA.equals(properties.getType())) {
            return new RsaParseJwtsTokenHandler();
        }
        return new HmacParseJwtsTokenHandler();
    }

}

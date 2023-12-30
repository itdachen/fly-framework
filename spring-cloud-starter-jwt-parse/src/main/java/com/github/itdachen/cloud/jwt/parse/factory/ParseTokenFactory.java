package com.github.itdachen.cloud.jwt.parse.factory;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import com.github.itdachen.cloud.jwt.parse.token.AbstractParseTokenHandler;
import com.github.itdachen.cloud.jwt.parse.token.EcParseTokenHandler;
import com.github.itdachen.cloud.jwt.parse.token.RsaParseTokenHandler;

/**
 * Description: token 解析工厂
 * Created by 王大宸 on 2023/04/30 15:35
 * Created with IntelliJ IDEA.
 */
public class ParseTokenFactory {

    private final CloudTokenProperties properties;

    public ParseTokenFactory(CloudTokenProperties properties) {
        this.properties = properties;
    }

    public AbstractParseTokenHandler build() {
        if (JwtTokenEnumType.ECDSA.equals(properties.getType())) {
            return new EcParseTokenHandler();
        }
        return new RsaParseTokenHandler();
    }

}

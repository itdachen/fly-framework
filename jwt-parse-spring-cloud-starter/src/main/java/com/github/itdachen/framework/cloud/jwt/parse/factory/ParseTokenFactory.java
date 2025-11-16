package com.github.itdachen.framework.cloud.jwt.parse.factory;

import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.enums.JwtTokenEnumType;
import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties.CloudTokenProperties;
import com.github.itdachen.framework.cloud.jwt.parse.token.AbstractParseTokenHandler;
import com.github.itdachen.framework.cloud.jwt.parse.token.EcParseTokenHandler;
import com.github.itdachen.framework.cloud.jwt.parse.token.RsaParseTokenHandler;

/**
 * Description: token 解析工厂
 * Created by 剑鸣秋朔 on 2023/04/30 15:35
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

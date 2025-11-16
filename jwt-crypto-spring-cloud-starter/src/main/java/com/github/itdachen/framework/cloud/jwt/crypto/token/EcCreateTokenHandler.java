package com.github.itdachen.framework.cloud.jwt.crypto.token;

import com.github.itdachen.framework.cloud.jwt.contents.SignatureAlgorithmContents;
import com.github.itdachen.framework.context.jwt.IJwtInfo;

import java.security.PrivateKey;

/**
 * EcCreateTokenHandler
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 23:06
 */
public class EcCreateTokenHandler extends AbstractCreateTokenHandler {

    public EcCreateTokenHandler() {
        super(SignatureAlgorithmContents.ES512);
    }

    @Override
    public String token(IJwtInfo jwtInfo, PrivateKey privateKey, Long expires, String issuer) throws Exception {
        return createToken(jwtInfo, privateKey, expires, issuer);
    }

}

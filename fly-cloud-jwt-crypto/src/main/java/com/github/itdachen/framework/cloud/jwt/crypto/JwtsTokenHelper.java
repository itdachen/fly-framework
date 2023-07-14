package com.github.itdachen.framework.cloud.jwt.crypto;

import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyJwtTokenProperties;
import com.github.itdachen.framework.cloud.jwt.core.IJwtInfo;
import com.github.itdachen.framework.cloud.jwt.core.SecretKeyConfiguration;
import com.github.itdachen.framework.cloud.jwt.crypto.factory.JwtsTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:14
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtsTokenHelper {

    private final JwtsTokenFactory tokenFactory;
    private final FlyJwtTokenProperties properties;

    public JwtsTokenHelper(JwtsTokenFactory tokenFactory,
                           FlyJwtTokenProperties properties) {
        this.tokenFactory = tokenFactory;
        this.properties = properties;
    }

    @Autowired
    private SecretKeyConfiguration secretKeyConfiguration;

    /***
     * 加密返回 token
     *
     * @author 王大宸
     * @date 2023/4/12 22:53
     * @param jwtInfo  jwtInfo
     * @return java.lang.String
     */
    public String createToken(IJwtInfo jwtInfo) throws Exception {
        return tokenFactory.build().createToken(
                jwtInfo,
                secretKeyConfiguration.getUserPriKey(),
                properties.getExpires(),
                properties.getIssuer()
        );
    }

}

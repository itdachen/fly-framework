package com.github.itdachen.framework.cloud.jwt.crypto;

import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties.CloudTokenProperties;
import com.github.itdachen.framework.cloud.jwt.ICryptoTokenHandler;
import com.github.itdachen.framework.cloud.jwt.ITokenSecretKeyHandler;
import com.github.itdachen.framework.cloud.jwt.crypto.factory.CryptoTokenHandler;
import com.github.itdachen.framework.cloud.jwt.crypto.factory.TokenFactory;
import com.github.itdachen.framework.cloud.jwt.crypto.key.PrivateKeyParseHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 21:52
 */
@Configuration
public class CryptoTokenAutoConfiguration {

    private final CloudTokenProperties tokenProperties;
    private final CloudTokenProperties cloudTokenProperties;

    public CryptoTokenAutoConfiguration(CloudTokenProperties tokenProperties,
                                        CloudTokenProperties cloudTokenProperties) {
        this.tokenProperties = tokenProperties;
        this.cloudTokenProperties = cloudTokenProperties;
    }

    /***
     * 工厂模式
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 23:19
     * @return com.github.itdachen.boot.jwt.crypto.factory.TokenFactory
     */
    @Bean
    public TokenFactory tokenFactory() {
        return new TokenFactory(tokenProperties);
    }


    /***
     * 生成 token, 调用工厂
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 23:18
     * @return com.github.itdachen.boot.jwt.ICryptoTokenHandler
     */
    @Bean
    @ConditionalOnMissingBean(ICryptoTokenHandler.class)
    public ICryptoTokenHandler cryptoTokenHandler() {
        return new CryptoTokenHandler(tokenFactory(), cloudTokenProperties);
    }


    /***
     * 初始化公钥/私钥
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 23:05
     * @return com.github.itdachen.boot.jwt.IJwtSecretKeyHandler
     */
    @Bean
    @ConditionalOnMissingBean(ITokenSecretKeyHandler.class)
    public ITokenSecretKeyHandler jwtSecretKeyHandler() {
        return new PrivateKeyParseHandler(tokenProperties);
    }

}

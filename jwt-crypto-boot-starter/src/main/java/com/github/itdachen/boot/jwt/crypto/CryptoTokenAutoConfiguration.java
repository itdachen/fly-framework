package com.github.itdachen.boot.jwt.crypto;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import com.github.itdachen.boot.jwt.ICryptoTokenHandler;
import com.github.itdachen.boot.jwt.ITokenSecretKeyStrategy;
import com.github.itdachen.boot.jwt.crypto.factory.CryptoTokenHandler;
import com.github.itdachen.boot.jwt.crypto.factory.TokenFactory;
import com.github.itdachen.boot.jwt.crypto.key.PrivateKeyParseStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 *
 * @author 王大宸
 * @date 2023-12-23 21:52
 */
@Configuration
public class CryptoTokenAutoConfiguration {

    private final CloudTokenProperties tokenProperties;

    public CryptoTokenAutoConfiguration(CloudTokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    /***
     * 工厂模式
     *
     * @author 王大宸
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
     * @author 王大宸
     * @date 2023/12/23 23:18
     * @return com.github.itdachen.boot.jwt.ICryptoTokenHandler
     */
    @Bean
    @ConditionalOnMissingBean(ICryptoTokenHandler.class)
    public ICryptoTokenHandler cryptoTokenHandler() {
        return new CryptoTokenHandler(tokenFactory());
    }


    /***
     * 初始化公钥/私钥
     *
     * @author 王大宸
     * @date 2023/12/23 23:05
     * @return com.github.itdachen.boot.jwt.IJwtSecretKeyHandler
     */
    @Bean
    @ConditionalOnMissingBean(ITokenSecretKeyStrategy.class)
    public ITokenSecretKeyStrategy jwtSecretKeyHandler() {
        return new PrivateKeyParseStrategy(tokenProperties);
    }


}

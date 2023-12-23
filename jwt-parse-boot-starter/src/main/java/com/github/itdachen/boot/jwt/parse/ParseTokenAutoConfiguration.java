package com.github.itdachen.boot.jwt.parse;

import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudTokenProperties;
import com.github.itdachen.boot.jwt.IVerifyTicketPublicKeyHelper;
import com.github.itdachen.boot.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.boot.jwt.parse.factory.ParseTokenFactory;
import com.github.itdachen.boot.jwt.parse.factory.VerifyTicketTokenHelper;
import com.github.itdachen.boot.jwt.parse.key.PublicKeyParseHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author 王大宸
 * @date 2023-12-23 22:26
 */
@Configuration
public class ParseTokenAutoConfiguration {

    private final CloudTokenProperties properties;

    public ParseTokenAutoConfiguration(CloudTokenProperties properties) {
        this.properties = properties;
    }

    /***
     * token 解析工厂
     *
     * @author 王大宸
     * @date 2023/12/23 22:31
     * @return com.github.itdachen.boot.jwt.parse.factory.ParseTokenFactory
     */
    @Bean
    public ParseTokenFactory parseTokenFactory() {
        return new ParseTokenFactory(properties);
    }


    /***
     * 解析 token, 调用工厂模式
     *
     * @author 王大宸
     * @date 2023/12/23 22:37
     * @return com.github.itdachen.boot.jwt.IVerifyTicketTokenHelper
     */
    @Bean
    @ConditionalOnMissingBean(IVerifyTicketTokenHelper.class)
    public IVerifyTicketTokenHelper verifyTicketTokenHelper() {
        return new VerifyTicketTokenHelper(parseTokenFactory());
    }

    /***
     * 公钥解析
     *
     * @author 王大宸
     * @date 2023/12/23 22:28
     * @return com.github.itdachen.boot.jwt.IVerifyTicketPublicKeyHelper
     */
    @Bean
    @ConditionalOnMissingBean(IVerifyTicketPublicKeyHelper.class)
    public IVerifyTicketPublicKeyHelper verifyTicketPublicKeyHelper() {
        return new PublicKeyParseHandler();
    }


}

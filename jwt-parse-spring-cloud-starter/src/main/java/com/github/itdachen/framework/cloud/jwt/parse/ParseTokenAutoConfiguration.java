package com.github.itdachen.framework.cloud.jwt.parse;

import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties.CloudAppClientProperties;
import com.github.itdachen.framework.boot.autoconfigure.cloud.auth.properties.CloudTokenProperties;
import com.github.itdachen.framework.cloud.jwt.IVerifyTicketPublicKeyHelper;
import com.github.itdachen.framework.cloud.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.cloud.jwt.parse.factory.ParseTokenFactory;
import com.github.itdachen.framework.cloud.jwt.parse.factory.VerifyTicketTokenHelper;
import com.github.itdachen.framework.cloud.jwt.parse.key.PublicKeyParseHandler;
import com.github.itdachen.framework.cloud.jwt.parse.matchers.IRequestPassMatchers;
import com.github.itdachen.framework.cloud.jwt.parse.matchers.impl.DefaultRequestPassMatchers;
import com.github.itdachen.framework.cloud.jwt.parse.verified.DefaultVerifiedTicketUrlService;
import com.github.itdachen.framework.cloud.jwt.parse.verified.IVerifiedTicketUrlService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author 剑鸣秋朔
 * @date 2023-12-23 22:26
 */
@Configuration
public class ParseTokenAutoConfiguration {

    private final CloudTokenProperties cloudTokenProperties;
    private final CloudAppClientProperties cloudAppClientProperties;

    public ParseTokenAutoConfiguration(CloudTokenProperties cloudTokenProperties,
                                       CloudAppClientProperties cloudAppClientProperties) {
        this.cloudTokenProperties = cloudTokenProperties;
        this.cloudAppClientProperties = cloudAppClientProperties;
    }

    /***
     * 需要自定义不拦截路径, 从写一个类实现 IRequestPassMatchers 接口类即可
     *
     * @author 剑鸣秋朔
     * @date 2023/5/6 16:48
     * @return com.github.itdachen.auth.jwts.parse.matchers.IRequestPassMatchers
     */
    @Bean
    @ConditionalOnMissingBean(IRequestPassMatchers.class)
    public IRequestPassMatchers requestPassMatchers() {
        return new DefaultRequestPassMatchers(cloudAppClientProperties);
    }

    /***
     * token 解析工厂
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 22:31
     * @return com.github.itdachen.cloud.jwt.parse.factory.ParseTokenFactory
     */
    @Bean
    public ParseTokenFactory parseTokenFactory() {
        return new ParseTokenFactory(cloudTokenProperties);
    }


    /***
     * 解析 token, 调用工厂模式
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 22:37
     * @return com.github.itdachen.cloud.jwt.IVerifyTicketTokenHelper
     */
    @Bean
    @ConditionalOnMissingBean(IVerifyTicketTokenHelper.class)
    public IVerifyTicketTokenHelper verifyTicketTokenHelper() {
        return new VerifyTicketTokenHelper(parseTokenFactory());
    }

    /***
     * 公钥解析
     *
     * @author 剑鸣秋朔
     * @date 2023/12/23 22:28
     * @return com.github.itdachen.cloud.jwt.IVerifyTicketPublicKeyHelper
     */
    @Bean
    @ConditionalOnMissingBean(IVerifyTicketPublicKeyHelper.class)
    public IVerifyTicketPublicKeyHelper verifyTicketPublicKeyHelper() {
        return new PublicKeyParseHandler();
    }

    /***
     * 权限校验
     *
     * @author 剑鸣秋朔
     * @date 2024/12/18 10:28
     * @return com.github.itdachen.cloud.jwt.parse.verified.IVerifiedTicketUrlService
     */
    @Bean
    @ConditionalOnMissingBean(IVerifiedTicketUrlService.class)
    public IVerifiedTicketUrlService defaultVerifiedTicketUrlService() {
        return new DefaultVerifiedTicketUrlService();
    }

}

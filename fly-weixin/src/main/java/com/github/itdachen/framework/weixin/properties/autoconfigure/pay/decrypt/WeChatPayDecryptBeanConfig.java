package com.github.itdachen.framework.weixin.properties.autoconfigure.pay.decrypt;

import com.github.itdachen.framework.weixin.properties.IWeChatPayProperties;
import com.github.itdachen.framework.weixin.properties.autoconfigure.pay.WeChatPayConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 解密方式， 默认不加密
 * Created by 剑鸣秋朔 on 2023/04/11 23:29
 * Created with IntelliJ IDEA.
 */
@Configuration
public class WeChatPayDecryptBeanConfig {

    private final WeChatPayConfigurationProperties chatPayConfigurationProperties;

    public WeChatPayDecryptBeanConfig(WeChatPayConfigurationProperties chatPayConfigurationProperties) {
        this.chatPayConfigurationProperties = chatPayConfigurationProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "weixin.pay", name = "crypto", havingValue = "false", matchIfMissing = true)
    public IWeChatPayProperties noneWeChatPayDecrypt() {
        return new NoneWeChatPayDecrypt(chatPayConfigurationProperties);
    }


    @Bean
    @ConditionalOnProperty(prefix = "weixin.pay", name = "crypto", havingValue = "true")
    public IWeChatPayProperties aesWeChatPayDecrypt() {
        return new NoneWeChatPayDecrypt(chatPayConfigurationProperties);
    }

}

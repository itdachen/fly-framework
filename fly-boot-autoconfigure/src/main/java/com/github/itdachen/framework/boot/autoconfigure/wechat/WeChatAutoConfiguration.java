package com.github.itdachen.framework.boot.autoconfigure.wechat;

import com.github.itdachen.framework.boot.autoconfigure.wechat.properties.WeChatAppletProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 *
 * @author 王大宸
 * @date 2024-11-21 11:17
 */
@Configuration
@EnableConfigurationProperties({
        WeChatAppletProperties.class
})
public class WeChatAutoConfiguration {
}

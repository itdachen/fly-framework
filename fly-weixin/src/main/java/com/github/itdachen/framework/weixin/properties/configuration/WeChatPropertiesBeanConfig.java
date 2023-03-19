package com.github.itdachen.framework.weixin.properties.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 启用微信模块配置
 * Created by 王大宸 on 2023/03/19 13:20
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties(WeChatCloudProperties.class)
public class WeChatPropertiesBeanConfig {
}

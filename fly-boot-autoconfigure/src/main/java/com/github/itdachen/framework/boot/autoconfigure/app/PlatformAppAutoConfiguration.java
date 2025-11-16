package com.github.itdachen.framework.boot.autoconfigure.app;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 平台/应用配置
 *
 * @author 剑鸣秋朔
 * @date 2024/4/3 22:11
 */
@Configuration
@EnableConfigurationProperties({
        PlatformInfoProperties.class,
        AppInfoProperties.class,
})
public class PlatformAppAutoConfiguration {
}

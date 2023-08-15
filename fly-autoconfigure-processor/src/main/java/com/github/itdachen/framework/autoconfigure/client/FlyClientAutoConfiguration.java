package com.github.itdachen.framework.autoconfigure.client;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 客户端信息(针对单体项目)
 * Created by 王大宸 on 2023-08-15 10:44
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        FlyClientProperties.class
})
public class FlyClientAutoConfiguration {



}

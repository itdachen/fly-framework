package com.github.itdachen.boot.autoconfigure.client;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 客户端信息
 * Created by 王大宸 on 2023-08-15 10:44
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        ClientProperties.class
})
public class ClientAutoConfiguration {



}

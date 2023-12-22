package com.github.itdachen.boot.autoconfigure.oss;

import com.github.itdachen.boot.autoconfigure.oss.properties.OssAliYunAutoconfigureProperties;
import com.github.itdachen.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.boot.autoconfigure.oss.properties.OssProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:09
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        OssProperties.class,
        OssLocalAutoconfigureProperties.class,
        OssAliYunAutoconfigureProperties.class,
})
public class OssAutoConfiguration {
}

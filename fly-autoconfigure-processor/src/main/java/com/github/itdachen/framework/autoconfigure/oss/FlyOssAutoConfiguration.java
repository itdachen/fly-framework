package com.github.itdachen.framework.autoconfigure.oss;

import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssAliYunAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssLocalAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023-07-08 23:09
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties({
        FlyOssProperties.class,
        FlyOssLocalAutoconfigureProperties.class,
        FlyOssAliYunAutoconfigureProperties.class,
})
public class FlyOssAutoConfiguration {
}

package com.github.itdachen.framework.boot.autoconfigure.oss;

import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssAliYunAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2023-07-08 23:09
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

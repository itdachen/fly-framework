package com.itdachen.framework.file.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created by 王大宸 on 2023/02/10 16:07
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties(LocalCloudStorageProperties.class)
public class OSSCloudStorageCoreConfig {

}

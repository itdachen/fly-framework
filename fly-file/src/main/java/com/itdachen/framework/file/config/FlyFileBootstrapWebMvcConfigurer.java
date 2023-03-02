package com.itdachen.framework.file.config;


import com.itdachen.framework.file.properties.LocalCloudStorageProperties;
import com.itdachen.framework.file.utils.MapPathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 文件映射
 * Created by 王大宸 on 2023/02/10 16:55
 * Created with IntelliJ IDEA.
 */
@Configuration
public class FlyFileBootstrapWebMvcConfigurer implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(FlyFileBootstrapWebMvcConfigurer.class);

    private final LocalCloudStorageProperties properties;

    public FlyFileBootstrapWebMvcConfigurer(LocalCloudStorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(MapPathUtils.filterPath(properties.getMapPath())).addResourceLocations("file:" + properties.getDiskFolder());
    }

}

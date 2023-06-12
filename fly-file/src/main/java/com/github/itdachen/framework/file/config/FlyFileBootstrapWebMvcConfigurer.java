package com.github.itdachen.framework.file.config;

import com.github.itdachen.framework.file.properties.OssCloudProperties;
import com.github.itdachen.framework.file.utils.MapPathUtils;
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

    private final OssCloudProperties properties;

    public FlyFileBootstrapWebMvcConfigurer(OssCloudProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(MapPathUtils.filterPath(properties.getLocal().getMapPath()))
                .addResourceLocations("file:" + properties.getLocal().getDiskFolder());
    }

}

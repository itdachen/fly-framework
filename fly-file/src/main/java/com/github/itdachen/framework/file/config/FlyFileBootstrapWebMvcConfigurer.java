package com.github.itdachen.framework.file.config;

import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssLocalAutoconfigureProperties;
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

    private final FlyOssLocalAutoconfigureProperties autoconfigureProperties;

    public FlyFileBootstrapWebMvcConfigurer(FlyOssLocalAutoconfigureProperties autoconfigureProperties) {
        this.autoconfigureProperties = autoconfigureProperties;
    }

    /**
     * 本地文件上传映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(MapPathUtils.filterPath(autoconfigureProperties.getMapPath()))
                .addResourceLocations("file:" + autoconfigureProperties.getDiskFolder());
    }

}

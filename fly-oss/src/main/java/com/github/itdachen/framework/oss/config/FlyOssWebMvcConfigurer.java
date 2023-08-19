package com.github.itdachen.framework.oss.config;

import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssLocalAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.oss.properties.FlyOssProperties;
import com.github.itdachen.framework.oss.utils.MapPathUtils;
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
public class FlyOssWebMvcConfigurer implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(FlyOssWebMvcConfigurer.class);

    private final FlyOssLocalAutoconfigureProperties autoconfigureProperties;
    private final FlyOssProperties ossProperties;

    public FlyOssWebMvcConfigurer(FlyOssLocalAutoconfigureProperties autoconfigureProperties,
                                  FlyOssProperties ossProperties) {
        this.autoconfigureProperties = autoconfigureProperties;
        this.ossProperties = ossProperties;
    }

    /***
     * 本地文件上传映射
     *
     * @author 王大宸
     * @date 2023/8/19 21:51
     * @param registry registry
     * @return void
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("Configuring fly-oss resource mapping, The file upload type is {}", ossProperties.getType());
        registry.addResourceHandler(MapPathUtils.filterPath(autoconfigureProperties.getMapPath()))
                .addResourceLocations("file:" + autoconfigureProperties.getDiskFolder());
    }

}

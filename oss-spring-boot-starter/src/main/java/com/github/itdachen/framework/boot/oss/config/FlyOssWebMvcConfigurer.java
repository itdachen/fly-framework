package com.github.itdachen.framework.boot.oss.config;

import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssProperties;
import com.github.itdachen.framework.boot.oss.utils.MapPathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 文件映射
 * Created by 剑鸣秋朔 on 2023/02/10 16:55
 * Created with IntelliJ IDEA.
 */
@Configuration
public class FlyOssWebMvcConfigurer implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(FlyOssWebMvcConfigurer.class);

    private final OssLocalAutoconfigureProperties autoconfigureProperties;
    private final OssProperties ossProperties;

    public FlyOssWebMvcConfigurer(OssLocalAutoconfigureProperties autoconfigureProperties,
                                  OssProperties ossProperties) {
        this.autoconfigureProperties = autoconfigureProperties;
        this.ossProperties = ossProperties;
    }

    /***
     * 本地文件上传映射
     *
     * @author 剑鸣秋朔
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

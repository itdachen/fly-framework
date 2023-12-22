package com.github.itdachen.boot.oss;

import com.github.itdachen.boot.autoconfigure.oss.properties.OssAliYunAutoconfigureProperties;
import com.github.itdachen.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.boot.autoconfigure.oss.properties.OssProperties;
import com.github.itdachen.boot.oss.factory.FileFactory;
import com.github.itdachen.boot.oss.service.IVerifyFileHeaderService;
import com.github.itdachen.boot.oss.service.Impl.VerifyFileHeaderServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 文件上传自动配置
 * Created by 王大宸 on 2023/02/17 9:14
 * Created with IntelliJ IDEA.
 */
@Configuration
public class OssAutoConfiguration {


    private final OssProperties autoconfigureProperties;
    private final OssAliYunAutoconfigureProperties aliYunOssAutoconfigureProperties;
    private final OssLocalAutoconfigureProperties localOssAutoconfigureProperties;
    private final IVerifyFileHeaderService verifyFileHeaderService;

    public OssAutoConfiguration(OssProperties autoconfigureProperties,
                                OssAliYunAutoconfigureProperties aliYunOssAutoconfigureProperties,
                                OssLocalAutoconfigureProperties localOssAutoconfigureProperties,
                                IVerifyFileHeaderService verifyFileHeaderService) {
        this.autoconfigureProperties = autoconfigureProperties;
        this.aliYunOssAutoconfigureProperties = aliYunOssAutoconfigureProperties;
        this.localOssAutoconfigureProperties = localOssAutoconfigureProperties;
        this.verifyFileHeaderService = verifyFileHeaderService;
    }

    @Bean
    public FileFactory fileFactory() {
        return new FileFactory(autoconfigureProperties,
                aliYunOssAutoconfigureProperties,
                localOssAutoconfigureProperties,
                verifyFileHeaderService);
    }


    @Bean
    @ConditionalOnMissingBean(IVerifyFileHeaderService.class)
    public IVerifyFileHeaderService verifyFileHeaderService() {
        return new VerifyFileHeaderServiceImpl();
    }


}

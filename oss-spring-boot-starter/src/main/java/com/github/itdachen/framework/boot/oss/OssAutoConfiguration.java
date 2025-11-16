package com.github.itdachen.framework.boot.oss;

import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssAliYunAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssLocalAutoconfigureProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.properties.OssProperties;
import com.github.itdachen.framework.boot.oss.factory.FileFactory;
import com.github.itdachen.framework.boot.oss.service.IVerifyFileHeaderService;
import com.github.itdachen.framework.boot.oss.service.Impl.VerifyFileHeaderServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 文件上传自动配置
 * Created by 剑鸣秋朔 on 2023/02/17 9:14
 * Created with IntelliJ IDEA.
 */
@Configuration
public class OssAutoConfiguration {

    private final OssProperties autoconfigureProperties;
    private final OssAliYunAutoconfigureProperties aliYunOssAutoconfigureProperties;
    private final OssLocalAutoconfigureProperties localOssAutoconfigureProperties;

    public OssAutoConfiguration(OssProperties autoconfigureProperties,
                                OssAliYunAutoconfigureProperties aliYunOssAutoconfigureProperties,
                                OssLocalAutoconfigureProperties localOssAutoconfigureProperties) {
        this.autoconfigureProperties = autoconfigureProperties;
        this.aliYunOssAutoconfigureProperties = aliYunOssAutoconfigureProperties;
        this.localOssAutoconfigureProperties = localOssAutoconfigureProperties;
    }

    @Bean
    public FileFactory fileFactory() {
        return new FileFactory(autoconfigureProperties,
                aliYunOssAutoconfigureProperties,
                localOssAutoconfigureProperties,
                verifyFileHeaderService());
    }

    @Bean
    @ConditionalOnMissingBean(IVerifyFileHeaderService.class)
    public IVerifyFileHeaderService verifyFileHeaderService() {
        return new VerifyFileHeaderServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(FileHelper.class)
    public FileHelper fileHelper() {
        return new FileUploadHelperImpl(fileFactory());
    }


}

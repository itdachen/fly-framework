package com.itdachen.framework.file.service;

import com.itdachen.framework.file.service.Impl.VerifyFileHeaderServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 添加文件头校验默认配置
 * Created by 王大宸 on 2023/02/17 9:14
 * Created with IntelliJ IDEA.
 */
@Configuration
public class VerifyFileHeaderBean {

    @Bean
    @ConditionalOnMissingBean(IVerifyFileHeaderService.class)
    public IVerifyFileHeaderService imageValidateCodeGenerator() {
       return new VerifyFileHeaderServiceImpl();
    }


}

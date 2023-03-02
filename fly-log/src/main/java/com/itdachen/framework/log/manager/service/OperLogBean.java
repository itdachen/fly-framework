package com.itdachen.framework.log.manager.service;

import com.itdachen.framework.log.manager.service.impl.DefaultIApiLogClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 默认操作日志打印方式
 * Created by 王大宸 on 2021-12-01 16:46
 * Created with IntelliJ IDEA.
 */
@Configuration
public class OperLogBean {

    @Bean
    @ConditionalOnMissingBean(IApiLogClientService.class)
    public IApiLogClientService commonOperLogService() {
        return new DefaultIApiLogClientServiceImpl();
    }


}

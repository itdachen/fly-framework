package com.github.itdachen.framework.oplog.manager.service;

import com.github.itdachen.framework.oplog.manager.service.impl.DefaultOplogClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 默认操作日志打印方式
 * Created by 王大宸 on 2021-12-01 16:46
 * Created with IntelliJ IDEA.
 */
@Configuration
public class OplogClientServiceBean {

    @Bean
    @ConditionalOnMissingBean(IOplogClientService.class)
    public IOplogClientService commonOperLogService() {
        return new DefaultOplogClientServiceImpl();
    }


}

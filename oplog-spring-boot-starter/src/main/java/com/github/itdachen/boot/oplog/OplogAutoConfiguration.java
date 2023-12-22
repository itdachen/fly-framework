package com.github.itdachen.boot.oplog;

import com.github.itdachen.boot.oplog.aspectj.OplogAspectj;
import com.github.itdachen.boot.oplog.manager.service.IOplogClientService;
import com.github.itdachen.boot.oplog.manager.service.impl.DefaultOplogClientServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * OplogAutoConfiguration
 *
 * @author 王大宸
 * @date 2023-12-22 23:57
 */
@Configuration
@Aspect
//@Component
public class OplogAutoConfiguration {



    @Bean
    public OplogAspectj oplogAspectj(){
        return new OplogAspectj();
    }

    @Bean
    @ConditionalOnMissingBean(IOplogClientService.class)
    public IOplogClientService commonOperLogService() {
        return new DefaultOplogClientServiceImpl();
    }


}

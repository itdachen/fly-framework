package com.github.itdachen.boot.oplog;

import com.github.itdachen.boot.oplog.aspectj.OplogAspectj;
import com.github.itdachen.boot.oplog.manager.service.IOplogClientHandler;
import com.github.itdachen.boot.oplog.manager.service.impl.DefaultOplogClientHandlerImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * OplogAutoConfiguration
 *
 * @author 王大宸
 * @date 2023-12-22 23:57
 */
@Configuration
@EnableAspectJAutoProxy
public class OplogAutoConfiguration {



    @Bean
    public OplogAspectj oplogAspectj(){
        return new OplogAspectj();
    }

    @Bean
    @ConditionalOnMissingBean(IOplogClientHandler.class)
    public IOplogClientHandler oplogClientService() {
        return new DefaultOplogClientHandlerImpl();
    }


}

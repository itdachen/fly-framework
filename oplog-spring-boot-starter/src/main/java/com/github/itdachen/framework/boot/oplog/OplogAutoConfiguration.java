package com.github.itdachen.framework.boot.oplog;

import com.github.itdachen.framework.boot.oplog.manager.service.IOplogClientHandler;
import com.github.itdachen.framework.boot.oplog.manager.service.IPageViewClientHandler;
import com.github.itdachen.framework.boot.oplog.manager.service.impl.DefaultOplogClientHandlerImpl;
import com.github.itdachen.framework.boot.oplog.manager.service.impl.DefaultPageViewClientHandlerImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OplogAutoConfiguration
 *
 * @author 王大宸
 * @date 2023-12-22 23:57
 */
@Configuration
public class OplogAutoConfiguration {



    @Bean
    @ConditionalOnMissingBean(IOplogClientHandler.class)
    public IOplogClientHandler oplogClientService() {
        return new DefaultOplogClientHandlerImpl();
    }


    @Bean
    @ConditionalOnMissingBean(IPageViewClientHandler.class)
    public IPageViewClientHandler pageViewClientHandler() {
        return new DefaultPageViewClientHandlerImpl();
    }

}

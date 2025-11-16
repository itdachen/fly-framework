package com.github.itdachen.framework.boot.oplog;

import com.github.itdachen.framework.boot.oplog.aspectj.OplogAspectj;
import com.github.itdachen.framework.boot.oplog.aspectj.PageViewAspectj;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 切面配置类
 *
 * @author 剑鸣秋朔
 * @date 2025/9/9 22:25
 */
@Configuration
@EnableAspectJAutoProxy
public class OplogAspectjAutoConfiguration {

    @Bean
    public OplogAspectj oplogAspectj() {
        return new OplogAspectj();
    }

    @Bean
    public PageViewAspectj pageViewAspectj() {
        return new PageViewAspectj();
    }


}

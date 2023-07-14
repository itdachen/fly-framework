package com.github.itdachen.framework.cloud.jwt.parse.matchers;

import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyCloudAppClientProperties;
import com.github.itdachen.framework.cloud.jwt.parse.matchers.impl.DefaultRequestPassMatchers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 不拦截路径默认加载方式
 * Created by 王大宸 on 2023/05/06 16:16
 * Created with IntelliJ IDEA.
 */
@Configuration
public class RequestPassMatchersBeanConfig {

    private final FlyCloudAppClientProperties properties;

    public RequestPassMatchersBeanConfig(FlyCloudAppClientProperties properties) {
        this.properties = properties;
    }

    /***
     * 需要自定义不拦截路径, 从写一个类实现 IRequestPassMatchers 接口类即可
     *
     * @author 王大宸
     * @date 2023/5/6 16:48
     * @return com.github.itdachen.auth.jwts.parse.matchers.IRequestPassMatchers
     */
    @Bean
    @ConditionalOnMissingBean(IRequestPassMatchers.class)
    public IRequestPassMatchers requestPassMatchers() {
        return new DefaultRequestPassMatchers(properties);
    }

}

package com.github.itdachen.framework.cloud.jwt.parse.key;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * Description:
 * Created by 王大宸 on 2023/05/01 13:56
 * Created with IntelliJ IDEA.
 */
@Configuration
@ComponentScan({"com.github.itdachen"})
public class FlyAutoConfiguration {

    /***
     * 坑爹玩意, 新版本的需要加这玩意
     *
     * @author 王大宸
     * @date 2023/5/1 13:56
     * @param converters converters
     * @return HttpMessageConverters
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public AuthClientTokenSecretKey authClientSecretKey() {
        return new AuthClientTokenSecretKey();
    }

    @Bean
    public AuthClientServiceSecretKey authServerClientSecretKey() {
        return new AuthClientServiceSecretKey();
    }


}

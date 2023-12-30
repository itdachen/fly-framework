package com.github.itdachen.cloud.openfeign.decoder;

import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 注册 ServerResponseDecoder 到 Feign 中
 * Created by 王大宸 on 2023/05/05 20:55
 * Created with IntelliJ IDEA.
 */
@Configuration(proxyBeanMethods = false)
public class FeignServerResponseDecoderConfig {

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    public FeignServerResponseDecoderConfig(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Bean
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new OptionalDecoder(new ResponseEntityDecoder(new ServerResponseDecoder(new SpringDecoder(this.messageConverters, customizers))));
    }

}

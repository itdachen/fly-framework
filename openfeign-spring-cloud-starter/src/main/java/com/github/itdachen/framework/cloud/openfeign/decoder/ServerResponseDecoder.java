package com.github.itdachen.framework.cloud.openfeign.decoder;

import com.github.itdachen.framework.context.annotation.IgnoreResponseAdvice;
import com.github.itdachen.framework.core.response.ServerResponse;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Description: Decoder 对 ServerResponse 进行解析, 解决 ResponseBodyAdvice 的侵入性问题
 * Created by 王大宸 on 2023/05/05 20:55
 * Created with IntelliJ IDEA.
 */
public class ServerResponseDecoder implements Decoder {

    private final Decoder decoder;

    public ServerResponseDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {

        Method method = response.request().requestTemplate().methodMetadata().method();
        boolean isResult = method.getReturnType() != ServerResponse.class
                && method.isAnnotationPresent(IgnoreResponseAdvice.class);
        if (isResult) {
            return this.decoder.decode(response, type);
        }
        return this.decoder.decode(response, type);
    }

}

package com.github.itdachen.framework.boot.body.advice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.framework.context.annotation.IgnoreResponseAdvice;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Description: 全局统一响应处理
 * Created by 王大宸 on 2023/02/26 23:43
 * Created with IntelliJ IDEA.
 */
@RestControllerAdvice(basePackages = {"com.github.itdachen"})
public class IgnoreResponseAdviceHandler implements ResponseBodyAdvice<Object> {
    private static final Logger logger = LoggerFactory.getLogger(IgnoreResponseAdviceHandler.class);

    /***
     * 添加不拦截的返回类, 如果有新的类型不需要拦截，添加在下面括号中
     * 返回类型如果不是下面种情况，默认添加 ServerResponse 类型:
     * 1、ServerResponse            返回类型为 ServerResponse 的不拦截
     * 2、IgnoreResponseAdvice      添加 IgnoreResponseAdvice 注解的不拦截
     *
     * @author 王大宸
     * @date 2021/11/20 15:01
     * @param returnType   返回数据统一结果集
     * @param aClass       数据类型
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return !(
                returnType.getParameterType().equals(ServerResponse.class)
                        || returnType.hasMethodAnnotation(IgnoreResponseAdvice.class)
        );
    }

    @Override
    public Object beforeBodyWrite(Object data,
                                  MethodParameter returnType,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            try {
                // 将数据包装在 ServerResponse 里后，再转换为json字符串响应给前端
                return new ObjectMapper().writeValueAsString(ServerResponse.ok(data));
            } catch (JsonProcessingException e) {
                logger.error("返回String类型错误: ", e);
                return ServerResponse.err("返回String类型错误");
            }
        }
        // 将原本的数据包装在ServerResponse里
        return ServerResponse.ok(data);
    }

}

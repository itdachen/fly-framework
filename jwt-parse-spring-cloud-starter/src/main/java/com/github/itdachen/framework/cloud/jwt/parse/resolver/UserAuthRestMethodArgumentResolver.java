package com.github.itdachen.framework.cloud.jwt.parse.resolver;

import com.github.itdachen.framework.context.annotation.CurrentUser;
import com.github.itdachen.framework.context.handler.GlobalContextUserDetailsHandler;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Description: 配置解析器，用来封装当前登录用户的信息
 * Created by 剑鸣秋朔 on 2023/04/30 16:05
 * Created with IntelliJ IDEA.
 */
public class UserAuthRestMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthRestMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserInfoDetails.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public UserInfoDetails resolveArgument(MethodParameter parameter,
                                           ModelAndViewContainer mavContainer,
                                           NativeWebRequest webRequest,
                                           WebDataBinderFactory binderFactory) throws Exception {
        return GlobalContextUserDetailsHandler.getUserDetails();
    }

}

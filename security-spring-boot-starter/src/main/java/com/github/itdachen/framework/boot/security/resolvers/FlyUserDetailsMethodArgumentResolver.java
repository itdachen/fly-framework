package com.github.itdachen.framework.boot.security.resolvers;

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
 * Description:
 * Created by 剑鸣秋朔 on 2023/04/10 16:04
 * Created with IntelliJ IDEA.
 */
public class FlyUserDetailsMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(FlyUserDetailsMethodArgumentResolver.class);

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

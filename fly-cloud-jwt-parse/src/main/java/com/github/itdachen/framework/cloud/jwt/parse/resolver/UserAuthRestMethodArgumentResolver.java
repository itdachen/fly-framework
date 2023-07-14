package com.github.itdachen.framework.cloud.jwt.parse.resolver;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.annotation.CurrentUser;
import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Description: 配置解析器，用来封装当前登录用户的信息
 * Created by 王大宸 on 2023/04/30 16:05
 * Created with IntelliJ IDEA.
 */
public class UserAuthRestMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthRestMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(CurrentUserDetails.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public CurrentUserDetails resolveArgument(MethodParameter parameter,
                                              ModelAndViewContainer mavContainer,
                                              NativeWebRequest webRequest,
                                              WebDataBinderFactory binderFactory) throws Exception {

        CurrentUserDetails userDetails = new CurrentUserDetails();
        userDetails.setAccount(BizContextHandler.getAccount());
        userDetails.setId(BizContextHandler.getUserId());
        userDetails.setNickName(BizContextHandler.getNickName());
        userDetails.setTenantId(BizContextHandler.getTenantId());

        userDetails.setAvatar(BizContextHandler.getAvatar());
        userDetails.setAppId(BizContextHandler.getAppId());
        userDetails.setOpenId(BizContextHandler.getOpenId());
        userDetails.setUserType(BizContextHandler.getUserType());
        userDetails.setSex(BizContextHandler.getSex());
        userDetails.setDeptId(BizContextHandler.getDeptId());
        userDetails.setDeptTitle(BizContextHandler.getDeptTitle());
        userDetails.setTelephone(BizContextHandler.getTelephone());
        userDetails.setEmail(BizContextHandler.getEmail());
        return userDetails;
    }

}

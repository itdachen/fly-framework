package com.github.itdachen.boot.security.interceptor;

import com.github.itdachen.boot.security.context.SecurityContextHandler;
import com.github.itdachen.boot.security.user.CurrentUserInfo;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.handler.GlobalContextUserDetailsHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 认证信息拦截器
 * Created by 王大宸 on 2022-09-23 17:21
 * Created with IntelliJ IDEA.
 */
public class FlyWebSecurityInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(FlyWebSecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // OPTIONS 直接通过
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        Object principal = SecurityContextHandler.getUserInfo();
        if (principal instanceof CurrentUserInfo userInfo) {
            GlobalContextUserDetailsHandler.contextUserHandler(userInfo);
            return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        BizContextHandler.remove();
    }

}

package com.github.itdachen.framework.security.interceptor;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.security.context.SecurityContextHandler;
import com.github.itdachen.framework.security.user.CurrentUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:
 * Created by 王大宸 on 2022-09-23 17:21
 * Created with IntelliJ IDEA.
 */
public class BrowserInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(BrowserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // OPTIONS 直接通过
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        CurrentUserInfo userInfo = SecurityContextHandler.getUserInfo();

        BizContextHandler.setAccount(userInfo.getUsername());
        BizContextHandler.setNickName(userInfo.getName());
        BizContextHandler.setUserId(userInfo.getId());
        BizContextHandler.setAvatar(userInfo.getAvatar());
        BizContextHandler.setUserType(userInfo.getType());
        BizContextHandler.setTenantId(userInfo.getTenantId());
        BizContextHandler.setDeptId(userInfo.getDepartId());

        /* 防止重复提交拦截 */
        return true;
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

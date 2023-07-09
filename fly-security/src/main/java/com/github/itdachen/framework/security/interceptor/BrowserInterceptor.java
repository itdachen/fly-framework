package com.github.itdachen.framework.security.interceptor;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;
import com.github.itdachen.framework.security.context.SecurityContextHandler;
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

        CurrentUserDetails userInfo = (CurrentUserDetails) SecurityContextHandler.getUserInfo();

        BizContextHandler.setUserId(userInfo.getId());
        BizContextHandler.setNickName(userInfo.getNickName());
        BizContextHandler.setAvatar(userInfo.getAvatar());
        BizContextHandler.setAccount(userInfo.getAccount());
        BizContextHandler.setTenantId(userInfo.getTenantId());
        BizContextHandler.setAppId(userInfo.getAppId());
        BizContextHandler.setOpenId(userInfo.getOpenId());
        BizContextHandler.setUserType(userInfo.getUserType());
        BizContextHandler.setSex(userInfo.getSex());
        BizContextHandler.setDeptId(userInfo.getDeptId());
        BizContextHandler.setDeptTitle(userInfo.getDeptTitle());
        BizContextHandler.setTelephone(userInfo.getTelephone());
        BizContextHandler.setEmail(userInfo.getEmail());
        BizContextHandler.setOther(userInfo.getOther());

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

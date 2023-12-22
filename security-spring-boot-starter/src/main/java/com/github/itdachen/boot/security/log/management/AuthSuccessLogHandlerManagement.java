package com.github.itdachen.boot.security.log.management;

import com.github.itdachen.boot.security.authentication.mobile.MobileAuthenticationToken;
import com.github.itdachen.boot.security.log.IAuthSuccessCredentialsLogHandler;
import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;
import com.github.itdachen.framework.core.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


/**
 * Description: 默认登录成功记录方式
 * Created by 王大宸 on 2023-07-09 14:21
 * Created with IntelliJ IDEA.
 */
public class AuthSuccessLogHandlerManagement implements IAuthSuccessCredentialsLogHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessLogHandlerManagement.class);

    @Override
    public void handler(HttpServletRequest request,
                        HttpServletResponse response,
                        Authentication authentication,
                        String sessionId) {

        /* 获取登录用户信息 */
        CurrentUserDetails userDetails = getUserDetails(request, authentication);

        if (null == userDetails || StringUtils.isEmpty(userDetails.getAccount()) || StringUtils.isEmpty(userDetails.getNickName())) {
            logger.info("登录成功, 但是未捕捉到登录方式!");
            return;
        }

        logger.info(userDetails.getAccount() + " 登录成功, 登录方式: " + userDetails.getSignMethod() + ", 登录IP: " + request.getRemoteAddr());
    }

    /***
     * 获取登录用户信息
     *
     * @author 王大宸
     * @date 2023/7/2 14:27
     * @param request request
     * @param authentication authentication
     * @return com.github.itdachen.framework.context.userdetails.CurrentUserDetails
     */
    private CurrentUserDetails getUserDetails(HttpServletRequest request, Authentication authentication) {
        /* 账号密码登录 */
        if (authentication.getClass().equals(UsernamePasswordAuthenticationToken.class)) {
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
            Object principal = authenticationToken.getPrincipal();
            return (CurrentUserDetails) principal;
        }
        /* 短信验证码登录 */
        if (authentication.getClass().equals(MobileAuthenticationToken.class)) {
            MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;
            Object principal = authenticationToken.getPrincipal();
            return (CurrentUserDetails) principal;
        }
        /* 记住我 */
        if (authentication.getClass().equals(RememberMeAuthenticationToken.class)) {
            RememberMeAuthenticationToken authenticationToken = (RememberMeAuthenticationToken) authentication;
            Object principal = authenticationToken.getPrincipal();
            CurrentUserDetails userDetails = (CurrentUserDetails) principal;
            userDetails.setSignMethod("RememberMe");
            return userDetails;
        }
        return null;
    }

}

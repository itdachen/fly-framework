package com.github.itdachen.framework.boot.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.io.IOException;

/**
 * Description: 退出成功处理器
 * Created by 王大宸 on 2022-09-23 16:43
 * Created with IntelliJ IDEA.
 */
public class AuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationLogoutSuccessHandler.class);

    private final String signOutSuccessUrl;

    public AuthenticationLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }


    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.logout.
     * LogoutSuccessHandler#onLogoutSuccess(jakarta.servlet.http.
     * HttpServletRequest, jakarta.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        if (null != authentication) { //清除认证
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.sendRedirect(signOutSuccessUrl);
    }


}

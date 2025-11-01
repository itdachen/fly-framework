package com.github.itdachen.framework.boot.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * 自定义退出登录
 *
 * @author 王大宸
 * @date 2024-08-26 16:13
 */
public class AuthenticationLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 退出登录处理, 例如根据 session id 删除数据库在线用户信息
        String sessionId = request.getSession().getId();

        //清除认证
        if (null != authentication) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

    }

}

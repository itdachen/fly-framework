package com.github.itdachen.framework.boot.security.log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

/**
 * Description: 登录认证成功日志
 * Created by 王大宸 on 2023-07-09 14:20
 * Created with IntelliJ IDEA.
 */
public interface IAuthSuccessCredentialsLogHandler {

    void handler(HttpServletRequest request, HttpServletResponse response, Authentication authentication, String sessionId);


}

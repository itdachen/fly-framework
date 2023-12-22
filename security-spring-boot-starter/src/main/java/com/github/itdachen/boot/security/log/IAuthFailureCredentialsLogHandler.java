package com.github.itdachen.boot.security.log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

/**
 * Description: 登录失败日志
 * Created by 王大宸 on 2023-07-09 14:20
 * Created with IntelliJ IDEA.
 */
public interface IAuthFailureCredentialsLogHandler {

    /***
     * handler
     *
     * @author 王大宸
     * @date 2023/7/9 14:29
     * @param request request      请求
     * @param response response    返回
     * @param exception exception  异常
     * @param sessionId sessionId  sessionID
     * @return void
     */
    void handler(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception, String sessionId);

}

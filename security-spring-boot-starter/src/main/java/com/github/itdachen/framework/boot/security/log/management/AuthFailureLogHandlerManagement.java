package com.github.itdachen.framework.boot.security.log.management;

import com.github.itdachen.framework.boot.security.log.IAuthFailureCredentialsLogHandler;
import com.github.itdachen.framework.tools.request.BodyUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

/**
 * Description: 默认登录失败记录方式
 * Created by 剑鸣秋朔 on 2023-07-09 14:22
 * Created with IntelliJ IDEA.
 */
public class AuthFailureLogHandlerManagement implements IAuthFailureCredentialsLogHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthFailureLogHandlerManagement.class);

    @Override
    public void handler(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException exception,
                        String sessionId) {
        final Map<String, String> body = BodyUtils.requestBody(request);
        String username = "";
        if (body.containsKey("imageCode")) {
            username = body.get("username");
        }
        if (body.containsKey("smsCode")) {
            username = body.get("mobile");
        }

        logger.info(username + " 登录失败, 登录IP: " + request.getRemoteAddr() + ", 消息: " + exception.getMessage());
    }
}

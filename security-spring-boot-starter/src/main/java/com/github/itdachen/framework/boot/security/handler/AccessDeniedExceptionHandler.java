package com.github.itdachen.framework.boot.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.framework.boot.security.user.CurrentUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * Description: 权限不足
 * Created by 剑鸣秋朔 on 2022-09-23 16:41
 * Created with IntelliJ IDEA.
 */
//@Component
@Deprecated
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedExceptionHandler.class);
    private final ObjectMapper objectMapper;

    public AccessDeniedExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            CurrentUserInfo userInfo = (CurrentUserInfo) context.getAuthentication().getPrincipal();
            logger.error("权限不足, 请求人ID: {}, 请求人昵称: {}, 请求方式: {}, 请求地址: {}",
                    userInfo.getId(),
                    userInfo.getNickName(),
                    httpServletRequest.getMethod(),
                    httpServletRequest.getRequestURI());
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString("没有权限，不允许访问"));
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString("没有权限，不允许访问"));
        }
    }

}

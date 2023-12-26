package com.github.itdachen.boot.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.boot.autoconfigure.security.properties.session.SecuritySessionProperties;
import com.github.itdachen.boot.security.user.CurrentUserInfo;
import com.github.itdachen.framework.context.exception.ClientTokenException;
import com.github.itdachen.framework.core.response.ServerResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Description: 认证异常处理
 * Created by 王大宸 on 2021-11-27 10:57
 * Created with IntelliJ IDEA.
 */
public class ClientSessionExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ClientSessionExceptionHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 重定向策略
     */
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final SecuritySessionProperties sessionProperties;

    public ClientSessionExceptionHandler(SecuritySessionProperties sessionProperties) {
        this.sessionProperties = sessionProperties;
    }


    /***
     * 当前登录用户异常
     *
     * @author 王大宸
     * @date 2021/11/27 10:58
     * @param request
     * @param response
     * @param ex
     * @return void
     */
    @ExceptionHandler(ClientTokenException.class)
    public void clientTokenException(HttpServletRequest request,
                                     HttpServletResponse response,
                                     ClientTokenException ex) throws IOException {
        logger.error("当前登录用户异常: ", ex);
        request.getSession();
        redirectStrategy.sendRedirect(
                request,
                response,
                sessionProperties.getSessionInvalidUrl()
        );
    }

    /***
     * 权限不足
     *
     * @author 王大宸
     * @date 2021/11/27 11:29
     * @param request
     * @param response
     * @param ex
     * @return com.itdachen.auth.core.response.ServerResponse<java.lang.String>
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedException(HttpServletRequest request,
                                      HttpServletResponse response,
                                      AccessDeniedException ex) throws IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        CurrentUserInfo userInfo = (CurrentUserInfo) context.getAuthentication().getPrincipal();
        logger.error("权限不足, 请求人ID: {}, 请求人昵称: {}, 请求方式: {}, 请求地址: {}",
                userInfo.getId(),
                userInfo.getNickName(),
                request.getMethod(),
                request.getRequestURI());
        response.setStatus(HttpStatus.OK.value());

        /* 如果是 Ajax 请求, 返回 json 数据, 如果不是 Ajax 请求, 跳转到 403 页面 */
        boolean isAjax = isAjaxRequest(request);
        if (isAjax) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ServerResponse.resErrorJson(403, "抱歉，您的权限不足，无法访问该资源！")));
        } else {
            response.sendRedirect("/error/403");
        }


    }

    /***
     * 判断是否 ajax 请求
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        String xRequestedWith = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
            // 这里只判断是不是 Ajax 请求
            return true;
        }
        return false;

//        String contentTypeHeader = request.getHeader("Content-Type");
//        if (contentTypeHeader == null || contentTypeHeader.contains("application/json")){
//            return true;
//        }
//        String acceptHeader = request.getHeader("Accept");
//        if (acceptHeader == null || acceptHeader.contains("application/json")){
//            return true;
//        }
//       return false;
    }


}

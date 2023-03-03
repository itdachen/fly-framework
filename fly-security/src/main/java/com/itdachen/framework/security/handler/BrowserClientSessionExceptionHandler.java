package com.itdachen.framework.security.handler;

import com.itdachen.framework.security.exception.ClientTokenException;
import com.itdachen.framework.security.properties.SecurityBrowserProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * Created by 王大宸 on 2021-11-27 10:57
 * Created with IntelliJ IDEA.
 */
@RestControllerAdvice(basePackages = {"com.itdachen"})
public class BrowserClientSessionExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BrowserClientSessionExceptionHandler.class);
    /**
     * 重定向策略
     */
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final SecurityBrowserProperties securityProperties;

    public BrowserClientSessionExceptionHandler(SecurityBrowserProperties securityProperties) {
        this.securityProperties = securityProperties;
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
        logger.error(ex.getMessage());
        request.getSession();
        redirectStrategy.sendRedirect(
                request,
                response,
                securityProperties.getSession().getSessionInvalidUrl()
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
        logger.error(ex.getMessage());
        response.setStatus(HttpStatus.OK.value());

        redirectStrategy.sendRedirect(
                request,
                response,
               // securityProperties.getSession().getSessionInvalidUrl()
                "/error/403"
        );

       // return ServerResponse.errStatusMsgDate(ResultCode.ERROR.getStatus(), ex.getMessage(), null);
    }


}

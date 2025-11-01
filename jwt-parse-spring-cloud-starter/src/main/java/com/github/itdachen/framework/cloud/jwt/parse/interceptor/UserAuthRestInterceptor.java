package com.github.itdachen.framework.cloud.jwt.parse.interceptor;

import com.github.itdachen.framework.cloud.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.cloud.jwt.parse.verified.IVerifiedTicketUrlService;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.annotation.IgnoreUserToken;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.jwt.IJwtInfo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * Description: 用户认证拦截器
 * Created by 王大宸 on 2023/05/05 15:19
 * Created with IntelliJ IDEA.
 */
public class UserAuthRestInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);

    private final IVerifyTicketTokenHelper verifyTicketTokenService;
    private final IVerifiedTicketUrlService verifiedTicketUrlService;

    public UserAuthRestInterceptor(IVerifyTicketTokenHelper verifyTicketTokenService,
                                   IVerifiedTicketUrlService verifiedTicketUrlService) {
        this.verifyTicketTokenService = verifyTicketTokenService;
        this.verifiedTicketUrlService = verifiedTicketUrlService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken ignoreUserToken = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (ignoreUserToken == null) {
            ignoreUserToken = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (ignoreUserToken != null) {
            return true;
        }

        // 是否鉴权 IVerifiedTicketUrl
        boolean verifiedTicket = verifiedTicketUrlService.handler(request);
        if (!verifiedTicket) {
            writeErrorResponse(response, HttpStatus.OK.value(), HttpStatus.BAD_REQUEST.value(), "非法请求");
            return false;
        }

        String token = request.getHeader(UserInfoConstant.HEADER_AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(UserInfoConstant.HEADER_AUTHORIZATION)) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }
        if (StringUtils.isEmpty(token)) {
            throw new BizException("用户认证失败");
        }
        token = token.replaceAll("%20", " ");
        if (token.startsWith(UserInfoConstant.TOKEN_TYPE)) {
            token = token.substring(UserInfoConstant.TOKEN_TYPE.length());
        }
        try {
            final IJwtInfo ijwtInfo = verifyTicketTokenService.parseToken(token);
            BizContextHandler.setContextHandler(ijwtInfo);
            return true;
        } catch (Exception ex) {
            logger.error("用户认证错误：", ex);
            writeErrorResponse(response, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BizContextHandler.remove();
    }

    private String getMessage(int status, String msg) {
        return "{\"success\":\"false\",\"status\":\"" + status + "\", \"msg\":\"" + msg + "\",\"data\": null}";
    }

    private void writeErrorResponse(HttpServletResponse response, int httpStatus, int status, String msg) throws IOException {
        response.setStatus(httpStatus);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(getMessage(status, msg));
    }


}

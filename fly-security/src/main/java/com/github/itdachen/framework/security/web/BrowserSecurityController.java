package com.github.itdachen.framework.security.web;

import com.github.itdachen.framework.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.autoconfigure.security.constants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Description:
 * Created by 王大宸 on 2022-09-25 11:34
 * Created with IntelliJ IDEA.
 */
@Controller
public class BrowserSecurityController {
    private static final Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final FlySecurityProperties securityProperties;

    public BrowserSecurityController(FlySecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /***
     * 当需要身份认证时，跳转到这里, 做统一处理
     *
     * @author 王大宸
     * @date 2021/11/27 10:56
     * @param request
     * @param response
     * @return com.itdachen.auth.core.response.ServerResponse<java.lang.String>
     */
    @RequestMapping(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String signInPage = securityProperties.getSignInPage();
//        final String contextPath = ContextPathHandler.contextPath();
//        signInPage = AuthorizeHttpRequestsHandler.anyRequestUriHandler(contextPath, signInPage);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("访问的服务需要身份认证, 引发跳转的请求是: " + targetUrl);
            redirectStrategy.sendRedirect(request, response, signInPage);
        } else {
            redirectStrategy.sendRedirect(request, response, signInPage);
        }
    }

    /***
     * session 过期处理
     *
     * @author 王大宸
     * @date 2022/9/25 11:34
     * @return java.lang.String
     */
    @GetMapping(value = SecurityConstants.DEFAULT_SESSION_INVALID_URL)
    public String sessionInvalid() {
        return "session/invalid";
    }

}

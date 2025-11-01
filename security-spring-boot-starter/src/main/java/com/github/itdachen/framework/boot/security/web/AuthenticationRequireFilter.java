package com.github.itdachen.framework.boot.security.web;

import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.FlySecurityProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
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
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * 认证需要访问的地址过滤, 所有需要认证的, 而没有认证的, 都走这个过滤器
 *
 * @author 王大宸
 * @date 2024/11/30 23:06
 */
public class AuthenticationRequireFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationRequireFilter.class);
    private static final String HTTP_URI = SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL;

    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final FlySecurityProperties flySecurityProperties;

    public AuthenticationRequireFilter(FlySecurityProperties flySecurityProperties) {
        this.flySecurityProperties = flySecurityProperties;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (!this.isUrlRequest((HttpServletRequest) servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            SavedRequest savedRequest = requestCache.getRequest(request, response);
            String signInPage = flySecurityProperties.getSignInPage();
            if (savedRequest != null) {
                String targetUrl = savedRequest.getRedirectUrl();
                logger.info("访问的服务需要身份认证, 引发跳转的请求是: " + targetUrl);
                redirectStrategy.sendRedirect(request, response, signInPage);
            } else {
                redirectStrategy.sendRedirect(request, response, signInPage);
            }

        }


    }


    /***
     * 地址校验
     *
     * @author 王大宸
     * @date 2024/11/28 15:38
     * @param request request
     * @return boolean
     */
    private boolean isUrlRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        final String contextPath = request.getContextPath();
        if ("".equals(contextPath)) {
            return HTTP_URI.equals(uri);
        } else {
            String[] split = uri.split("\\?");
            return split[0].equals(contextPath + HTTP_URI);
        }
    }


}

package com.github.itdachen.framework.boot.security.authentication.platforms;

import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.framework.boot.security.constants.ServerForwardKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 内部开发平台跳转 认证过滤器
 *
 * @author 剑鸣秋朔
 * @date 2023-11-13 16:02
 */
public class PlatformAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly = true;

    public PlatformAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.THIRD_PLATFORM, SecurityConstants.HTTP_GET));
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals(SecurityConstants.HTTP_GET)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        /* 获取认证授权码 */
        String ticketCode = request.getParameter(ServerForwardKey.TICKET_CODE);
        /* 获取回访地址 */
        String ticketUri = request.getParameter(ServerForwardKey.TICKET_URL);
        if (null == ticketCode) {
            ticketCode = "";
        }
        if (null == ticketUri) {
            ticketUri = "";
        }
        ticketCode = ticketCode.trim();
        ticketUri = ticketUri.trim();

        PlatformAuthenticationToken token = new PlatformAuthenticationToken(ticketCode, ticketUri);
        setDetails(request, token);
        // 封装成 token 交给 AuthenticationManager 去校验
        return this.getAuthenticationManager().authenticate(token);
    }

    /**
     * Provided so that subclasses may configure what is put into the
     * authentication request's details property.
     *
     * @param request that an authentication request is being created for
     * @param token   the authentication request object that should have its details   set
     */
    protected void setDetails(HttpServletRequest request, PlatformAuthenticationToken token) {
        token.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter.
     * If set to true, and an authentication request is received which is not a
     * POST request, an exception will be raised immediately and authentication
     * will not be attempted. The <tt>unsuccessfulAuthentication()</tt> method
     * will be called as if handling a failed authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

}

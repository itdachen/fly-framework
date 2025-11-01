package com.github.itdachen.framework.boot.security.authentication.mobile;

import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;


/**
 * Description: 短信登录过滤器
 * Created by 王大宸 on 2023-12-09 23:43
 * Created with IntelliJ IDEA.
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST");
    private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

    private boolean postOnly = true;

    public MobileAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    public MobileAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);
        mobile = mobile != null ? mobile.trim() : "";

        MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile);
        setDetails(request, authRequest);

        // return this.getAuthenticationManager().authenticate(authRequest);


        AuthenticationManager authenticationManager = this.getAuthenticationManager();
        Authentication authenticate = authenticationManager.authenticate(authRequest);
        return authenticate;

    }


    /**
     * 获取手机号
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }


    protected void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }


    public void setMobileParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        mobileParameter = usernameParameter;
    }


}

package com.github.itdachen.framework.security.handler;

import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.security.constants.LoginModeConstant;
import com.github.itdachen.framework.security.constants.SecurityBrowserConstants;
import com.github.itdachen.framework.security.exception.BizSecurityException;
import com.github.itdachen.framework.security.properties.SecurityBrowserProperties;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Description: 登录失败处理器
 * Created by 王大宸 on 2022-09-23 16:39
 * Created with IntelliJ IDEA.
 */
@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    private final SecurityBrowserProperties securityProperties;

    public AuthenticationFailureHandler(SecurityBrowserProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        // 登录错误，添加跳转路径，跳转到登陆页面，需要在路径后面添加 ?error=true 作为 thymeleaf 页面提示
        String failureUrl = securityProperties.getSignInPage() + "?error=true";

        /* 登录之后是否需要重定向到指定地址 */
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString) && queryString.contains(SecurityBrowserConstants.REDIRECT_URI)) {
            List<String> strings = StringUtils.strToList(queryString, "&");
            String[] split;
            for (String string : strings) {
                split = string.split("=");
                if (!SecurityBrowserConstants.REDIRECT_URI.contains(split[0])) {
                    continue;
                }
                failureUrl += "&" + string;
                break;
            }
        }

        if (null == exception) {
            setDefaultFailureUrl(failureUrl);
            super.onAuthenticationFailure(request, response, exception);
            return;
        }
        String message = exception.getMessage();
        String type = "";
        // 手机号码登录失败
        if (StringUtils.startsWithIgnoreCase(message, "SMS")) {
            type = "sms";
            exception = new BizSecurityException("验证码错误");
        }
        if (StringUtils.startsWithIgnoreCase(message, LoginModeConstant.NO_MOBILE_USER)) {
            type = "sms";
            exception = new BizSecurityException(message.replaceAll(LoginModeConstant.NO_MOBILE_USER, ""));
        }
        /* 密码登录失败 */
        if (StringUtils.startsWithIgnoreCase(message, "IMAGE")) {
            type = "password";
            exception = new BizSecurityException("验证码错误");
        }
        if (StringUtils.startsWithIgnoreCase(message, LoginModeConstant.NOT_HAVE_USER)) {
            type = "password";
            exception = new BizSecurityException("用户名或密码错误");
        }

        if (StringUtils.isNotEmpty(type)) {
            failureUrl = failureUrl + "&mode=" + type;
        }
        setDefaultFailureUrl(failureUrl);
        super.onAuthenticationFailure(request, response, exception);
    }

}

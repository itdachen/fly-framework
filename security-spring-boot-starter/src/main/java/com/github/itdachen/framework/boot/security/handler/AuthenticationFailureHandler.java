package com.github.itdachen.framework.boot.security.handler;

import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.boot.security.constants.LoginModeConstant;
import com.github.itdachen.framework.boot.security.exception.BizSecurityException;
import com.github.itdachen.framework.boot.security.log.IAuthFailureCredentialsLogHandler;
import com.github.itdachen.framework.core.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description: 登录失败处理器
 * Created by 王大宸 on 2022-09-23 16:39
 * Created with IntelliJ IDEA.
 */
//@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    private final FlySecurityProperties flySecurityProperties;
    private final IAuthFailureCredentialsLogHandler authFailureCredentialsLogHandler;

    public AuthenticationFailureHandler(FlySecurityProperties flySecurityProperties,
                                        IAuthFailureCredentialsLogHandler authFailureCredentialsLogHandler) {
        this.flySecurityProperties = flySecurityProperties;
        this.authFailureCredentialsLogHandler = authFailureCredentialsLogHandler;
    }

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
            16, 3,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    // AuthenticationFailureHandler
                    thread.setName("authentication-failure-handler-thread-" + ThreadLocalRandom.current().nextInt(1000));
                    return thread;
                }
            });


    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        // 登录错误，添加跳转路径，跳转到登陆页面，需要在路径后面添加 ?error=true 作为 thymeleaf 页面提示
        String failureUrl = flySecurityProperties.getSignInPage() + "?error=true";

        /* 登录之后是否需要重定向到指定地址 */
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString) && queryString.contains(SecurityConstants.REDIRECT_URI)) {
            List<String> strings = StringUtils.strToList(queryString, "&");
            String[] split;
            for (String string : strings) {
                split = string.split("=");
                if (!SecurityConstants.REDIRECT_URI.contains(split[0])) {
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

        final String message = exception.getMessage();

        /* 登录失败数据入库 */
        // AsyncThreadsManager.me().execute(LogAsyncFactory.failureTimerTask(request, response, exception, request.getSession().getId()));

        AuthenticationException e = exception;
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    authFailureCredentialsLogHandler.handler(request, response, e, request.getSession().getId());
                } catch (Exception e) {
                    logger.error("登录失败日志入库失败!");
                }
            }
        });

        String type = LoginModeConstant.PASSWORD;
        // 手机号码登录失败
        if (StringUtils.startsWithIgnoreCase(message, "SMS")) {
            type = LoginModeConstant.SMS;
            exception = new BizSecurityException("验证码错误");
        }
        if (StringUtils.startsWithIgnoreCase(message, LoginModeConstant.NO_MOBILE_USER)) {
            type = LoginModeConstant.SMS;
            exception = new BizSecurityException(message.replaceAll(LoginModeConstant.NO_MOBILE_USER, ""));
        }
        /* 密码登录失败 */
        if (StringUtils.startsWithIgnoreCase(message, "IMAGE")) {
            type = LoginModeConstant.PASSWORD;
            exception = new BizSecurityException("验证码错误");
        }
        if (StringUtils.startsWithIgnoreCase(message, LoginModeConstant.NOT_HAVE_USER)) {
            type = LoginModeConstant.PASSWORD;
            exception = new BizSecurityException("用户名或密码错误");
        }

        if (StringUtils.isNotEmpty(type)) {
            failureUrl = failureUrl + "&mode=" + type;
        }
        setDefaultFailureUrl(failureUrl);
        super.onAuthenticationFailure(request, response, exception);
    }


}

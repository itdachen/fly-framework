package com.github.itdachen.framework.boot.security.handler;

import com.github.itdachen.framework.boot.autoconfigure.security.properties.FlySecurityProperties;
import com.github.itdachen.framework.boot.security.log.IAuthSuccessCredentialsLogHandler;
import com.github.itdachen.framework.boot.autoconfigure.security.constants.SecurityConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Description: 登录成功处理器
 * Created by 剑鸣秋朔 on 2022-09-23 16:36
 * Created with IntelliJ IDEA.
 */
//@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    private final FlySecurityProperties flySecurityProperties;
    private final IAuthSuccessCredentialsLogHandler authSuccessCredentialsLogHandler;

    public AuthenticationSuccessHandler(FlySecurityProperties flySecurityProperties,
                                        IAuthSuccessCredentialsLogHandler authSuccessCredentialsLogHandler) {
        this.flySecurityProperties = flySecurityProperties;
        this.authSuccessCredentialsLogHandler = authSuccessCredentialsLogHandler;
    }

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
            16, 3,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("authentication-success-handler-thread-" + ThreadLocalRandom.current().nextInt(1000));
                    return thread;
                }
            });

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 获取登录时,跟账号密码同时传入的其他信息,赋值给登录用户信息
//        String netType = request.getParameter("netType");
//        CurrentUser user = (CurrentUser) authentication.getPrincipal();
//        user.setType(netType);

        /* 登录成功数据入库 */
        //   AsyncThreadsManager.me().execute(LogAsyncFactory.successTimerTask(request, response, authentication, request.getSession().getId()));


        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    authSuccessCredentialsLogHandler.handler(request, response, authentication, request.getSession().getId());
                } catch (Exception e) {
                    logger.error("登录成功日志入库失败!");
                }
            }
        });

        /* 登录成功之后的回调地址属性 */
        setTargetUrlParameter(SecurityConstants.REDIRECT_URI);

        // 是不是总是跳转到默认配置地址
        setAlwaysUseDefaultTargetUrl(false);
        // setAlwaysUseDefaultTargetUrl(true);

        // 登录成功之后跳转地址
        setDefaultTargetUrl(flySecurityProperties.getSuccessForwardUrl());

        super.onAuthenticationSuccess(request, response, authentication);
    }

}

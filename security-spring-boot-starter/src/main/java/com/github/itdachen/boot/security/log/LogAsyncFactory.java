package com.github.itdachen.boot.security.log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.TimerTask;

/**
 * Description:
 * Created by 王大宸 on 2023-07-12 15:58
 * Created with IntelliJ IDEA.
 */
@Deprecated
public class LogAsyncFactory {

    /***
     * 登录成功任务
     *
     * @author 王大宸
     * @date 2023/7/12 16:00
     * @param request request
     * @param response response
     * @param authentication authentication
     * @param sessionId sessionId
     * @return java.util.TimerTask
     */
//    public static TimerTask successTimerTask(HttpServletRequest request,
//                                             HttpServletResponse response,
//                                             Authentication authentication,
//                                             String sessionId) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                SpringBeanUtils.getBean(IAuthSuccessCredentialsLogHandler.class).handler(request,
//                        response,
//                        authentication,
//                        sessionId);
//            }
//        };
//    }

    /***
     * 登录失败记录
     *
     * @author 王大宸
     * @date 2023/7/12 16:01
     * @param request request
     * @param response response
     * @param exception exception
     * @param sessionId sessionId
     * @return java.util.TimerTask
     */
//    public static TimerTask failureTimerTask(HttpServletRequest request,
//                                             HttpServletResponse response,
//                                             AuthenticationException exception,
//                                             String sessionId) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                SpringBeanUtils.getBean(IAuthFailureCredentialsLogHandler.class).handler(request,
//                        response,
//                        exception,
//                        sessionId);
//            }
//        };
//    }

}

package com.github.itdachen.boot.oplog.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.boot.autoconfigure.AppContextHelper;;
import com.github.itdachen.boot.oplog.entity.PageViewLog;
import com.github.itdachen.boot.oplog.manager.service.IPageViewClientHandler;
import com.github.itdachen.framework.context.annotation.CheckApiClient;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.context.annotation.PageView;
import com.github.itdachen.framework.context.id.IdUtils;
import com.github.itdachen.framework.tools.ServletUtils;
import com.github.itdachen.framework.tools.ip.IpAddressUtils;
import com.github.itdachen.framework.tools.request.BrowserUtils;
import com.github.itdachen.framework.tools.request.OSUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * 页面访问日志 AOP
 *
 * @author 王大宸
 * @date 2025/9/9 21:43
 */
@Aspect
public class PageViewAspectj {
    private static final Logger logger = LoggerFactory.getLogger(PageViewAspectj.class);

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 3,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("oplog-page-view-thread-" + ThreadLocalRandom.current().nextInt(1000));
                    return thread;
                }
            });


    @Pointcut("@annotation(pageView)")
    public void logPointCut(PageView pageView) {
    }


    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut(pageView)", returning = "resJson", argNames = "joinPoint,pageView,resJson")
    public void doAfterReturning(JoinPoint joinPoint, PageView pageView, Object resJson) {
        if (null == pageView) {
            return;
        }

        /* 操作日志基础信息 */
        PageViewLog pageViewLog = setRequest(joinPoint, pageView);


        try {
            // 保存数据库
            // AsyncThreadsManager.me().execute(OplogAsyncFactory.recordOplog(apiLog));

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        AppContextHelper.getBean(IPageViewClientHandler.class).save(pageViewLog);
                    } catch (Exception e) {
                        logger.error("页面访问日志数据入库异常: ", e);
                    }
                }
            });


        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==页面访问前置通知异常==异常信息: {}", exp.getMessage(), exp);
        }
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param ex        异常
     */
    @AfterThrowing(value = "logPointCut(pageView)", throwing = "ex", argNames = "joinPoint,pageView,ex")
    public void doAfterThrowing(JoinPoint joinPoint, PageView pageView, Exception ex) {
        if (null == pageView) {
            return;
        }

        /* 操作日志基础信息 */
        PageViewLog oplogInfo = setRequest(joinPoint, pageView);
        oplogInfo.setExInfo(JSONObject.toJSONString(ex));

        try {
            // 保存数据库
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        AppContextHelper.getBean(IPageViewClientHandler.class).save(oplogInfo);
                    } catch (Exception e) {
                        logger.error("页面访问日志数据入库异常: ", e);
                    }
                }
            });
        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==页面访问前置通知异常==异常信息: {}", exp.getMessage(), exp);
        }

    }


    private PageViewLog setRequest(JoinPoint joinPoint, PageView pageView) {
        // *========数据库日志=========*//
        HttpServletRequest request = ServletUtils.getRequest();

        String userAgent = request.getHeader("user-agent");
        if (null == userAgent) {
            userAgent = request.getHeader("User-Agent");
        }


        PageViewLog pageViewLog = new PageViewLog().builder()
                .id(IdUtils.getId())
                .opCode(pageView.value().getCode())
                .opTitle(pageView.value().getTitle())
                .opVersion(pageView.version())
                .opTime(LocalDateTime.now())

                .hostIp(IpAddressUtils.getNetworkIp(request))
                .hostAddress("-")
                .hostOs(OSUtils.osInfo(request))
                .hostBrowser(BrowserUtils.browserInfo(request))
                .userAgent(userAgent)

                .requestId(request.getRequestId())
                .requestUri(request.getRequestURI())
                .requestMethod(request.getMethod())

                .build();


        CheckApiClient apiClient = joinPoint.getTarget().getClass().getAnnotation(CheckApiClient.class);
        if (null != apiClient) {
            pageViewLog.setMenuTitle(apiClient.title());
        }
        if (null == apiClient) {
            FuncTitle funcTitle = joinPoint.getTarget().getClass().getAnnotation(FuncTitle.class);
            if (null != funcTitle) {
                pageViewLog.setMenuTitle(funcTitle.value());
            }
        }

        return pageViewLog;
    }

    /**
     * 获取请求的参数，放到log中
     */
    private String getRequestValue(JoinPoint joinPoint) {
        try {
            if (joinPoint.getArgs()[0] instanceof MultipartFile) {
                return ((MultipartFile) joinPoint.getArgs()[0]).getOriginalFilename();
            }
            Object[] args = joinPoint.getArgs();
            int length = args.length;
            if (0 == length) {
                return "{}";
            }
            if (1 == length) {
                return JSON.toJSONString(joinPoint.getArgs());
            } else {
                return JSON.toJSONString(args[length - 1]);
            }
        } catch (Exception e) {
            return "{}";
        }


//        // 1、获取方法的参数的字符串数组
//        Object[] args = joinPoint.getArgs();
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        if (null == methodSignature){
//            apiLog.setParams("{}");
//            return;
//        }
//
//        //2.获取到方法的所有参数名称的字符串数组
//        String[] parameterNames = methodSignature.getParameterNames();
//        if (null == parameterNames || 0 == parameterNames.length){
//            apiLog.setParams("{}");
//            return;
//        }
//        JSONObject jObject = new JSONObject();
//        for (int i = 0, len = parameterNames.length; i < len; i++) {
//            jObject.put(parameterNames[i], args[i]);
//        }
//        apiLog.setParams(StringUtils.substring(jObject.toJSONString(), 0, 2000));
    }

    private String getMessage(int status, String msg) {
        return "{\"success\":\"false\",\"status\":\"" + status + "\", \"msg\":\"" + msg + "\",\"data\": null}";
    }


}

package com.github.itdachen.boot.oplog.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.boot.oplog.constants.OplogConstant;
import com.github.itdachen.boot.oplog.entity.LogInfo;
import com.github.itdachen.boot.oplog.entity.OplogClient;
import com.github.itdachen.boot.oplog.manager.service.IOplogClientHandler;
import com.github.itdachen.boot.oplog.utils.ResCollectionUtils;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.annotation.CheckApiClient;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.context.annotation.Log;
import com.github.itdachen.framework.context.id.IdUtils;
import com.github.itdachen.framework.tools.ServletUtils;
import com.github.itdachen.framework.tools.ip.IpAddressUtils;
import com.github.itdachen.framework.tools.request.BrowserUtils;
import com.github.itdachen.framework.tools.request.OSUtils;
import com.github.itdachen.framework.tools.useragent.UserAgentUtils;
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
 * Description: 操作日志 AOP
 * Created by 王大宸 on 2021-12-01 16:49
 * Created with IntelliJ IDEA.
 */
@Aspect
//@Component
public class OplogAspectj {
    private static final Logger logger = LoggerFactory.getLogger(OplogAspectj.class);

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 3,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("oplog-aspect-thread-" + ThreadLocalRandom.current().nextInt(1000));
                    return thread;
                }
            });


    @Pointcut("@annotation(log)")
    public void logPointCut(Log log) {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut(log)", returning = "resJson", argNames = "joinPoint,log,resJson")
    public void doAfterReturning(JoinPoint joinPoint, Log log, Object resJson) {
        if (null == log) {
            return;
        }

        /* 操作日志基础信息 */
        LogInfo oplogInfo = setRequest(joinPoint, log);

        oplogInfo.setResponseCode("200");
        oplogInfo.setResponseJson("{}");
        oplogInfo.setResponseStatus(OplogConstant.IS_OK);
        oplogInfo.setResponseMsg("操作成功！");


        if (null == resJson) {
            oplogInfo.setResponseJson("{}");
        } else if (ResCollectionUtils.isArray(resJson)) {
            oplogInfo.setResponseJson(JSONObject.toJSONString(resJson));
        } else {
            /* 响应数据 */
            String jsonString = JSONObject.toJSONString(resJson);
            JSONObject json = JSONObject.parseObject(jsonString);
            oplogInfo.setResponseMsg(json.getString("msg"));
            oplogInfo.setResponseJson(JSONObject.toJSONString(resJson));
            if (!json.getBooleanValue("success")) {
                oplogInfo.setResponseStatus(OplogConstant.IS_ERR);
            }
        }


        try {
            // 保存数据库
            // AsyncThreadsManager.me().execute(OplogAsyncFactory.recordOplog(apiLog));

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        AppContextHelper.getBean(IOplogClientHandler.class).save(oplogInfo);
                    } catch (Exception e) {
                        logger.error("操作日志数据入库异常: ", e);
                    }
                }
            });


        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==前置通知异常==异常信息: {}", exp.getMessage(), exp);
        }
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param ex        异常
     */
    @AfterThrowing(value = "logPointCut(log)", throwing = "ex", argNames = "joinPoint,log,ex")
    public void doAfterThrowing(JoinPoint joinPoint, Log log, Exception ex) {
        if (null == log) {
            return;
        }

        /* 操作日志基础信息 */
        LogInfo oplogInfo = setRequest(joinPoint, log);

        /* 响应数据 */
        String jsonString = JSONObject.toJSONString(ex);
        JSONObject json = JSONObject.parseObject(jsonString);

        oplogInfo.setResponseCode(json.getString("status"));
        oplogInfo.setResponseJson(jsonString);
        oplogInfo.setResponseStatus(OplogConstant.IS_ERR);
        oplogInfo.setResponseMsg(getMessage(json.getIntValue("status"), ex.getMessage()));


        try {
            // 保存数据库
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        AppContextHelper.getBean(IOplogClientHandler.class).save(oplogInfo);
                    } catch (Exception e) {
                        logger.error("操作日志数据入库异常: ", e);
                    }
                }
            });
        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==前置通知异常==异常信息: {}", exp.getMessage(), exp);
        }

    }


    private LogInfo setRequest(JoinPoint joinPoint, Log log) {
        // *========数据库日志=========*//
        HttpServletRequest request = ServletUtils.getRequest();

        String userAgent = request.getHeader("user-agent");
        if (null == userAgent) {
            userAgent = request.getHeader("User-Agent");
        }


        LogInfo logInfo = new LogInfo().builder()
                .id(IdUtils.getId())
                .opCode(log.value().getCode())
                .opTitle(log.value().getTitle())
                .opVersion(log.version())
                .opTime(LocalDateTime.now())


                .hostIp(IpAddressUtils.getNetworkIp(request))
                .hostAddress("-")
                .hostOs(OSUtils.osInfo(request))
                .hostBrowser(BrowserUtils.browserInfo(request))
                .userAgent(userAgent)

                .requestId(request.getRequestId())
                .requestUri(request.getRequestURI())
                .requestMethod(request.getMethod())
                .requestParams(getRequestValue(joinPoint)) // 请求参数

                .build();


        CheckApiClient apiClient = joinPoint.getTarget().getClass().getAnnotation(CheckApiClient.class);
        if (null != apiClient) {
            logInfo.setMenuTitle(apiClient.title());
        }
        if (null == apiClient) {
            FuncTitle funcTitle = joinPoint.getTarget().getClass().getAnnotation(FuncTitle.class);
            if (null != funcTitle) {
                logInfo.setMenuTitle(funcTitle.value());
            }
        }

        return logInfo;
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

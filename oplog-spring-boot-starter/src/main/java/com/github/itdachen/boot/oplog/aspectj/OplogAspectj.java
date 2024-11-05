package com.github.itdachen.boot.oplog.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.boot.oplog.constants.OplogConstant;
import com.github.itdachen.boot.oplog.entity.OplogClient;
import com.github.itdachen.boot.oplog.manager.service.IOplogClientService;
import com.github.itdachen.boot.oplog.utils.ResCollectionUtils;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.annotation.CheckApiClient;
import com.github.itdachen.framework.context.annotation.Log;
import com.github.itdachen.framework.context.id.IdUtils;
import com.github.itdachen.framework.tools.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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
        OplogClient apiLog = setRequest(joinPoint, log);
        apiLog.setMakeUseStatus(OplogConstant.IS_OK);

        apiLog.setMakeUseStatus("200");
        apiLog.setMsg("操作成功！");
        apiLog.setJsonResult("[]");
        if (null == resJson) {
            apiLog.setJsonResult("[]");
        } else if (ResCollectionUtils.isArray(resJson)) {
            apiLog.setJsonResult(JSONObject.toJSONString(resJson));
        } else {
            /* 响应数据 */
            String jsonString = JSONObject.toJSONString(resJson);
            JSONObject json = JSONObject.parseObject(jsonString);
            apiLog.setMsg(json.getString("msg"));
            apiLog.setJsonResult(JSONObject.toJSONString(resJson));
            if (!json.getBooleanValue("success")) {
                apiLog.setMakeUseStatus(OplogConstant.IS_ERR);
            }
        }


        try {
            // 保存数据库
            // AsyncThreadsManager.me().execute(OplogAsyncFactory.recordOplog(apiLog));

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        AppContextHelper.getBean(IOplogClientService.class).save(apiLog);
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
        OplogClient apiLog = setRequest(joinPoint, log);

        /* 响应数据 */
        String jsonString = JSONObject.toJSONString(ex);
        JSONObject json = JSONObject.parseObject(jsonString);

        apiLog.setJsonResult(getMessage(json.getIntValue("status"), ex.getMessage()));
        apiLog.setMakeUseStatus(OplogConstant.IS_ERR);
        apiLog.setMsg(json.getString("msg"));

        try {
            // 保存数据库
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        AppContextHelper.getBean(IOplogClientService.class).save(apiLog);
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


    private OplogClient setRequest(JoinPoint joinPoint, Log log) {
        // *========数据库日志=========*//
        OplogClient apiLog = new OplogClient();
        apiLog.setRequestMethod(ServletUtils.getRequest().getMethod());

        // 请求参数
        apiLog.setParams(getRequestValue(joinPoint));

        apiLog.setTenantId(BizContextHandler.getTenantId());

        apiLog.setServiceId(ServletUtils.getRequest().getContextPath());
        apiLog.setRequestUri(ServletUtils.getRequest().getRequestURI());
        apiLog.setMakeUseIp(ServletUtils.getIPAddress());
        apiLog.setLogType(log.type());
        apiLog.setMakeUseType(log.title());

        CheckApiClient apiClient = joinPoint.getTarget().getClass().getAnnotation(CheckApiClient.class);
        if (null != apiClient) {
            apiLog.setMenuTitle(apiClient.title());
            apiLog.setClientId(apiClient.clientId());
        }

        // 获取当前的用户
        apiLog.setId(IdUtils.getId());
        apiLog.setCreateTime(LocalDateTime.now());
        apiLog.setCreateUser(BizContextHandler.getNickName());
        apiLog.setCreateUserId(BizContextHandler.getUserId());

        return apiLog;
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

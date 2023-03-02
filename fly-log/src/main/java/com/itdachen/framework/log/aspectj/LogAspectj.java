package com.itdachen.framework.log.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.itdachen.framework.context.BizContextHandler;
import com.itdachen.framework.context.annotation.CheckApiClient;
import com.itdachen.framework.context.annotation.Log;
import com.itdachen.framework.context.snowflake.IdUtils;
import com.itdachen.framework.core.response.ServerResponse;
import com.itdachen.framework.log.constants.ApiLogConstant;
import com.itdachen.framework.log.entity.ApiLogClient;
import com.itdachen.framework.log.manager.AsyncFactory;
import com.itdachen.framework.log.manager.AsyncManager;
import com.itdachen.framework.tools.ObjectUtils;
import com.itdachen.framework.tools.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * Description:
 * Created by 王大宸 on 2021-12-01 16:49
 * Created with IntelliJ IDEA.
 */
@Aspect
@Component
public class LogAspectj {
    private static final Logger logger = LoggerFactory.getLogger(LogAspectj.class);

    // 配置织入点
    @Pointcut("@annotation(com.itdachen.framework.context.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    /**
     * @param joinPoint  切点
     * @param e          异常
     * @param resultJson 返回数据
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object resultJson) {
        try {
            // 获得注解
            Log log = getAnnotationLog(joinPoint);
            if (null == log) {
                return;
            }

            // *========数据库日志=========*//
            ApiLogClient apiLog = new ApiLogClient();
            apiLog.setMethod(ServletUtils.getRequest().getMethod());
            setRequestValue(apiLog, joinPoint);
            apiLog.setJsonResult(JSONObject.toJSONString(resultJson));
            apiLog.setRequestUri(ServletUtils.getRequest().getRequestURI());
            apiLog.setRemoteIp(ServletUtils.getIPAddress());
            apiLog.setRemoteType(log.type());
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            apiLog.setMethod(className + "." + methodName + "()");

            // 获取当前的用户
            apiLog.setId(IdUtils.getId());
            apiLog.setCreateTime(LocalDateTime.now());
            apiLog.setCreateUser(BizContextHandler.getUserName());
            apiLog.setCreateUserId(BizContextHandler.getUserId());

            CheckApiClient apiClient = joinPoint.getTarget().getClass().getAnnotation(CheckApiClient.class);
            if (null != apiClient) {
                apiLog.setTitle(apiClient.title());
                apiLog.setClientId(apiClient.clientId());
            }

            // 业务操作
            ServerResponse res = ObjectUtils.objToClass(resultJson, ServerResponse.class);
            if (null != res) {
                apiLog.setRemoteStatus(ApiLogConstant.IS_OK);
                apiLog.setMsg(res.getMsg());
                if (!res.getSuccess()) {
                    apiLog.setRemoteStatus(ApiLogConstant.IS_ERR);
                }
            }
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(apiLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==前置通知异常==异常信息: {}", exp.getMessage(), exp);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param apiLog 操作日志
     */
    private void setRequestValue(ApiLogClient apiLog, JoinPoint joinPoint) {
        // 1、获取方法的参数的字符串数组
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //2.获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        JSONObject jObject = new JSONObject();
        for (int i = 0, len = parameterNames.length; i < len; i++) {
            jObject.put(parameterNames[i], args[i]);
        }
        apiLog.setParams(StringUtils.substring(jObject.toJSONString(), 0, 2000));
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}

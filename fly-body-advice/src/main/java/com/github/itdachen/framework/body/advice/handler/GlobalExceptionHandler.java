package com.github.itdachen.framework.body.advice.handler;

import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.exception.RateLimiterException;
import com.github.itdachen.framework.core.response.ServerResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description: 全局异常处理
 * Created by 王大宸 on 2023/02/26 23:42
 * Created with IntelliJ IDEA.
 */
@RestControllerAdvice(basePackages = {"com.github.itdachen"})
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /***
     * 统一异常处理,自定义抛出异常
     *
     * @author 王大宸
     * @date 2023/3/18 18:34
     * @param response response
     * @param ex ex
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.String>
     */
    @ExceptionHandler(BizException.class)
    public ServerResponse<String> bizExceptionHandler(HttpServletResponse response, BizException ex) {
        logger.error(ex.getMessage());
        response.setStatus(HttpStatus.OK.value());
        if (StringUtils.isEmpty(ex.getMessage())) {
            return ServerResponse.errMsg("出现未知错误, 请联系技术人员!");
        }
        return ServerResponse.errMsg(ex.getMessage());
    }

    /***
     * 限流异常
     *
     * @author 王大宸
     * @date 2023/7/3 11:25
     * @param response response
     * @param ex ex
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.String>
     */
    @ExceptionHandler(RateLimiterException.class)
    public ServerResponse<String> rateLimiterException(HttpServletResponse response, RateLimiterException ex) {
        logger.error("RateLimiterException: {}", ex.getMessage());
        response.setStatus(HttpStatus.OK.value());
        if (StringUtils.isEmpty(ex.getMessage())) {
            return ServerResponse.errStatusMsg(429, "系统繁忙, 请稍后再试!");
        }
        return ServerResponse.errStatusMsg(429, ex.getMessage());
    }

    /***
     * 断言异常
     *
     * @author 王大宸
     * @date 2023/4/29 18:50
     * @param response response
     * @param ex ex
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.String>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ServerResponse<String> illegalArgumentExceptionHandler(HttpServletResponse response, IllegalArgumentException ex) {
        logger.error("IllegalArgumentException: ", ex.getMessage());
        response.setStatus(HttpStatus.OK.value());
        if (StringUtils.isEmpty(ex.getMessage())) {
            return ServerResponse.errMsg("出现未知错误, 请联系技术人员!");
        }
        return ServerResponse.errMsg(ex.getMessage());
    }


    /***
     * 其他异常
     *
     * @author 王大宸
     * @date 2023/3/18 18:34
     * @param response response
     * @param ex ex
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.String>
     */
    @ExceptionHandler(Exception.class)
    public ServerResponse<String> exceptionHandler(HttpServletResponse response, Exception ex) {
        logger.error("未知错误: ", ex);
        response.setStatus(HttpStatus.OK.value());
        if (StringUtils.isEmpty(ex.getMessage())) {
            return ServerResponse.errMsg("未知错误,请联系管理员");
        }
        return ServerResponse.errMsg(ex.getMessage());
    }


}

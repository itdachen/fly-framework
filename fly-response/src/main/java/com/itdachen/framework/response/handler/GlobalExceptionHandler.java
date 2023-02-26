package com.itdachen.framework.response.handler;

import com.itdachen.framework.core.exception.BizException;
import com.itdachen.framework.core.response.ServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * Description: 全局异常处理
 * Created by 王大宸 on 2023/02/26 23:42
 * Created with IntelliJ IDEA.
 */
@RestControllerAdvice(basePackages = {"com.itdachen"})
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 统一异常处理,自定义抛出异常
     */
    @ExceptionHandler(BizException.class)
    public ServerResponse<String> bizExceptionHandler(HttpServletResponse response, BizException ex) {
        logger.error(ex.getMessage());
        response.setStatus(HttpStatus.OK.value());
        if (StringUtils.isEmpty(ex.getMessage())) {
            return ServerResponse.errMsg("出现未知错误,请联系技术人员!");
        }
        return ServerResponse.errMsg(ex.getMessage());
    }


    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public ServerResponse<String> baseExceptionHandler(HttpServletResponse response, Exception ex) {
        logger.error("未知错误: ", ex);
        response.setStatus(HttpStatus.OK.value());
        if (StringUtils.isEmpty(ex.getMessage())) {
            return ServerResponse.errMsg("未知错误,请联系管理员");
        }
        return ServerResponse.errMsg(ex.getMessage());
    }


}

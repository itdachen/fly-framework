package com.github.itdachen.framework.boot.body.advice.handler;

import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.exception.RateLimiterException;
import com.github.itdachen.framework.core.response.ServerResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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
            return ServerResponse.err("出现未知错误, 请联系技术人员!");
        }
        return ServerResponse.err(ex.getStatus(), ex.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ServerResponse<String> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ServerResponse.err("不支持' " + e.getMethod() + " '请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ServerResponse<String> handlerRuntimeException(RuntimeException ex) {
        logger.error("运行时异常: ", ex);
        return ServerResponse.err("发生了一个错误, 请联系管理员！");
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
            return ServerResponse.err(429, "系统繁忙, 请稍后再试!");
        }
        return ServerResponse.err(429, ex.getMessage());
    }


    /***
     * 方法参数验证异常
     *
     * @author 王大宸
     * @date 2024/5/5 22:11
     * @param response response
     * @param ex ex
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.String>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServerResponse<String> methodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex) {
        logger.error("methodArgumentNotValidException: {}", ex.getMessage());
        response.setStatus(HttpStatus.OK.value());
        if (StringUtils.isEmpty(ex.getMessage())) {
            return ServerResponse.err(500, "发生了一个错误, 请联系管理员!");
        }
        return handleBindingResult(ex.getBindingResult());
    }


    /***
     * 处理【MethodArgumentNotValidException】异常；提取错误信息，构建 ServerResponse 统一返回对象；
     *
     * @author 王大宸
     * @date 2024/5/5 22:20
     * @param result result
     * @return ApiRestResponse
     */
    private ServerResponse<String> handleBindingResult(BindingResult result) {
        //把【MethodArgumentNotValidException异常】处理为，对应的ApiRestResponse统一返回对象；
        //这儿创建一个List集合；后面我们在MethodArgumentNotValidException中获取的错误信息，都存放在这个集合中去；
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {//如果BindingResult中，包含错误，就获取其中所有的错误信息；
            List<ObjectError> allErrors = result.getAllErrors();
            //遍历所有的错误信息；
            for (int i = 0; i < allErrors.size(); i++) {
                ObjectError objectError = allErrors.get(i);
                //提取具体的错误信息；
                String message = objectError.getDefaultMessage();
                //将错误信息，添加到list集合中
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return ServerResponse.err();
        }
        //根据MethodArgumentNotValidException异常的具体错误信息，构建 ServerResponse 统一返回对象；
        return ServerResponse.err(10012, list.toString());
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
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ServerResponse<String> illegalArgumentExceptionHandler(HttpServletResponse response, IllegalArgumentException ex) {
//        logger.error("断言异常: ", ex);
//        response.setStatus(HttpStatus.OK.value());
//        if (StringUtils.isEmpty(ex.getMessage())) {
//            return ServerResponse.err("出现未知错误, 请联系技术人员!");
//        }
//        return ServerResponse.err(ex.getMessage());
//    }


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
    public ServerResponse<String> throwableHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(HttpStatus.OK.value());
        if (null == ex) {
            logger.error("发生了一个未知错误, Exception 为空 ...");
            return ServerResponse.err("发生了一个错误, 请联系管理员！");
        }
        Throwable cause = ex.getCause();
        if (null == cause) {
            logger.error("服务器发生了一个错误: {}", ex.getMessage(), ex);
            return ServerResponse.err("发生了一个错误, 请联系管理员！");
        }
        if (null == ex.getCause().getMessage() || "".equals(ex.getCause().getMessage())
                || "null".equals(ex.getCause().getMessage())) {
            logger.error("服务器发生了一个错误, 提示消息 [ ex.getCause().getMessage() ] 为空！", ex);
            return ServerResponse.err("发生了一个错误, 请联系管理员！");
        }
        logger.error("服务器发生了一个错误: {}", ex.getCause().getMessage(), ex);
        return ServerResponse.err("发生了一个错误, 请联系管理员！");
    }


}

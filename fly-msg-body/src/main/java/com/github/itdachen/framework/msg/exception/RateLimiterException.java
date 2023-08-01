package com.github.itdachen.framework.msg.exception;

import com.github.itdachen.framework.msg.enums.HttpStatusCode;

/**
 * Description: 限流异常
 * 使用 AOP 处理限流, 这里只能继承 RuntimeException 异常
 * 如果继承的是 Exception, 将会抛出 UndeclaredThrowableException 且无法捕捉
 * Created by 王大宸 on 2023-07-03 11:06
 * Created with IntelliJ IDEA.
 */
public class RateLimiterException extends RuntimeException {

    private Integer status = HttpStatusCode.RATE_LIMITER.getStatus();

    private Object data = null;

    public RateLimiterException() {
        super(HttpStatusCode.RATE_LIMITER.getMessage());
    }

    public RateLimiterException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public RateLimiterException(String message, Integer status, Object data) {
        super(message);
        this.status = status;
        this.data = data;
    }

    public RateLimiterException(String message) {
        super(message);
        this.status = 429;
    }

    public RateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateLimiterException(Throwable cause) {
        super(cause);
    }

    public RateLimiterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

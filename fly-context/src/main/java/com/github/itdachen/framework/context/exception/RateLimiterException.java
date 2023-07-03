package com.github.itdachen.framework.context.exception;

/**
 * Description: 限流异常
 * Created by 王大宸 on 2023-07-03 11:06
 * Created with IntelliJ IDEA.
 */
public class RateLimiterException extends Exception {

    private Integer status = 429;

    private Object data = null;

    public RateLimiterException() {
        super("系统繁忙,请稍后再试");
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

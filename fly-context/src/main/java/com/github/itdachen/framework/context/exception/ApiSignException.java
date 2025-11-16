package com.github.itdachen.framework.context.exception;

/**
 * 接口签名异常
 *
 * @author 剑鸣秋朔
 * @date 2024-11-04 10:24
 */
public class ApiSignException extends Exception {

    private Integer status = 500;

    private Object data = null;

    public ApiSignException() {
    }

    public ApiSignException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public ApiSignException(String message, Integer status, Object data) {
        super(message);
        this.status = status;
        this.data = data;
    }

    public ApiSignException(String message) {
        super(message);
    }

    public ApiSignException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiSignException(Throwable cause) {
        super(cause);
    }

    public ApiSignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
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

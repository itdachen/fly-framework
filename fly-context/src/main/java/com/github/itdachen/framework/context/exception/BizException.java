package com.github.itdachen.framework.context.exception;

/**
 * Description: 自定义基础异常
 * Created by 王大宸 on 2023/02/12 22:04
 * Created with IntelliJ IDEA.
 */
public class BizException extends Exception {

    private Integer status = 500;

    private Object data = null;

    public BizException() {
    }

    public BizException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public BizException(String message, Integer status, Object data) {
        super(message);
        this.status = status;
        this.data = data;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
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

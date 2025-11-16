package com.github.itdachen.framework.msg.exception;

import com.github.itdachen.framework.msg.enums.HttpStatusCode;

/**
 * Description: 自定义基础异常
 * Created by 剑鸣秋朔 on 2023-08-01 9:31
 * Created with IntelliJ IDEA.
 */
public class BizException extends Exception {

    private Integer status = HttpStatusCode.ERROR.getStatus();

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

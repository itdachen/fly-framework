package com.github.itdachen.framework.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Description: 安全认证自定义基础异常
 * Created by 王大宸 on 2022-10-16 13:38
 * Created with IntelliJ IDEA.
 */
public class BizSecurityException  extends AuthenticationException {

    private int status = 403;

    private Object data = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BizSecurityException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BizSecurityException(String message, int status, Object data) {
        super(message);
        this.status = status;
        this.data = data;
    }

    public BizSecurityException(String message) {
        super(message);
    }

    public BizSecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}

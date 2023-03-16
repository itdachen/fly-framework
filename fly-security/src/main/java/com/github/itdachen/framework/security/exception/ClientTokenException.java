package com.github.itdachen.framework.security.exception;

/**
 * Description: 客户端异常
 * Created by 王大宸 on 2022-09-23 10:08
 * Created with IntelliJ IDEA.
 */
public class ClientTokenException extends Exception {

    private int status = 401;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ClientTokenException() {
    }

    public ClientTokenException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ClientTokenException(String message) {
        super(message);
    }

    public ClientTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientTokenException(Throwable cause) {
        super(cause);
    }

    public ClientTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

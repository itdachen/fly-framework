package com.github.itdachen.framework.msg.body;


import com.github.itdachen.framework.msg.enums.HttpStatusCode;

import java.io.Serializable;

/**
 * 全局业务返回数据格式
 *
 * @author 剑鸣秋朔
 * @date 2023/02/12 22:07
 */
public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = 8540566017796274461L;

    /**
     * 是否成功
     */
    private Boolean success = true;

    /**
     * 状态:
     */
    private Integer status = 200;

    /**
     * 消息
     */
    private String msg = "操作成功";

    /**
     * 数据
     */
    private T data;


    private ServerResponse() {
    }

    public ServerResponse(Boolean success, Integer status, String msg, T data) {
        this.success = success;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功
     */
    public static <T> ServerResponse<T> ok() {
        return new ServerResponse<T>(HttpStatusCode.SUCCESS.getSuccess(), HttpStatusCode.SUCCESS.getStatus(), HttpStatusCode.SUCCESS.getMessage(), null);
    }

    public static <T> ServerResponse<T> okMsg(String msg) {
        return new ServerResponse<T>(HttpStatusCode.SUCCESS.getSuccess(), HttpStatusCode.SUCCESS.getStatus(), msg, null);
    }

    public static <T> ServerResponse<T> okData(T data) {
        return new ServerResponse<T>(HttpStatusCode.SUCCESS.getSuccess(), HttpStatusCode.SUCCESS.getStatus(), HttpStatusCode.SUCCESS.getMessage(), data);
    }

    public static <T> ServerResponse<T> okMsgData(String msg, T data) {
        return new ServerResponse<T>(HttpStatusCode.SUCCESS.getSuccess(), HttpStatusCode.SUCCESS.getStatus(), msg, data);
    }

    public static <T> ServerResponse<T> okStatusMsg(int status, String msg) {
        return new ServerResponse<T>(HttpStatusCode.SUCCESS.getSuccess(), HttpStatusCode.SUCCESS.getStatus(), msg, null);
    }

    public static <T> ServerResponse<T> okStatusMsgDate(int status, String msg, T data) {
        return new ServerResponse<T>(HttpStatusCode.SUCCESS.getSuccess(), status, msg, data);
    }

    /**
     * 操作错误
     */
    public static <T> ServerResponse<T> err() {
        return new ServerResponse<T>(HttpStatusCode.ERROR.getSuccess(), HttpStatusCode.ERROR.getStatus(), HttpStatusCode.ERROR.getMessage(), null);
    }

    public static <T> ServerResponse<T> errMsg(String msg) {
        return new ServerResponse<T>(HttpStatusCode.ERROR.getSuccess(), HttpStatusCode.ERROR.getStatus(), msg, null);
    }

    public static <T> ServerResponse<T> errData(T data) {
        return new ServerResponse<T>(HttpStatusCode.ERROR.getSuccess(), HttpStatusCode.ERROR.getStatus(), HttpStatusCode.ERROR.getMessage(), data);
    }

    public static <T> ServerResponse<T> errMsgData(String msg, T data) {
        return new ServerResponse<T>(HttpStatusCode.ERROR.getSuccess(), HttpStatusCode.ERROR.getStatus(), msg, data);
    }

    public static <T> ServerResponse<T> errStatusMsg(int status, String msg) {
        return new ServerResponse<T>(HttpStatusCode.ERROR.getSuccess(), status, msg, null);
    }

    public static <T> ServerResponse<T> errStatus(int status) {
        return new ServerResponse<T>(HttpStatusCode.ERROR.getSuccess(), status, null, null);
    }

    public static <T> ServerResponse<T> errStatusMsgDate(int status, String msg, T data) {
        return new ServerResponse<T>(HttpStatusCode.ERROR.getSuccess(), status, msg, data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

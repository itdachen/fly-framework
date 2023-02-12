package com.itdachen.framework.core.response;

import com.itdachen.framework.core.enums.ReturnCode;

import java.io.Serializable;

/**
 * 全局业务返回数据格式
 *
 * @author 王大宸
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
        return new ServerResponse<T>(ReturnCode.SUCCESS.getSuccess(), ReturnCode.SUCCESS.getStatus(), ReturnCode.SUCCESS.getMessage(), null);
    }

    public static <T> ServerResponse<T> okMsg(String msg) {
        return new ServerResponse<T>(ReturnCode.SUCCESS.getSuccess(), ReturnCode.SUCCESS.getStatus(), msg, null);
    }

    public static <T> ServerResponse<T> okData(T data) {
        return new ServerResponse<T>(ReturnCode.SUCCESS.getSuccess(), ReturnCode.SUCCESS.getStatus(), ReturnCode.SUCCESS.getMessage(), data);
    }

    public static <T> ServerResponse<T> okMsgData(String msg, T data) {
        return new ServerResponse<T>(ReturnCode.SUCCESS.getSuccess(), ReturnCode.SUCCESS.getStatus(), msg, data);
    }

    public static <T> ServerResponse<T> okStatusMsg(int status, String msg) {
        return new ServerResponse<T>(ReturnCode.SUCCESS.getSuccess(), ReturnCode.SUCCESS.getStatus(), msg, null);
    }

    public static <T> ServerResponse<T> okStatusMsgDate(int status, String msg, T data) {
        return new ServerResponse<T>(ReturnCode.SUCCESS.getSuccess(), status, msg, data);
    }

    /**
     * 操作错误
     */
    public static <T> ServerResponse<T> err() {
        return new ServerResponse<T>(ReturnCode.SERVER_ERROR.getSuccess(), ReturnCode.SERVER_ERROR.getStatus(), ReturnCode.SERVER_ERROR.getMessage(), null);
    }

    public static <T> ServerResponse<T> errMsg(String msg) {
        return new ServerResponse<T>(ReturnCode.SERVER_ERROR.getSuccess(), ReturnCode.SERVER_ERROR.getStatus(), msg, null);
    }

    public static <T> ServerResponse<T> errData(T data) {
        return new ServerResponse<T>(ReturnCode.SERVER_ERROR.getSuccess(), ReturnCode.SERVER_ERROR.getStatus(), ReturnCode.SERVER_ERROR.getMessage(), data);
    }

    public static <T> ServerResponse<T> errMsgData(String msg, T data) {
        return new ServerResponse<T>(ReturnCode.SERVER_ERROR.getSuccess(), ReturnCode.SERVER_ERROR.getStatus(), msg, data);
    }

    public static <T> ServerResponse<T> errStatusMsg(int status, String msg) {
        return new ServerResponse<T>(ReturnCode.SERVER_ERROR.getSuccess(), status, msg, null);
    }

    public static <T> ServerResponse<T> errStatus(int status) {
        return new ServerResponse<T>(ReturnCode.SERVER_ERROR.getSuccess(), status, null, null);
    }

    public static <T> ServerResponse<T> errStatusMsgDate(int status, String msg, T data) {
        return new ServerResponse<T>(ReturnCode.SERVER_ERROR.getSuccess(), status, msg, data);
    }

    /**
     * 参数校验错误
     */
    public static <T> ServerResponse<T> valid(T data, String msg) {
        return new ServerResponse<T>(ReturnCode.PARAMS_VALID.getSuccess(), ReturnCode.PARAMS_VALID.getStatus(), msg, data);
    }

    /**
     * 参数校验错误,自定义返回消息
     */
    public static <T> ServerResponse<T> validMsg(String msg) {
        return new ServerResponse<T>(ReturnCode.PARAMS_VALID_MSG.getSuccess(), ReturnCode.PARAMS_VALID_MSG.getStatus(), msg, null);
    }

    public static <T> ServerResponse<T> statusMsg(int status, String msg) {
        return new ServerResponse<T>(ReturnCode.EXCEPTION.getSuccess(), status, msg, null);
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

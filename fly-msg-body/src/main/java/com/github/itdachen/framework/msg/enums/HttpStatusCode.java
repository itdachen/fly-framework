package com.github.itdachen.framework.msg.enums;

/**
 * Description: 状态码
 * Created by 剑鸣秋朔 on 2023-08-01 9:27
 * Created with IntelliJ IDEA.
 */
public enum HttpStatusCode {


    // 操作成功与失败
    SUCCESS(true, 200, "操作成功！"),
    ERROR(false, 500, "操作失败！"),

    RATE_LIMITER(false, 429, "系统繁忙，请稍后再试！"),

    ;

    // 响应是否成功
    private final Boolean success;
    // 响应状态码
    private final Integer status;
    // 响应信息
    private final String message;

    HttpStatusCode(Boolean success, Integer status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

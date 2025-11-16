package com.github.itdachen.framework.core.enums;

/**
 * Description: 常用返回码
 * * 1xx: 指示信息--表示请求已接收，继续处理。
 * * 2xx: 成功--表示请求已被成功接收、理解、接受。
 * * 3xx: 重定向--要完成请求必须进行更进一步的操作。
 * * 4xx: 客户端错误--请求有语法错误或请求无法实现。
 * * 5xx: 服务器端错误--服务器未能实现合法的请求
 * Created by 剑鸣秋朔 on 2023/02/12 22:08
 * Created with IntelliJ IDEA.
 */
public enum ReturnCode {

    // 操作成功与失败
    SUCCESS(true, 200, "操作成功！"),
    SERVER_ERROR(false, 500, "操作失败！"),

    // 未知异常
    EXCEPTION(false, 500, "系统异常,请联系管理员！"),

    /* 未授权, 登录失败, 服务器配置问题导致登录失败  */
    UN_AUTHORIZED(false, 401, "未授权！"),
    /* 禁止访问 */
    FORBIDDEN(false, 403, "禁止访问！"),
    /* 资源被禁止, 资源未启用 */
    RESOURCES_IS_BAN(false, 405, "资源未启用！"),
    UN_ACCEPTABLE(false, 406, "无法接受！"),
    /* 永远不可用, 被禁用*/
    NEVER_AVAILABLE(false, 406, "资源已被禁用, 且永远不可用！"),

    GATEWAY_ERROR(false, 502, "错误网关！"),
    SERVICE_UNAVAILABLE(false, 503, "无法获得服务！"),

    // 请求参数校验
    PARAMS_VALID(false, 400001, "参数校验错误！"),
    PARAMS_VALID_MSG(false, 400002, "参数校验错误！"),

    /**
     * 订单
     */
    ORDER_CONFIRM_PRICE_FAIL(false, 280002, "创建订单-验价失败"),
    ORDER_CONFIRM_REPEAT(false, 280008, "订单恶意-重复提交"),
    ORDER_CONFIRM_TOKEN_EQUAL_FAIL(false, 280009, "订单令牌缺少"),
    ORDER_CONFIRM_NOT_EXIST(false, 280010, "订单不存在"),

    /**
     * 支付
     */
    PAY_ORDER_FAIL(false, 300001, "创建支付订单失败"),
    PAY_ORDER_CALLBACK_SIGN_FAIL(false, 300002, "支付订单回调验证签失败"),
    PAY_ORDER_CALLBACK_NOT_SUCCESS(false, 300003, "支付回调更新处理失败"),
    PAY_ORDER_NOT_EXIST(false, 300005, "订单不存在"),
    PAY_ORDER_STATE_ERROR(false, 300006, "订单状态不正常"),
    PAY_ORDER_PAY_TIMEOUT(false, 300007, "订单支付超时"),


    ;

    // 响应是否成功
    private final Boolean success;
    // 响应状态码
    private final Integer status;
    // 响应信息
    private final String message;

    ReturnCode(Boolean success, Integer status, String message) {
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

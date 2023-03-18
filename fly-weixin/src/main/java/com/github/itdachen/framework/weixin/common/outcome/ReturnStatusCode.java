package com.github.itdachen.framework.weixin.common.outcome;

/**
 * Description: 通用返回状态码
 * Created by 王大宸 on 2023/03/18 22:48
 * Created with IntelliJ IDEA.
 */
public class ReturnStatusCode {
    public static final String ERR_CODE_FILED = "errcode";
    public static final String ERR_MSG_FILED = "errmsg";

    public static Integer OK = 0;
    public static Integer ERROR = -1;

    /***
     * 根据状态码, 返回错误信息
     *
     * @author 王大宸
     * @date 2023/3/18 23:04
     * @param codeValue 状态码
     * @return java.lang.String
     */
    public static String errorCodeValue(Integer codeValue) {
        switch (codeValue) {
            case -1 -> {
                return "系统繁忙";
            }
            case 0 -> {
                return "请求成功";
            }
            case 45009 -> {
                return "接口调用超过限制";
            }
            case 40001 -> {
                return "token 错误";
            }
            case 48001 -> {
                return "api 功能未授权，请确认公众号已获得该接口";
            }
            case 41001 -> {
                return "缺少 access_token 参数";
            }
            case 42001 -> {
                return "access_token 超时";
            }
            case 41002 -> {
                return "缺少 appid 参数";
            }
            case 40013 -> {
                return "不合法的 appid";
            }
            case 61016 -> {
                return "API的功能类别需要通过组件进行确认";
            }
            case 61007 -> {
                return "api未经组件授权";
            }
            case 48004 -> {
                return "api 接口被封禁";
            }
            case 40066 -> {
                return "不合法的 url";
            }
            case 45011 -> {
                return "API 调用太频繁，请稍候再试";
            }
            case 61004 -> {
                return "把ip加入白名单ip列表再重试";
            }
            case 45002 -> {
                return "消息内容超过限制";
            }
            case 43003 -> {
                return "需要 HTTPS 请求";
            }
            case 41018 -> {
                return "缺少组件_appid";
            }
            case 43001 -> {
                return "需要 GET 请求";
            }
            case 43002 -> {
                return "需要 POST 请求";
            }
            case 50002 -> {
                return "用户受限，可能是用户帐号被冻结或注销";
            }
            case 40164 -> {
                return "无效ip，不在白名单中";
            }
            case 40014 -> {
                return "不合法的 access_token";
            }
            case 61014 -> {
                return "请按照文档使用 component_access_token 调用api";
            }
            case 45035 -> {
                return "访问客户端ip未注册，不在ip白名单中";
            }
            default -> {
                return null;
            }
        }
    }


}

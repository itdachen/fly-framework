package com.github.itdachen.framework.boot.wechat.biz;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信官方返回状态码
 *
 * @author 剑鸣秋朔
 * @date 2024/12/1 15:08
 */
public class WeChatHttpStatus {

    public static final String ERR_CODE_FILED = "errcode";
    public static final String ERR_MSG_FILED = "errmsg";
    public static final String DEFAULT_MSG = "微信官方发生了一个错误";

    public static final Integer OK = 0;
    public static final Integer ERROR = -1;

    /* 微信官方返回状态码 */
    public static final Map<Integer, String> HTTP_STATUS_MAP = new HashMap<>();


    static {
        HTTP_STATUS_MAP.put(ERROR, "NO OK!");
        HTTP_STATUS_MAP.put(OK, "OK!");
        HTTP_STATUS_MAP.put(45009, "接口调用超过限制");
        HTTP_STATUS_MAP.put(40001, "token 错误");
        HTTP_STATUS_MAP.put(48001, "api 功能未授权，请确认公众号已获得该接口");
        HTTP_STATUS_MAP.put(41001, "缺少 access_token 参数");
        HTTP_STATUS_MAP.put(42001, "access_token 超时");
        HTTP_STATUS_MAP.put(41002, "缺少 appid 参数");
        HTTP_STATUS_MAP.put(40013, "不合法的 appid");
        HTTP_STATUS_MAP.put(61016, "API的功能类别需要通过组件进行确认");
        HTTP_STATUS_MAP.put(61007, "api未经组件授权");
        HTTP_STATUS_MAP.put(48004, "api 接口被封禁");
        HTTP_STATUS_MAP.put(40066, "不合法的 url");
        HTTP_STATUS_MAP.put(45011, "API 调用太频繁，请稍候再试");
        HTTP_STATUS_MAP.put(61004, "把ip加入白名单ip列表再重试");
        HTTP_STATUS_MAP.put(45002, "消息内容超过限制");
        HTTP_STATUS_MAP.put(43003, "需要 HTTPS 请求");
        HTTP_STATUS_MAP.put(41018, "缺少组件_appid");
        HTTP_STATUS_MAP.put(43001, "需要 GET 请求");
        HTTP_STATUS_MAP.put(43002, "需要 POST 请求");
        HTTP_STATUS_MAP.put(50002, "用户受限，可能是用户帐号被冻结或注销");
        HTTP_STATUS_MAP.put(40164, "无效ip，不在白名单中");
        HTTP_STATUS_MAP.put(40014, "不合法的 access_token");
        HTTP_STATUS_MAP.put(61014, "请按照文档使用 component_access_token 调用api");
        HTTP_STATUS_MAP.put(45035, "访问客户端ip未注册，不在ip白名单中");

    }


    public static String getMsg(int code) {

        if (OK == code) {
            return "ok";
        }
        if (ERROR == code) {
            return "请求错误!";
        }

        String msg = HTTP_STATUS_MAP.get(code);
        if (null == msg) {
            msg = DEFAULT_MSG;
        }
        return msg;
    }

}

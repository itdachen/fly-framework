package com.github.itdachen.framework.weixin.oplatform.token;

import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.framework.weixin.common.client.WeChatBizHttp;
import com.github.itdachen.framework.weixin.common.outcome.ReturnStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:  第三方平台接口的调用凭据, 每个令牌的有效期为 2 小时
 * 微信后台推送验证票据:
 * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/ThirdParty/token/component_verify_ticket.html
 * Created by 王大宸 on 2023/03/19 0:56
 * Created with IntelliJ IDEA.
 */
public class ComponentAccessTokenApi extends WeChatBizHttp {
    private static final Logger logger = LoggerFactory.getLogger(ComponentAccessTokenApi.class);
    private static final String API_TITLE = "获取第三方平台接口调用凭证(api_component_token)";
    private static final String COMPONENT_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    /***
     * 获取令牌
     *
     * @author 王大宸
     * @date 2023/3/19 1:20
     * @param appId     第三方平台 appid
     * @param appSecret 第三方平台 appsecret
     * @param ticket    验证票据(微信后台推送)
     * @return java.lang.String
     */
    public static String componentAccessToken(String appId, String appSecret, String ticket) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("component_appid", appId);
        jsonObject.put("component_appsecret", appSecret);
        jsonObject.put("component_verify_ticket", ticket);

        JSONObject json = createPost(COMPONENT_TOKEN_API, jsonObject);

        if (json.containsKey("component_access_token")) {
            return json.getString("component_access_token");
        }

        String msg = getErrorCodeValue(json);
        if (null == msg) {
            msg = errorCodeValue(getErrorCode(json));
        }
        logger.error(errorLog(API_TITLE, json));
        throw new Exception(msg);
    }

    private static String errorCodeValue(Integer codeValue) {
        switch (codeValue) {
            case 61004 -> {
                return "访问客户端IP未注册";
            }
            case 61005 -> {
                return "ticket 已过期";
            }
            case 41004 -> {
                return "缺少 secret 参数";
            }
            case 40125 -> {
                return "无效的appsecret";
            }
            case 61006 -> {
                return "ticket 无效";
            }
            case 61011 -> {
                return "invalid component";
            }
            case 45009 -> {
                return "接口调用超过限制";
            }
            case 47001 -> {
                return "解析 JSON/XML 内容错误";
            }
            case 40001 -> {
                return "AppSecret 错误，或者 access_token 无效。";
            }
            case 48001 -> {
                return "api 功能未授权，请确认公众号/小程序已获得该接口";
            }
            default -> {
                return ReturnStatusCode.DEFAULT_MSG;
            }
        }
    }

}

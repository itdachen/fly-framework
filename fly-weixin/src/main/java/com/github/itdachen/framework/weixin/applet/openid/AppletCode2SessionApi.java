package com.github.itdachen.framework.weixin.applet.openid;

import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.framework.weixin.applet.entity.AppletCode2SessionInfo;
import com.github.itdachen.framework.weixin.common.client.WeChatBizHttp;
import com.github.itdachen.framework.weixin.common.outcome.ReturnStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Description: 获取用户信息
 * Created by 剑鸣秋朔 on 2023/03/18 23:50
 * Created with IntelliJ IDEA.
 */
public class AppletCode2SessionApi extends WeChatBizHttp {
    private static final Logger logger = LoggerFactory.getLogger(AppletCode2SessionApi.class);
    private static final String API_TITLE = "获取用户 openId ";
    private static final String USER_OPEN_ID_API = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    /***
     * 小程序登录
     *
     * @author 剑鸣秋朔
     * @date 2023/3/19 0:34
     * @param appId     小程序 appId
     * @param appSecret 小程序秘钥
     * @param code      微信小程序临时的 code
     * @return com.github.itdachen.framework.weixin.applet.openid.Code2SessionInfo
     */
    public static AppletCode2SessionInfo code2Session(String appId, String appSecret, String code) throws Exception {
        String url = String.format(USER_OPEN_ID_API, appId, appSecret, code);
        JSONObject json = createGet(url);

        if (httpSuccess(json)) {
            return json.toJavaObject(AppletCode2SessionInfo.class);
        }

//        if (json.get(ReturnStatusCode.ERR_CODE_FILED).equals(ReturnStatusCode.OK)) {
//            return json.toJavaObject(AppletCode2SessionInfo.class);
//        }

        String msg = getErrorCodeValue(json);
        if (null == msg) {
            msg = errorCodeValue(getErrorCode(json));
        }
        logger.error(errorLog(API_TITLE, json));
        throw new Exception(msg);
    }

    /***
     * 获取用户 openId
     *
     * @author 剑鸣秋朔
     * @date 2023/3/19 0:43
     * @param appId     小程序 appId
     * @param appSecret 小程序秘钥
     * @param code      微信小程序临时的 code
     * @return java.lang.String
     */
    public static String getUserOpenId(String appId, String appSecret, String code) throws Exception {
        String url = String.format(USER_OPEN_ID_API, appId, appSecret, code);
        return String.valueOf(createGet(url, "openid", API_TITLE));
    }

    /***
     * 错误码
     *
     * @author 剑鸣秋朔
     * @date 2023/3/19 0:33
     * @param codeValue codeValue
     * @return java.lang.String
     */
    private static String errorCodeValue(Integer codeValue) {
        switch (codeValue) {
            case 40029 -> {
                return "无效的 oauth_code";
            }
            case 45011 -> {
                return "API 调用太频繁，请稍候再试";
            }
            case 40226 -> {
                return "高风险等级用户，小程序登录拦截 。";
            }
            default -> {
                return ReturnStatusCode.DEFAULT_MSG;
            }
        }
    }
}

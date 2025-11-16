package com.github.itdachen.framework.boot.wechat.applet.api;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.framework.boot.wechat.applet.model.JsCode2Session;

/**
 * 获取小程序 openid
 *
 * @author 剑鸣秋朔
 * @date 2024/12/1 0:29
 */
public class JsCode2SessionApi {
    private static final String USER_OPEN_ID_API = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";


    public static JsCode2Session jsCode2Session(String appId, String appSecret, String jscode) {
        String url = String.format(USER_OPEN_ID_API, appId, appSecret, jscode);
        String body = HttpRequest.get(url).execute().body();

        JSONObject json = JSONObject.parseObject(body);
        return json.toJavaObject(JsCode2Session.class);


//        Map<String, String> map = new HashMap<>();
//        map.put("openid", json.getString("openid"));
//        map.put("session_key", json.getString("session_key"));
//        return map;
    }


//    public static void main(String[] args) {
//        String appID = "wxda3ce83a8d38bca8";
//        String appSecret = "46548fd93a76524dc17b01c32ab4e455";
//        String jscode = "0a1GeWkl24Vqve41tpml2InSSg2GeWkU";
//
//
//        jsCode2Session(appID, appSecret, jscode);
//
//
//    }


}

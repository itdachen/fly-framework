package com.github.itdachen.boot.wechat.applet;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取小程序 openid
 *
 * @author 王大宸
 * @date 2024/12/1 0:29
 */
public class JsCode2SessionUtils {


    public static Map<String, String> jsCode2Session(String appID, String appSecret, String jscode) {
        String httpURI = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appID + "&secret=" + appSecret + "&js_code=" + jscode + "&grant_type=authorization_code";

        String body = HttpRequest.get(httpURI).execute().body();
        JSONObject json = JSONObject.parseObject(body);

        Map<String, String> map = new HashMap<>();
        map.put("openid", json.getString("openid"));
        map.put("session_key", json.getString("session_key"));
        return map;
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

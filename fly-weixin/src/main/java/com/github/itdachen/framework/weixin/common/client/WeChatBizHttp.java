package com.github.itdachen.framework.weixin.common.client;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.framework.weixin.common.outcome.ReturnStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 微信请求基础
 * Created by 王大宸 on 2023/03/18 22:50
 * Created with IntelliJ IDEA.
 */
public class WeChatBizHttp {
    private static final Logger logger = LoggerFactory.getLogger(WeChatBizHttp.class);

    /***
     * http 请求默认请求头
     *
     * @author 王大宸
     * @date 2023/2/5 1:00
     * @return java.util.Map<java.lang.String, java.lang.String>
     */
    public static Map<String, String> requestHeader() {
        Map<String, String> heads = new HashMap<>();
        heads.put("Content-Type", "application/json;charset=UTF-8");
        return heads;
    }


    public static JSONObject createPost(String uri, String body) {
        return JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(requestHeader(), false)
                .body(body)
                .execute()
                .body()
        );
    }

    public static JSONObject createPost(String uri, String body, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(requestHeader(), false)
                .body(body)
                .execute()
                .body()
        );
        return getContainsKey(json, containsKey, title);
    }

    public static JSONObject createPost(String uri, JSONObject body) {
        return JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(requestHeader(), false)
                .body(String.valueOf(body))
                .execute()
                .body()
        );
    }

    public static JSONObject createPost(String uri, JSONObject body, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(requestHeader(), false)
                .body(String.valueOf(body))
                .execute()
                .body()
        );
        return getContainsKey(json, containsKey, title);
    }

    public static JSONObject createPost(String uri, Map<String, String> body) {
        return JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(WeChatBizHttp.requestHeader(), false)
                .body(String.valueOf(body))
                .execute()
                .body()
        );
    }

    public static JSONObject createPost(String uri, Map<String, String> body, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(WeChatBizHttp.requestHeader(), false)
                .body(String.valueOf(body))
                .execute()
                .body()
        );
        return getContainsKey(json, containsKey, title);
    }


    public static JSONObject createPost(String uri) {
        return JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(requestHeader(), false)
                .execute().body()
        );
    }

    public static JSONObject createPost(String uri, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri)
                .headerMap(requestHeader(), false)
                .execute().body()
        );
        return getContainsKey(json, containsKey, title);
    }

    public static JSONObject createGet(String uri) {
        return JSONObject.parseObject(HttpUtil.createGet(uri).execute().body());
    }

    public static JSONObject createGet(String uri, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createGet(uri).execute().body());
        return getContainsKey(json, containsKey, title);
    }

    public static JSONObject getContainsKey(JSONObject json, String containsKey, String title) throws Exception {
        if (json.containsKey(containsKey)) {
            return json.getJSONObject(containsKey);
        }
        logger.error(title + "失败, 错误码: {}, 错误消息:{}", json.getString(ReturnStatusCode.ERR_CODE_FILED), json.getString(ReturnStatusCode.ERR_MSG_FILED));
        throw new Exception(title + "失败, 错误码: " + json.getString(ReturnStatusCode.ERR_CODE_FILED) + ", 错误消息: " + json.getString(ReturnStatusCode.ERR_MSG_FILED));
    }

    public static JSONObject getContainsKey(JSONObject json, String containsKey) throws Exception {
        return getContainsKey(json, containsKey, "接口调用");
    }

    /***
     * 异常提醒
     *
     * @author 王大宸
     * @date 2023/2/5 1:20
     * @param body body
     * @return void
     */
    public static void ex(String title, JSONObject body) throws Exception {
        logger.error(title + "失败, 错误码: {}, 错误消息:{}", body.getString(ReturnStatusCode.ERR_CODE_FILED), body.getString(ReturnStatusCode.ERR_MSG_FILED));
        throw new Exception(title + "失败, 错误码: " + body.getString(ReturnStatusCode.ERR_CODE_FILED) + ", 错误消息: " + body.getString(ReturnStatusCode.ERR_MSG_FILED));
    }

    public static void ex(Logger log, String title, JSONObject body) throws Exception {
        log.error(title + "失败, 错误码: {}, 错误消息:{}", body.getString(ReturnStatusCode.ERR_CODE_FILED), body.getString(ReturnStatusCode.ERR_MSG_FILED));
        throw new Exception(title + "失败, 错误码: " + body.getString(ReturnStatusCode.ERR_CODE_FILED) + ", 错误消息: " + body.getString(ReturnStatusCode.ERR_MSG_FILED));
    }

    public static void ex(String title, String errcode, String msg) throws Exception {
        logger.error(title + "失败, 错误码: {}, 错误消息:{}", errcode, msg);
        throw new Exception(title + "失败, 错误码: " + errcode + ", 错误消息: " + msg);
    }

    public static void ex(Logger log, String title, String errcode, String msg) throws Exception {
        log.error(title + "失败, 错误码: {}, 错误消息:{}", errcode, msg);
        throw new Exception(title + "失败, 错误码: " + errcode + ", 错误消息: " + msg);
    }


}

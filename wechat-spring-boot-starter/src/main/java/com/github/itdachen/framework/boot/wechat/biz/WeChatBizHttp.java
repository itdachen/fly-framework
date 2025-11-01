package com.github.itdachen.framework.boot.wechat.biz;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.framework.context.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信请求基础
 *
 * @author 王大宸
 * @date 2024/12/1 15:32
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
        return JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).body(body).execute().body());
    }

    public static JSONObject createPost(String uri, String body, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).body(body).execute().body());
        return getContainsKey(json, containsKey, title);
    }

    public static JSONObject createPost(String uri, JSONObject body) {
        return JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).body(String.valueOf(body)).execute().body());
    }

    public static JSONObject createPost(String uri, JSONObject body, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).body(String.valueOf(body)).execute().body());
        return getContainsKey(json, containsKey, title);
    }

    public static JSONObject createPost(String uri, Map<String, String> body) {
        return JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).body(String.valueOf(body)).execute().body());
    }

    public static JSONObject createPost(String uri, Map<String, String> body, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).body(String.valueOf(body)).execute().body());
        return getContainsKey(json, containsKey, title);
    }


    public static JSONObject createPost(String uri) {
        return JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).execute().body());
    }

    public static JSONObject createPost(String uri, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createPost(uri).headerMap(requestHeader(), false).execute().body());
        return getContainsKey(json, containsKey, title);
    }

    protected static JSONObject createGet(String uri) {
        return JSONObject.parseObject(HttpUtil.createGet(uri).execute().body());
    }

    /***
     * GET 请求, 获取指定 Key 的数据
     *
     * @author 王大宸
     * @date 2023/3/19 0:42
     * @param uri uri
     * @param containsKey containsKey
     * @param title title
     * @return com.alibaba.fastjson.JSONObject
     */
    protected static JSONObject createGet(String uri, String containsKey, String title) throws Exception {
        JSONObject json = JSONObject.parseObject(HttpUtil.createGet(uri).execute().body());
        return getContainsKey(json, containsKey, title);
    }

    /***
     * 获取 json 中指定 key 的数据
     *
     * @author 王大宸
     * @date 2023/3/19 0:39
     * @param json  alibaba JSONObject
     * @param containsKey 指定的 Key
     * @param title 接口标题
     * @return com.alibaba.fastjson.JSONObject
     */
    public static JSONObject getContainsKey(JSONObject json, String containsKey, String title) throws Exception {
        if (json.containsKey(containsKey)) {
            return json.getJSONObject(containsKey);
        }
        logger.error(errorLog(title, json));
        throw new BizException(title + "失败, 错误码: " + json.getString(WeChatHttpStatus.ERR_CODE_FILED) + ", 错误消息: " + json.getString(WeChatHttpStatus.ERR_MSG_FILED));
    }

    /***
     * 获取 json 中指定 key 的数据
     *
     * @author 王大宸
     * @date 2023/3/19 0:39
     * @param json  alibaba JSONObject
     * @param containsKey 指定的 Key
     * @return com.alibaba.fastjson.JSONObject
     */
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
        logger.error(errorLog(title, body));
        throw new BizException(title + "失败, 错误码: " + body.getString(WeChatHttpStatus.ERR_CODE_FILED) + ", 错误消息: " + body.getString(WeChatHttpStatus.ERR_MSG_FILED));
    }


    public static Integer getErrorCode(JSONObject obj) {
        return Integer.parseInt(String.valueOf(obj.get(WeChatHttpStatus.ERR_CODE_FILED)));
    }

    public static String errorLog(String title, JSONObject json) {
        return title + "失败, 错误码: " + json.getString(WeChatHttpStatus.ERR_CODE_FILED) + ", 错误消息: " + json.getString(WeChatHttpStatus.ERR_MSG_FILED);
    }

    public static String errorLog(JSONObject json) {
        return "错误码: " + json.getString(WeChatHttpStatus.ERR_CODE_FILED) + ", 错误消息: " + json.getString(WeChatHttpStatus.ERR_MSG_FILED);
    }


    public static String getErrorCodeValue(JSONObject json) {
        return WeChatHttpStatus.getMsg(getErrorCode(json));
    }

    protected static String publicErrMsg(Integer errorCode) {
        return WeChatHttpStatus.getMsg(errorCode);
    }

    /***
     * 是否请求成功
     *
     * @author 王大宸
     * @date 2023/5/29 10:09
     * @param json json
     * @return java.lang.Boolean
     */
    public static Boolean httpSuccess(JSONObject json) {
        return WeChatHttpStatus.OK.equals(json.get(WeChatHttpStatus.ERR_CODE_FILED));
    }

    public static Boolean httpSuccess(Integer status) {
        return WeChatHttpStatus.OK.equals(status);
    }


}

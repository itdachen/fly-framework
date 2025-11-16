package com.github.itdachen.framework.boot.oplog.ooxml.poi.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * 响应信息
 *
 * @author 剑鸣秋朔
 * @date 2025/7/25 22:49
 */
public class ReplyResponseMsgUtils {


    /***
     * 执行导出, 返回给前端消息
     *
     * @author 剑鸣秋朔
     * @date 2025/7/19 2:21
     * @return com.github.itdachen.ooxml.poi.OOXmlPoiExpHelper<T, Q>
     */
    public static void reply(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(okMsg(msg));
    }

    /***
     * 成功消息
     */
    private static String okMsg(String msg) {
        return "{\"success\":\"true\",\"status\":\"" + 200 + "\", \"msg\":\"" + msg + "\",\"data\": null}";
    }


}

package com.github.itdachen.framework.tools.request;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 获取请求体
 * Created by 剑鸣秋朔 on 2023-07-01 13:29
 * Created with IntelliJ IDEA.
 */
public class BodyUtils {

    public static Map<String, String> requestBody(HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
            }
        }
        return res;
    }


}

package com.github.itdachen.framework.tools.request;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Description: 根据请求获取操作系统
 * Created by 剑鸣秋朔 on 2023-07-01 13:24
 * Created with IntelliJ IDEA.
 */
public class OSUtils {

    public static String osInfo(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String os = "";
        if (browserDetails.toLowerCase().contains("windows")) {
            os = "Windows";
        } else if (browserDetails.toLowerCase().contains("mac")) {
            os = "Mac";
        } else if (browserDetails.toLowerCase().contains("x11")) {
            os = "Unix";
        } else if (browserDetails.toLowerCase().contains("android")) {
            os = "Android";
        } else if (browserDetails.toLowerCase().contains("iphone")) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + browserDetails;
        }
        return os;
    }


}

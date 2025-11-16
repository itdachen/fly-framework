package com.github.itdachen.framework.context.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * AjaxUtils
 *
 * @author 剑鸣秋朔
 * @date 2024-05-23 17:11
 */
public class AjaxUtils {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xRequestedWith = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(xRequestedWith) || "axios".equals(xRequestedWith);
    }

}

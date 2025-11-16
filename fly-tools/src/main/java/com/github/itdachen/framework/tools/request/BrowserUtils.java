package com.github.itdachen.framework.tools.request;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Description: 根据请求获取浏览器名称和版本号
 * Created by 剑鸣秋朔 on 2023-07-01 13:27
 * Created with IntelliJ IDEA.
 */
public class BrowserUtils {

    public static String browserInfo(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String user = browserDetails.toLowerCase();

        String browser = null;
        if (user.contains("edge")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Edge")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("msie")) {
            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0]
                    + "-" + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0]
                        + "-" + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                        .replace("OPR", "Opera");
            }
        } else if (user.contains("chrome")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) ||
                (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) ||
                (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            browser = "Netscape-?";
        } else if (user.contains("firefox")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            String IEVersion = (browserDetails.substring(browserDetails.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
            browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
        } else {
            browser = "UnKnown, More-Info: " + browserDetails;
        }
        return browser;

    }

}

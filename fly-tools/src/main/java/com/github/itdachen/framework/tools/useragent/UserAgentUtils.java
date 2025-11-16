package com.github.itdachen.framework.tools.useragent;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 * Description: 获取客户端信息
 * Created by 剑鸣秋朔 on 2023-08-17 21:54
 * Created with IntelliJ IDEA.
 */
public class UserAgentUtils {

    public static UserAgent getUserAgent(String userAgent) {
        return UserAgent.parseUserAgentString(userAgent);
    }

    /***
     * 获取浏览器对象
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:01
     * @param agent agent
     * @return eu.bitwalker.useragentutils.Browser
     */
    public static Browser getBrowser(String agent) {
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        return userAgent.getBrowser();
    }

    /***
     * 获取浏览器对象
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:01
     * @param userAgent userAgent
     * @return eu.bitwalker.useragentutils.Browser
     */
    public static Browser getBrowser(UserAgent userAgent) {
        return userAgent.getBrowser();
    }

    /***
     * 获取浏览器名称
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:04
     * @param agent agent
     * @return java.lang.String
     */
    public static String getBrowserName(String agent) {
        return getBrowser(agent).getGroup().getName();
    }

    /***
     * 获取浏览器名称
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:04
     * @param userAgent userAgent
     * @return java.lang.String
     */
    public static String getBrowserName(UserAgent userAgent) {
        return getBrowser(userAgent).getGroup().getName();
    }

    /***
     * 获取操作系统
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:02
     * @param agent agent
     * @return eu.bitwalker.useragentutils.OperatingSystem
     */
    public static OperatingSystem getOperationSystem(String agent) {
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        return userAgent.getOperatingSystem();
    }

    /***
     * 获取操作系统
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:02
     * @param userAgent userAgent
     * @return eu.bitwalker.useragentutils.OperatingSystem
     */
    public static OperatingSystem getOperationSystem(UserAgent userAgent) {
        return userAgent.getOperatingSystem();
    }

    /***
     * 获取设备类型
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:04
     * @param agent agent
     * @return java.lang.String
     */
    public static String getDeviceType(String agent) {
        return getOperationSystem(agent).getDeviceType().toString();
    }


    /***
     * 获取设备类型
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:04
     * @param userAgent userAgent
     * @return java.lang.String
     */
    public static String getDeviceType(UserAgent userAgent) {
        return getOperationSystem(userAgent).getDeviceType().toString();
    }

    /***
     *  获取os: windwos、IOS、Android
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:05
     * @param agent agent
     * @return java.lang.String
     */
    public static String getOSInfo(String agent) {
        return getOperationSystem(agent).getGroup().getName();
    }

    /***
     *  获取os: windwos、IOS、Android
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:05
     * @param userAgent userAgent
     * @return java.lang.String
     */
    public static String getOSInfo(UserAgent userAgent) {
        return getOperationSystem(userAgent).getGroup().getName();
    }


    /***
     * 获取设备厂家
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:05
     * @param agent agent
     * @return java.lang.String
     */
    public static String getDeviceManufacturer(String agent) {
        return getOperationSystem(agent).getManufacturer().toString();
    }

    /***
     * 获取设备厂家
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:06
     * @param userAgent userAgent
     * @return java.lang.String
     */
    public static String getDeviceManufacturer(UserAgent userAgent) {
        return getOperationSystem(userAgent).getManufacturer().toString();
    }

    /***
     * 操作系统版本
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 22:06
     * @param userAgent userAgent
     * @return Android 1.x、Intel Mac OS X 10.15
     */
    public static String getOSVersion(String userAgent) {
        String osVersion = "";
        if (StringUtils.isBlank(userAgent)) {
            return osVersion;
        }
        if (userAgent.indexOf("(") >= 0 && userAgent.indexOf(")") >= 0) {
            String[] strArr = userAgent.substring(userAgent.indexOf("(") + 1,
                    userAgent.indexOf(")")).split(";");
            if (null == strArr || strArr.length == 0) {
                return osVersion;
            }
            osVersion = strArr[1];
            return osVersion;
        }
        return "";

    }


    /**
     * 生成web设备唯一ID
     *
     * @param map
     * @return
     */
    public static String geneWebUniqueDeviceId(Map<String, String> map) {
        String deviceId = MD5(map.toString());
        return deviceId;
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;

    }


    /***
     * 获取设备对象
     *
     * @author 剑鸣秋朔
     * @date 2023/8/17 21:58
     * @param agent agent
     * @return com.github.itdachen.framework.tools.useragent.DeviceInfo
     */
    public static DeviceInfo getDeviceInfo(String agent) {

        UserAgent userAgent = getUserAgent(agent);
        /* 浏览器名称 */
        String browserName = getBrowserName(userAgent);
        /* 操作系统信息 */
        OperatingSystem operatingSystem = getOperationSystem(userAgent);
        /* 操作系统类型 */
        String os = operatingSystem.getGroup().getName();
        /* 生产厂商 */
        String manufacture = operatingSystem.getManufacturer().toString();
        /* 设备类型 */
        String deviceType = operatingSystem.getDeviceType().toString();

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceType(deviceType);
        deviceInfo.setDeviceManufacturer(manufacture);
        deviceInfo.setBrowserName(browserName);
        deviceInfo.setOs(os);
        deviceInfo.setOsVersion(getOSVersion(agent));

        return deviceInfo;
    }

    public static void main(String[] args) {

        String userAgent = "Mozilla/5.0 (Linux; Android 10; LIO-AN00 Build/HUAWEILIO-AN00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/77.0.3865.120 MQQBrowser/6.2 TBS/045713 Mobile Safari/537.36 MMWEBID/3189 MicroMessenger/8.0.11.1980(0x28000B51) Process/tools WeChat/arm64 Weixin NetType/WIFI Language/zh_CN ABI/arm64";

        Map<String, String> map = new TreeMap<>();
        map.put("ip", "");
        map.put("bizId", "12310110");
        map.put("userAgent", userAgent);
        String deviceId = geneWebUniqueDeviceId(map);

        DeviceInfo deviceInfo = getDeviceInfo(userAgent);
        deviceInfo.setUuid(deviceId);
        System.err.println(deviceInfo.toString());
    }


}

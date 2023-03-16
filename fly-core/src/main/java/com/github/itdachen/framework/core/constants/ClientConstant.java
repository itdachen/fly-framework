package com.github.itdachen.framework.core.constants;

/**
 * Description: 客户端类型常量
 * Created by 王大宸 on 2021-11-16 22:20
 * Created with IntelliJ IDEA.
 */
public class ClientConstant {


    /**
     * PC 端操作
     */
    public final static String CLIENT_WEB = "PC";
    public final static String CLIENT_WEB_MSG = "电脑浏览器";

    /**
     * 小程序端操作
     */
    public final static String CLIENT_WE_CHAT = "WE_CHAT";
    public final static String CLIENT_WE_CHAT_MSG = "微信小程序";

    /**
     * APP 操作
     */
    public final static String CLIENT_APP = "MOBILE_APP";
    public final static String CLIENT_APP_MSG = "手机APP";

    /**
     * 授权第三方操作
     */
    public final static String CLIENT_AUTHORIZATION_CODE = "AUTH_CODE";
    public final static String CLIENT_AUTHORIZATION_CODE_MSG = "授权码模式";

    /**
     * 其他客户端操作
     */
    public final static String CLIENT_OTHER = "OTHER_CLIENT";
    public final static String CLIENT_OTHER_MSG = "其他客户端";

    /***
     * 根据传入的客户端id,查询操作客户端名称
     *
     * @author 王大宸
     * @date 2021/11/16 22:22
     * @param value 客户端id
     * @return java.lang.String
     */
    public static String getClientName(String value) {
        switch (value) {
            case CLIENT_WEB:
                return CLIENT_WEB_MSG;
            case CLIENT_WE_CHAT:
                return CLIENT_WE_CHAT_MSG;
            case CLIENT_APP:
                return CLIENT_APP_MSG;
            case CLIENT_AUTHORIZATION_CODE:
                return CLIENT_AUTHORIZATION_CODE_MSG;
            case CLIENT_OTHER:
                return CLIENT_OTHER_MSG;
            default:
                return "未知客户端";
        }
    }

}

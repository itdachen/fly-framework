package com.github.itdachen.framework.boot.security.constants;

/**
 * Description:
 * Created by 王大宸 on 2023/04/04 16:10
 * Created with IntelliJ IDEA.
 */
public class ServerForwardKey {
    /**
     * 第三方授权信息
     * 税务机关代码
     * 登录账号代码
     * 授权码
     */
    public static final String AUTHORIZATION_INFORMATION = "ticket_code=%S";

    /**
     * 未找到平台信息
     */
    public static final String ERROR_URI = "/error/404501";

    /**
     * 必带的参数
     */
    public static final String URI_KEY = "uri";

    /**
     * 存储失效 300 秒, 五分钟
     */
    public static final Integer REDIS_OUT_TIME = 300;

    public static final String PRE_AUTH_CODE_KEY = "pre_auth_code:";

    public static final String TICKET_URL = "ticket_uri";

    public static final String TICKET_CODE = "ticket_code";

}

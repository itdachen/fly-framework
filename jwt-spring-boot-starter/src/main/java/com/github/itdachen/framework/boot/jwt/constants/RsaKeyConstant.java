package com.github.itdachen.framework.boot.jwt.constants;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2022-06-29 10:40
 * Created with IntelliJ IDEA.
 */
public class RsaKeyConstant {

    public static final String PRI_KEY = "pri";

    public static final String PUB_KEY = "pub";

    public static final String REDIS_USER_PRI_KEY = "FLY:AUTH:JWT:PRI";
    public static final String REDIS_USER_PUB_KEY = "FLY:AUTH:JWT:PUB";

    public static final String REDIS_SERVICE_PRI_KEY = "FLY:AUTH:CLIENT:PRI";
    public static final String REDIS_SERVICE_PUB_KEY = "FLY:AUTH:CLIENT:PUB";

    /* 用户 token 存 Redis 中 key 前缀 */
    public static final String REDIS_SERVICE_USER_KEY = "FLY:AUTH:TOKEN:";

}

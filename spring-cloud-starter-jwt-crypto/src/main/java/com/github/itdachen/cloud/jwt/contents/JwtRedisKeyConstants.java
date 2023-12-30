package com.github.itdachen.cloud.jwt.contents;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:07
 * Created with IntelliJ IDEA.
 */
public class JwtRedisKeyConstants {


    /**
     * token 私钥存储 key
     */
    public static final String USER_PRI_KEY = "FLY:AUTH:JWT:PRI";

    /**
     * token 公钥存储 key
     */
    public static final String USER_PUB_KEY = "FLY:AUTH:JWT:PUB";

    /**
     * token 算法存储 key
     */
    public static final String USER_PRI_KEY_ALG = "FLY:AUTH:JWT:ALG";

    public static final String TOKEN_STORE_PREFIX = "FLY:OAUTH:";


    public static final String SERVICE_PRI_KEY = "FLY:AUTH:CLIENT:PRI";

    public static final String SERVICE_PUB_KEY = "FLY:AUTH:CLIENT:PUB";

}

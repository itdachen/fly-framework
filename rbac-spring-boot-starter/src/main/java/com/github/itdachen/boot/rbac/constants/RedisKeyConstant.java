package com.github.itdachen.boot.rbac.constants;

/**
 * Description: sys_element
 * Created by 王大宸 on 2022-04-02 10:12
 * Created with IntelliJ IDEA.
 */
public class RedisKeyConstant {

    public static final String REDIS_KEY_USER_PERMISSIONS = "PERMISSIONS:USER:POWER:%s";

    public static final String REDIS_KEY_ALL_PERMISSIONS = "PERMISSIONS:ALL";

    public static final String USER_PERMISSIONS_ELEMENT = "PERMISSIONS:USER:ELEMENT:";

    public static final String USER_PERMISSIONS_MENU = "PERMISSIONS:USER:MENU:";

    public static final String REDIS_KEY_CAPTCHA = "CAPTCHA:%s";

    public static final String REDIS_KEY_TOKEN = "TOKEN";

    /**
     * 系统权限缓存有效期
     */
    public static final int SYS_PERMISSIONS_TIME_OUT = 12;

    /**
     * 个人权限缓存有效期
     */
    public static final int USER_PERMISSIONS_TIME_OUT = 20;

}

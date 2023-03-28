package com.github.itdachen.framework.datasource.constants;

/**
 * Description:
 * Created by 王大宸 on 2023/03/28 16:39
 * Created with IntelliJ IDEA.
 */
public class DataSourceKeyConstant {


    /**
     * 密码秘钥
     */
    public static final String SECRET_KEY = "#!SAGA2020*&@";

    /* {bcrypt} */
    private static final String DEFAULT_ID_PREFIX = "{";

    private static final String DEFAULT_ID_SUFFIX = "}";

    /**
     * AES 加密
     */
    public static final String AES = "{aes}";

    /**
     * 不加密
     */
    public static final String NOOP = "{noop}";

}

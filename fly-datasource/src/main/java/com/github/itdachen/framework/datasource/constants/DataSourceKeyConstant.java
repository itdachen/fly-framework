package com.github.itdachen.framework.datasource.constants;

import com.github.itdachen.framework.datasource.enums.EncoderTypeKeyEnum;

/**
 * Description:
 * Created by 王大宸 on 2023/03/28 16:39
 * Created with IntelliJ IDEA.
 */
public class DataSourceKeyConstant {

    /* {bcrypt} */
    public static final String PREFIX = "{";

    public static final String SUFFIX = "}";

    /**
     * AES 加密
     */
    public static final String AES = EncoderTypeKeyEnum.AES.toString();

    /**
     * 不加密
     */
    public static final String NOOP = EncoderTypeKeyEnum.NOOP.toString();

}

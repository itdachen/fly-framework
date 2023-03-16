package com.github.itdachen.framework.core;

/**
 * Description:
 * Created by 王大宸 on 2023/02/12 22:05
 * Created with IntelliJ IDEA.
 */
public class StringHelper {

    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

}

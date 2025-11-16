package com.github.itdachen.framework.boot.oplog.utils;

import java.util.Collection;

/**
 * 判断是否集合
 *
 * @author 剑鸣秋朔
 * @date 2024-08-26 15:54
 */
public class ResCollectionUtils {

    public static boolean isArray(Object obj) {
        return obj instanceof Collection || obj.getClass().isArray();
    }

}

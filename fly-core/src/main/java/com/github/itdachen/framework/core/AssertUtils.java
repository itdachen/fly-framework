package com.github.itdachen.framework.core;

import com.github.itdachen.framework.context.exception.BizException;

/**
 * 断言工具类
 *
 * @author 王大宸
 * @date 2025-04-17 15:57
 */
public class AssertUtils {


    /***
     * 判断字符串非空
     *
     * @author 王大宸
     * @date 2025/4/17 16:01
     * @param str str
     * @param message message
     * @return void
     */
    public static void isNotEmpty(String str, String... message) {
        if (isBlank(str)) {
            execute(message);
        }
    }

    /***
     * 判断对象非空
     *
     * @author 王大宸
     * @date 2025/4/17 15:59
     * @param obj obj
     * @param message message
     * @return void
     */
    public static void isNotNull(Object obj, String... message) {
        if (obj == null) {
            execute(message);
        }
    }

    /***
     * 判断结果是否为真
     *
     * @author 王大宸
     * @date 2025/4/17 15:59
     * @param isTrue isTrue
     * @param message message
     * @return void
     */
    public static void isTrue(boolean isTrue, String... message) {
        if (isTrue) {
            execute(message);
        }
    }

    /***
     * 最终执行方法
     *
     * @author 王大宸
     * @date 2025/4/17 15:59
     * @param message message
     * @return void
     */
    private static void execute(String... message) {
        String msg = "Oops! Something was wrong.";
        if (message != null && message.length > 0) {
            msg = message[0];
        }
        throw new BizException(msg);
    }


    public static boolean isBlank(CharSequence str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; ++i) {
                if (!isBlankChar(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    private static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == 65279 || c == 8234;
    }


}

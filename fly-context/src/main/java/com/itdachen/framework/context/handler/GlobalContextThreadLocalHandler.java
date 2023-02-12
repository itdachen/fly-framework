package com.itdachen.framework.context.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 使用 ThreadLocal  存储每一个线程中的信息
 * Created by 王大宸 on 2022-06-28 15:34
 * Created with IntelliJ IDEA.
 */
public class GlobalContextThreadLocalHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    /***
     * 向线程中添加信息
     *
     * @author 王大宸
     * @date 2022/6/28 15:35
     * @param key    添加的 key
     * @param value  添加的值
     * @return void
     */
    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    /***
     * 获取线程中的信息
     *
     * @author 王大宸
     * @date 2022/6/28 15:35
     * @param key 需要获取信息的 key
     * @return java.lang.Object
     */
    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }


    /***
     * 返回线程信息
     *
     * @author 王大宸
     * @date 2022/6/28 15:35
     * @param value value
     * @return java.lang.String
     */
    public static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }


    /***
     * 清空线程信息
     *
     * @author 王大宸
     * @date 2022/6/28 15:35
     */
    public static void remove() {
        threadLocal.remove();
    }

}

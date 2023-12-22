package com.github.itdachen.boot.oss.utils;

/**
 * Description: 映射路径处理
 * Created by 王大宸 on 2023/02/13 9:25
 * Created with IntelliJ IDEA.
 */
public class MapPathUtils {

    /***
     * 映射路径处理
     *
     * @author 王大宸
     * @date 2023/2/13 9:32
     * @param path path
     * @return java.lang.String
     */
    public static String mapPath(String path) {
        path = !path.startsWith("/") ? "/" + path : path;
        path = !path.endsWith("/") ? path + "/" : path;
        return path;
    }

    /***
     * 过滤路径处理
     *
     * @author 王大宸
     * @date 2023/2/13 9:32
     * @param path path
     * @return java.lang.String
     */
    public static String filterPath(String path) {
        return mapPath(path) + "**";
    }

//    public static void main(String[] args) {
//        String path = "upload";
//        System.err.println(filterPath(path));
//    }


}

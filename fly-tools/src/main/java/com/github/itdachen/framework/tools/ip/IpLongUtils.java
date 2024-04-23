package com.github.itdachen.framework.tools.ip;

/**
 * Description:
 * Created by 王大宸 on 2022-12-05 9:42
 * Created with IntelliJ IDEA.
 */
public class IpLongUtils {

    /**
     * 把字符串IP转换成long
     *
     * @param ipStr 字符串IP
     * @return IP对应的long值
     */
    public static long ip2Long(String ipStr) {
        String[] ip = ipStr.split("\\.");
        return (Long.valueOf(ip[0]) << 24) + (Long.valueOf(ip[1]) << 16)
                + (Long.valueOf(ip[2]) << 8) + Long.valueOf(ip[3]);
    }

    /**
     * 把IP的long值转换成字符串
     *
     * @param ipLong IP的long值
     * @return long值对应的字符串
     */
    public static String long2Ip(long ipLong) {
        StringBuilder ip = new StringBuilder();
        ip.append(ipLong >>> 24).append(".");
        ip.append((ipLong >>> 16) & 0xFF).append(".");
        ip.append((ipLong >>> 8) & 0xFF).append(".");
        ip.append(ipLong & 0xFF);
        return ip.toString();
    }

    public static void main(String[] args) {
        String ip = "192.168.31.43";
        String ip2 = "192.168.255.43";
        String ip3 = "192.168.56.43";
        long l = ip2Long(ip);
        long l2 = ip2Long(ip2);
        long l3 = ip2Long(ip3);

        if (l < l3 && l3 < l2) {
            System.err.println("l < l3 && l3 < l2");
        }

        System.err.println("IP1 ==> Long : " + ip);
        System.err.println("IP2 ==> Long : " + l2);
        String s = long2Ip(l);
        String ss = long2Ip(l2);
        System.out.println(s);


            if (ip.equals(s)) {
                System.err.println("========> OK");
            }

    }


}

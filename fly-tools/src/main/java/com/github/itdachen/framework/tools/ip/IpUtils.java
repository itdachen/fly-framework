package com.github.itdachen.framework.tools.ip;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2022-12-05 9:18
 * Created with IntelliJ IDEA.
 */
public class IpUtils {

    public static void main(String[] args) {
        String ip = "192.168.100.1";
        String mask = "31";
        System.out.println(parseIpMaskRange(ip, mask).size());
    }

    public static List<String> parseIpMaskRange(String ip, String mask) {
        List<String> list = new ArrayList<String>();
        if ("32".equals(mask)) {
            list.add(ip);
        } else {
            String startIp = getBeginIpStr(ip, mask);
            String endIp = getEndIpStr(ip, mask);
            if (!"31".equals(mask)) {
                String subStart = startIp.split("\\.")[0] + "." + startIp.split("\\.")[1] + "." + startIp.split("\\.")[2] + ".";
                String subEnd = endIp.split("\\.")[0] + "." + endIp.split("\\.")[1] + "." + endIp.split("\\.")[2] + ".";
                startIp = subStart + (Integer.valueOf(startIp.split("\\.")[3]) + 1);
                endIp = subEnd + (Integer.valueOf(endIp.split("\\.")[3]) - 1);
            }
            list = parseIpRange(startIp, endIp);
        }
        return list;
    }

    public static List<String> parseIpRange(String ipfrom, String ipto) {
        List<String> ips = new ArrayList<String>();
        String[] ipfromd = ipfrom.split("\\.");
        String[] iptod = ipto.split("\\.");
        int[] int_ipf = new int[4];
        int[] int_ipt = new int[4];
        for (int i = 0; i < 4; i++) {
            int_ipf[i] = Integer.parseInt(ipfromd[i]);
            int_ipt[i] = Integer.parseInt(iptod[i]);
        }
        for (int A = int_ipf[0]; A <= int_ipt[0]; A++) {
            for (int B = (A == int_ipf[0] ? int_ipf[1] : 0); B <= (A == int_ipt[0] ? int_ipt[1]
                    : 255); B++) {
                for (int C = (B == int_ipf[1] ? int_ipf[2] : 0); C <= (B == int_ipt[1] ? int_ipt[2]
                        : 255); C++) {
                    for (int D = (C == int_ipf[2] ? int_ipf[3] : 0); D <= (C == int_ipt[2] ? int_ipt[3]
                            : 255); D++) {
                        ips.add(new String(A + "." + B + "." + C + "." + D));
                    }
                }
            }
        }
        return ips;
    }

    /**
     * 把long类型的Ip转为一般Ip类型：xx.xx.xx.xx
     *
     * @param ip
     * @return
     */
    public static String getIpFromLong(Long ip) {
        String s1 = String.valueOf((ip & 4278190080L) / 16777216L);
        String s2 = String.valueOf((ip & 16711680L) / 65536L);
        String s3 = String.valueOf((ip & 65280L) / 256L);
        String s4 = String.valueOf(ip & 255L);
        return s1 + "." + s2 + "." + s3 + "." + s4;
    }

    /**
     * 把xx.xx.xx.xx类型的转为long类型的
     *
     * @param ip
     * @return
     */
    public static Long getIpFromString(String ip) {
        Long ipLong = 0L;
        String ipTemp = ip;
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256 + Long.parseLong(ipTemp);
        return ipLong;
    }

    /**
     * 根据掩码位获取掩码
     *
     * @param maskBit 掩码位数，如"28"、"30"
     * @return
     */
    public static String getMaskByMaskBit(String maskBit) {
        return StringUtils.isEmpty(maskBit) ? "error, maskBit is null !"
                : maskBitMap().get(maskBit);
    }

    /**
     * 根据 ip/掩码位 计算IP段的起始IP 如 IP串 218.240.38.69/30
     *
     * @param ip      给定的IP，如218.240.38.69
     * @param maskBit 给定的掩码位，如30
     * @return 起始IP的字符串表示
     */
    public static String getBeginIpStr(String ip, String maskBit) {
        return getIpFromLong(getBeginIpLong(ip, maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的起始IP 如 IP串 218.240.38.69/30
     *
     * @param ip      给定的IP，如218.240.38.69
     * @param maskBit 给定的掩码位，如30
     * @return 起始IP的长整型表示
     */
    public static Long getBeginIpLong(String ip, String maskBit) {
        return getIpFromString(ip) & getIpFromString(getMaskByMaskBit(maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的终止IP 如 IP串 218.240.38.69/30
     *
     * @param ip      给定的IP，如218.240.38.69
     * @param maskBit 给定的掩码位，如30
     * @return 终止IP的字符串表示
     */
    public static String getEndIpStr(String ip, String maskBit) {
        return getIpFromLong(getEndIpLong(ip, maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的终止IP 如 IP串 218.240.38.69/30
     *
     * @param ip      给定的IP，如218.240.38.69
     * @param maskBit 给定的掩码位，如30
     * @return 终止IP的长整型表示
     */
    public static Long getEndIpLong(String ip, String maskBit) {
        return getBeginIpLong(ip, maskBit)
                + ~getIpFromString(getMaskByMaskBit(maskBit));
    }


    /**
     * 根据子网掩码转换为掩码位 如 255.255.255.252转换为掩码位 为 30
     *
     * @param netmarks
     * @return
     */
    public static int getNetMask(String netmarks) {
        StringBuffer sbf;
        String str;
        int inetmask = 0, count = 0;
        String[] ipList = netmarks.split("\\.");
        for (int n = 0; n < ipList.length; n++) {
            sbf = toBin(Integer.parseInt(ipList[n]));
            str = sbf.reverse().toString();
            count = 0;
            for (int i = 0; i < str.length(); i++) {
                i = str.indexOf('1', i);
                if (i == -1) {
                    break;
                }
                count++;
            }
            inetmask += count;
        }
        return inetmask;
    }

    /**
     * 计算子网大小
     *
     * @param maskBit 掩码位
     * @return
     */
    public static int getPoolMax(int maskBit) {
        if (maskBit <= 0 || maskBit >= 32) {
            return 0;
        }
        return (int) Math.pow(2, 32 - maskBit) - 2;
    }

    private static StringBuffer toBin(int x) {
        StringBuffer result = new StringBuffer();
        result.append(x % 2);
        x /= 2;
        while (x > 0) {
            result.append(x % 2);
            x /= 2;
        }
        return result;
    }

    /*
     * 存储着所有的掩码位及对应的掩码 key:掩码位 value:掩码（x.x.x.x）
     */
    private static Map<String, String> maskBitMap() {
        Map<String, String> maskBit = new HashMap<String, String>();
        maskBit.put("1", "128.0.0.0");
        maskBit.put("2", "192.0.0.0");
        maskBit.put("3", "224.0.0.0");
        maskBit.put("4", "240.0.0.0");
        maskBit.put("5", "248.0.0.0");
        maskBit.put("6", "252.0.0.0");
        maskBit.put("7", "254.0.0.0");
        maskBit.put("8", "255.0.0.0");
        maskBit.put("9", "255.128.0.0");
        maskBit.put("10", "255.192.0.0");
        maskBit.put("11", "255.224.0.0");
        maskBit.put("12", "255.240.0.0");
        maskBit.put("13", "255.248.0.0");
        maskBit.put("14", "255.252.0.0");
        maskBit.put("15", "255.254.0.0");
        maskBit.put("16", "255.255.0.0");
        maskBit.put("17", "255.255.128.0");
        maskBit.put("18", "255.255.192.0");
        maskBit.put("19", "255.255.224.0");
        maskBit.put("20", "255.255.240.0");
        maskBit.put("21", "255.255.248.0");
        maskBit.put("22", "255.255.252.0");
        maskBit.put("23", "255.255.254.0");
        maskBit.put("24", "255.255.255.0");
        maskBit.put("25", "255.255.255.128");
        maskBit.put("26", "255.255.255.192");
        maskBit.put("27", "255.255.255.224");
        maskBit.put("28", "255.255.255.240");
        maskBit.put("29", "255.255.255.248");
        maskBit.put("30", "255.255.255.252");
        maskBit.put("31", "255.255.255.254");
        maskBit.put("32", "255.255.255.255");
        return maskBit;
    }

    /**
     * 根据掩码位获取掩码
     *
     * @param masks
     * @return
     */
    public static String getMaskByMaskBit(int masks) {
        String ret = "";
        if (masks == 1)
            ret = "128.0.0.0";
        else if (masks == 2)
            ret = "192.0.0.0";
        else if (masks == 3)
            ret = "224.0.0.0";
        else if (masks == 4)
            ret = "240.0.0.0";
        else if (masks == 5)
            ret = "248.0.0.0";
        else if (masks == 6)
            ret = "252.0.0.0";
        else if (masks == 7)
            ret = "254.0.0.0";
        else if (masks == 8)
            ret = "255.0.0.0";
        else if (masks == 9)
            ret = "255.128.0.0";
        else if (masks == 10)
            ret = "255.192.0.0";
        else if (masks == 11)
            ret = "255.224.0.0";
        else if (masks == 12)
            ret = "255.240.0.0";
        else if (masks == 13)
            ret = "255.248.0.0";
        else if (masks == 14)
            ret = "255.252.0.0";
        else if (masks == 15)
            ret = "255.254.0.0";
        else if (masks == 16)
            ret = "255.255.0.0";
        else if (masks == 17)
            ret = "255.255.128.0";
        else if (masks == 18)
            ret = "255.255.192.0";
        else if (masks == 19)
            ret = "255.255.224.0";
        else if (masks == 20)
            ret = "255.255.240.0";
        else if (masks == 21)
            ret = "255.255.248.0";
        else if (masks == 22)
            ret = "255.255.252.0";
        else if (masks == 23)
            ret = "255.255.254.0";
        else if (masks == 24)
            ret = "255.255.255.0";
        else if (masks == 25)
            ret = "255.255.255.128";
        else if (masks == 26)
            ret = "255.255.255.192";
        else if (masks == 27)
            ret = "255.255.255.224";
        else if (masks == 28)
            ret = "255.255.255.240";
        else if (masks == 29)
            ret = "255.255.255.248";
        else if (masks == 30)
            ret = "255.255.255.252";
        else if (masks == 31)
            ret = "255.255.255.254";
        else if (masks == 32)
            ret = "255.255.255.255";
        return ret;
    }

    public static boolean internalIp(String ip) {
        byte[] addr = textToNumericFormatV4(ip);
        return internalIp(addr) || "127.0.0.1".equals(ip);
    }

    /**
     * 将IPv4地址转换成字节
     *
     * @param text IPv4地址
     * @return byte 字节
     */
    public static byte[] textToNumericFormatV4(String text) {
        if (StringUtils.isEmpty(text) || 0 == text.length()) {
            return null;
        }
        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L)) {
                        return null;
                    }
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L)) {
                        return null;
                    }
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return bytes;
    }

    private static boolean internalIp(byte[] addr) {
        if (null == addr || addr.length < 2) {
            return true;
        }
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }

    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "未知";
        }
    }


}

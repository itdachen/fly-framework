package com.github.itdachen.framework.tools;

import com.google.common.hash.Hashing;

/**
 * Description: 短链码生成工具类
 * Created by 王大宸 on 2023-10-18 14:29
 * Created with IntelliJ IDEA.
 */
public class ShortLinkUtils {
    /**
     * 62个字符
     */
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


//    public static void main(String[] args) {
//        String url = "https://itdachen.com/website/post/details/1415741983184142336";
//        String shortLinkCode = createShortLinkCode(url);
//        System.out.println(shortLinkCode);
//    }


    public static String createShortLinkCode(String url) {
        /* murmurhash 算法 */
        long murmurhash = murmurHash32(url);
        /* 10进制转62进制 */
        return encodeToBase62(murmurhash);
    }

    /**
     * murmurhash算法
     *
     * @param param
     * @return
     */
    public static long murmurHash32(String param) {
        return Hashing.murmur3_32_fixed().hashUnencodedChars(param).padToLong();
    }

    /**
     * 10进制转62进制
     *
     * @param num
     * @return
     */
    public static String encodeToBase62(long num) {
        // StringBuffer线程安全，StringBuilder线程不安全
        StringBuffer sb = new StringBuffer();
        do {
            int i = (int) (num % 62);
            sb.append(CHARS.charAt(i));
            num = num / 62;
        } while (num > 0);
        String value = sb.reverse().toString();
        return value;
    }

}

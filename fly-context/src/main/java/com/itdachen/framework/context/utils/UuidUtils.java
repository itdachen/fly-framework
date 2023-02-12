package com.itdachen.framework.context.utils;

import java.util.UUID;

/**
 * Description: 针对 uuid 做二次进化, 短的 uuid
 * Created by 王大宸 on 2023/02/12 22:54
 * Created with IntelliJ IDEA.
 */
public class UuidUtils {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String str;
        int x;
        for (int i = 0; i < 8; i++) {
            str = uuid.substring(i * 4, i * 4 + 4);
            x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


}

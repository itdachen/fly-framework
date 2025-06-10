package com.github.itdachen.framework.context.id;

import com.github.itdachen.framework.context.id.sharding.SnowflakeShardingKeyGeneratorUtils;
import com.github.itdachen.framework.context.id.utils.GuidUtils;
import com.github.itdachen.framework.context.id.utils.SnowflakeUtils;

import java.util.Random;

/**
 * Description: ID 生成工具, 对外调用
 * Created by 王大宸 on 2023/01/05 9:33
 * Created with IntelliJ IDEA.
 */
public class IdUtils {
    private static final SnowflakeShardingKeyGeneratorUtils shardingKeyGenerator = new SnowflakeShardingKeyGeneratorUtils();
    private static final SnowflakeUtils SNOWFLAKE_UTILS = new SnowflakeUtils(1, 1, 0L);
    private static final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /***
     * 获取随机长度的串
     *
     * @author 王大宸
     * @date 2025/6/10 16:42
     * @param length length
     * @return java.lang.String
     */
    public static String getStringNumRandom(int length) {
        //生成随机数字和字母,
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
            saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }


    /**
     * 雪花算法 id 生成
     */
    public static String getId() {
        return String.valueOf(SNOWFLAKE_UTILS.nextId());
    }

    /***
     * 获取 GUID
     *
     * @author 王大宸
     * @date 2023/12/26 22:40
     * @return java.lang.String
     */
    public static String guid() {
        return GuidUtils.nextGuid();
    }

    /***
     * 获取 GUID
     *
     * @author 王大宸
     * @date 2023/12/26 22:43
     * @param secure secure
     * @return java.lang.String
     */
    public static String guid(boolean secure) {
        return GuidUtils.nextGuid(secure);
    }


    public static String getShardingKey() {
        return shardingKeyGenerator.generateKey().toString();
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.err.println(nextGuid());
//        }
//    }

}

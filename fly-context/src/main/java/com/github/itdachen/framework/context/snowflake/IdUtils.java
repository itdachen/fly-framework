package com.github.itdachen.framework.context.snowflake;

import com.github.itdachen.framework.context.snowflake.sharding.SnowflakeShardingKeyGeneratorUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: 雪花算法, 对外统一调用
 * Created by 王大宸 on 2023/01/05 9:33
 * Created with IntelliJ IDEA.
 */
public class IdUtils {
    private static final SnowflakeShardingKeyGeneratorUtils shardingKeyGenerator = new SnowflakeShardingKeyGeneratorUtils();
    private static final Snowflake snowflake = new Snowflake(1, 1, 0L);

    /**
     * 雪花算法 id 生成
     */
    public static String getId() {
        return String.valueOf(snowflake.nextId());
    }

    public static String getShardingKey() {
        return shardingKeyGenerator.generateKey().toString();
    }

//    public static void main(String[] args) throws UnknownHostException {
//        System.err.println(Inet4Address.getLocalHost().getHostAddress());
//
//        for (int i = 0; i < 10; i++) {
//            System.err.println(getShardingKey());
//        }
//    }

}

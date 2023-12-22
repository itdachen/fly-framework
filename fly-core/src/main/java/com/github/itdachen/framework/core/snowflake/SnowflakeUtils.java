package com.github.itdachen.framework.core.snowflake;

/**
 * Description:
 * Created by 王大宸 on 2023/01/05 9:33
 * Created with IntelliJ IDEA.
 */
public class SnowflakeUtils {

    private static final Snowflake snowflake = new Snowflake(1, 1, 0L);

    /**
     * 雪花算法 id 生成
     */
    public static String getId() {
        return String.valueOf(snowflake.nextId());
    }

}

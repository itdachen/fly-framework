package com.github.itdachen.framework.boot.redis.key.builder;

import org.springframework.beans.factory.annotation.Value;

/**
 * RedisKeyBuilder
 *
 * @author 剑鸣秋朔
 * @date 2025-10-20 16:08
 */
public class RedisKeyBuilder {


    @Value("${spring.application.name:fly_redis}")
    private String applicationName;

    private static final String SPLIT_ITEM = ":";

    public String getSplitItem() {
        return SPLIT_ITEM;
    }

    public String getPrefix() {
        return applicationName + SPLIT_ITEM;
    }

}

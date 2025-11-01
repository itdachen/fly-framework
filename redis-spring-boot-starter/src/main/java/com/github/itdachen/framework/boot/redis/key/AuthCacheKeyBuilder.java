package com.github.itdachen.framework.boot.redis.key;

import com.github.itdachen.framework.boot.redis.key.builder.RedisKeyBuilder;
import com.github.itdachen.framework.boot.redis.key.builder.RedisKeyLoadMatch;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * AuthCacheKeyBuilder
 *
 * @author 王大宸
 * @date 2025-10-20 16:13
 */
@Configuration
@Conditional(RedisKeyLoadMatch.class)
public class AuthCacheKeyBuilder extends RedisKeyBuilder {

    private static final String AUTH_INFO_KEY = "auth";

    public String buildAuthKey(String key) {
        return super.getPrefix() + AUTH_INFO_KEY + super.getSplitItem() + key;
    }

}

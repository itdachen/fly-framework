package com.github.itdachen.framework.boot.redis.key;

import com.github.itdachen.framework.boot.redis.key.builder.RedisKeyBuilder;
import com.github.itdachen.framework.boot.redis.key.builder.RedisKeyLoadMatch;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 数据字典缓存 key
 *
 * @author 剑鸣秋朔
 * @date 2025/10/22 20:35
 */
@Configuration
@Conditional(RedisKeyLoadMatch.class)
public class DictCacheKeyBuilder extends RedisKeyBuilder {

    private static final String DICT_INFO_KEY = "dict";

    public String buildUserInfoKey(String userId) {
        return super.getPrefix() + DICT_INFO_KEY + super.getSplitItem() + userId;
    }

}

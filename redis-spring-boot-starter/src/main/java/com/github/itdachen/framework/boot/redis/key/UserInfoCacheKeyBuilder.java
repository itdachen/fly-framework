package com.github.itdachen.framework.boot.redis.key;

import com.github.itdachen.framework.boot.redis.key.builder.RedisKeyBuilder;
import com.github.itdachen.framework.boot.redis.key.builder.RedisKeyLoadMatch;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 用户 Key 案列
 *
 * @author 剑鸣秋朔
 * @date 2025-10-20 16:10
 */
@Configuration
@Conditional(RedisKeyLoadMatch.class)
public class UserInfoCacheKeyBuilder extends RedisKeyBuilder {

    private static final String USER_INFO_KEY = "user";

    public String buildUserInfoKey(String userId) {
        return super.getPrefix() + USER_INFO_KEY + super.getSplitItem() + userId;
    }


}

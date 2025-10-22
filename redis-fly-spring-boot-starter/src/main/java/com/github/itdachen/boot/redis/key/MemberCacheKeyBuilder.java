package com.github.itdachen.boot.redis.key;

import com.github.itdachen.boot.redis.key.builder.RedisKeyBuilder;
import com.github.itdachen.boot.redis.key.builder.RedisKeyLoadMatch;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * MemberCacheKeyBuilder
 *
 * @author 王大宸
 * @date 2025/10/22 20:38
 */
@Configuration
@Conditional(RedisKeyLoadMatch.class)
public class MemberCacheKeyBuilder extends RedisKeyBuilder {

    private static final String MEMBER_INFO_KEY = "member";

    public String buildUserInfoKey(String userId) {
        return super.getPrefix() + MEMBER_INFO_KEY + super.getSplitItem() + userId;
    }

}

package com.github.itdachen.framework.limiter.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * Description: 限流脚本
 * Created by 王大宸 on 2023-07-03 15:25
 * Created with IntelliJ IDEA.
 */
//@Configuration
//@EnableCaching
//public class RedisRateLimiterConfig {
//
//    @Bean
//    public DefaultRedisScript<Long> limitScript() {
//        // 泛型是返回值的类型
//        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
//        // 设置脚本
//        redisScript.setScriptText(limitScriptText());
//        // 设置返回值类型
//        redisScript.setResultType(Long.class);
//        return redisScript;
//    }
//
//    /**
//     * 限流脚本
//     */
//    private String limitScriptText() {
//        return "local key = KEYS[1]\n" +
//                "local count = tonumber(ARGV[1])\n" +
//                "local time = tonumber(ARGV[2])\n" +
//                "local current = redis.call('get', key);\n" +
//                "if current and tonumber(current) > count then\n" +
//                "    return tonumber(current);\n" +
//                "end\n" +
//                "current = redis.call('incr', key)\n" +
//                "if tonumber(current) == 1 then\n" +
//                "    redis.call('expire', key, time)\n" +
//                "end\n" +
//                "return tonumber(current);";
//    }
//
//}

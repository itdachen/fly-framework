package com.github.itdachen.framework.boot.redis.config;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * IGenericJackson2JsonRedisSerializer
 *
 * @author 剑鸣秋朔
 * @date 2025-10-20 16:03
 */
public class IGenericJackson2JsonRedisSerializer extends GenericJackson2JsonRedisSerializer {

    public IGenericJackson2JsonRedisSerializer() {
        super(MapperFactory.newInstance());
    }

    @Override
    public byte[] serialize(Object source) throws SerializationException {
        if (source != null && ((source instanceof String) || (source instanceof Character))) {
            return source.toString().getBytes();
        }
        return super.serialize(source);
    }

}

package net.wangjifeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: WJF
 * @date: 2020/5/24
 * @description: RedisConfig
 */

@Configuration
public class RedisConfig {

    /**
     * redis键值对的值的序列化方式：通用方式
     * @return RedisSerializer
     */
    private RedisSerializer redisValueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * redis键值对的健的序列化方式：所有的健都是字符串
     * @return RedisSerializer
     */
    private RedisSerializer redisKeySerializer() {
        return new StringRedisSerializer();
    }

    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(redisKeySerializer());
        redisTemplate.setValueSerializer(redisValueSerializer());
        return redisTemplate;
    }


}

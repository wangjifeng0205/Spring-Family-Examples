package net.wangjifeng.service.impl;

import net.wangjifeng.service.HashService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: HashServiceImpl
 */
@Service
public class HashServiceImpl implements HashService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addHashAll(String key, Map<String, Object> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    @Override
    public void addHash(String redisKey, String key, Object value) {
        redisTemplate.opsForHash().put(redisKey, key, value);
    }

    @Override
    public Object get(String redisKey, String key) {
        return redisTemplate.opsForHash().get(redisKey, key);
    }

    @Override
    public Object update(String redisKey, String key, Object value) {
        Object obj = this.get(redisKey, key);
        this.delete(redisKey,key);
        redisTemplate.opsForHash().put(redisKey, key, value);
        return obj;
    }

    @Override
    public void delete(String redisKey, String key) {
        redisTemplate.opsForHash().delete(redisKey, key);
    }

    @Override
    public void deleteAll(String redisKey) {
        redisTemplate.delete(redisKey);
    }
}

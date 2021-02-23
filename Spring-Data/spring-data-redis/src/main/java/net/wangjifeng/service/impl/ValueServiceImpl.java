package net.wangjifeng.service.impl;

import net.wangjifeng.service.ValueService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/5/27
 * @description: ValueServiceImpl
 */
@Service
public class ValueServiceImpl implements ValueService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addValue(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object update(String key, Object newValue) {
        return redisTemplate.opsForValue().getAndSet(key,newValue);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

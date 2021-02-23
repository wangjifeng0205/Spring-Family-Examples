package net.wangjifeng.service.impl;

import net.wangjifeng.service.SetService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: SetServiceImpl
 */
@Service
public class SetServiceImpl implements SetService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public void addAll(String key, Set<Object> set) {
        redisTemplate.opsForSet().add(key,set);
    }

    @Override
    public void add(String key, Object value) {
        redisTemplate.opsForSet().add(key,value);
    }

    @Override
    public Set<Object> findAll(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void deleteValue(String key, Object value) {
        redisTemplate.opsForSet().remove(key,value);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

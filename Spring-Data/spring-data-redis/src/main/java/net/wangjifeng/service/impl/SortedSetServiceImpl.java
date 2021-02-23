package net.wangjifeng.service.impl;

import net.wangjifeng.service.SortedSetService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashSet;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: SortedSetServiceImpl
 */
@Service
public class SortedSetServiceImpl implements SortedSetService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void add(String key, String value, Double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    @Override
    public LinkedHashSet<Object> findAll(String key) {
        return (LinkedHashSet<Object>) redisTemplate.opsForZSet().range(key,0,-1);
    }

    @Override
    public Long count(String key, Double scoreFrom, Double scoreTo) {
        return redisTemplate.opsForZSet().count(key,scoreFrom,scoreTo);
    }

    @Override
    public LinkedHashSet<Object> findByScore(String key, Double scoreFrom, Double scoreTo) {
        return (LinkedHashSet<Object>) redisTemplate.opsForZSet().rangeByScore(key,scoreFrom,scoreTo);
    }

    @Override
    public Long rank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key,value);
    }

    @Override
    public void remove(String key, String value) {
        redisTemplate.opsForZSet().remove(key,value);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }


}

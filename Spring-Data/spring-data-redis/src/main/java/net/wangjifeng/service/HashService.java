package net.wangjifeng.service;

import java.util.Map;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: HashService
 */

public interface HashService {


    /**
     * 添加大量数据到redis
     * @param key: redis-key
     * @param value redis-value
     */
    public void addHashAll(String key, Map<String, Object> value);

    /**
     * 添加一个数据到redis的redisKey对应的某个key中
     * @param redisKey: redis-key
     * @param key: value-key
     * @param value: value
     */
    public void addHash(String redisKey, String key, Object value);

    /**
     * 根据key获取redis-value
     * @param redisKey: redis-key
     * @param key：value-key
     * @return Object:某个小key的值
     */
    public Object get(String redisKey, String key);


    /**
     * 更新数据
     * @param key: redis-key
     * @param value: redis-value
     * @return Object:更新前某个小key的value
     */
    public Object update(String redisKey, String key, Object value);


    /**
     * 删除key对应的value
     * @param redisKey: redis-key
     * @param key: value-key
     */
    public void delete(String redisKey, String key);


    /**
     * 删除redisKey对应的缓存
     * @param redisKey: redis-key
     */
    public void deleteAll(String redisKey);

}

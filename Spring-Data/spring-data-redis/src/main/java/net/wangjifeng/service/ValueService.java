package net.wangjifeng.service;

/**
 * @author: WJF
 * @date: 2020/5/27
 * @description: ValueService
 */

public interface ValueService {

    /**
     * 添加数据到redis
     * @param key: redis-key
     * @param value: redis-value
     */
    public void addValue(String key, Object value);


    /**
     * 根据key获取redis-value
     * @param key:redis-key
     * @return Object
     */
    public Object get(String key);


    /**
     * 更新数据
     * @param key：redis-key
     * @param newValue:更新后的value
     * @return Object:更新前的value
     */
    public Object update(String key, Object newValue);


    /**
     * 删除key对应的value
     * @param key: redis-key
     */
    public void delete(String key);

}

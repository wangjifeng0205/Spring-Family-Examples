package net.wangjifeng.service;

import java.util.Set;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: SetService
 */

public interface SetService {

    /**
     * 添加大量数据
     * @param key: redis-key
     * @param set；参数
     */
    public void addAll(String key, Set<Object> set);


    /**
     * 添加数据
     * @param key: redis-key
     * @param value redis-value
     */
    public void add(String key, Object value);


    /**
     * 查询全部
     * @param key: redis-key
     * @return Set<Object>
     */
    public Set<Object> findAll(String key);


    /**
     * 删除某个值
     * @param key: redis-key
     * @param value: redis-value
     */
    public void deleteValue(String key, Object value);

    /**
     * 删除所有
     * @param key：redis-key
     */
    public void delete(String key);

}

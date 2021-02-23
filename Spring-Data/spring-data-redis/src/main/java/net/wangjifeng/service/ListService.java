package net.wangjifeng.service;

import net.wangjifeng.enums.OpsType;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: ListService
 */

public interface ListService {

    /**
     * 添加大量数据
     * @param key: redis-key
     * @param list: redis-value
     * @param type: 方位
     */
    public void addList(String key, List<Object> list, OpsType type);

    /**
     * 添加单个数据
     * @param redisKey: redis-key
     * @param value: redis-value
     * @param type: 方位
     */
    public void add(String redisKey, Object value, OpsType type);

    /**
     * 获取数据
     * @param key
     * @return List<Object>
     */
    public List<Object> get(String key);


    /**
     * 更新数据
     * @param key: redis-key
     * @param value: redis-value
     * @param index: index
     * @return Object: 原来的值
     */
    public Object update(String key, Object value, Integer index);


    /**
     * 删除数据
     * @param key: redis-key
     */
    public void delete(String key);

    /**
     * 从左或者从右删除链表中的值
     * @param redisKey: redis-key
     * @param type: 方位
     */
    public void deleteValue(String redisKey, OpsType type);

}

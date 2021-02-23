package net.wangjifeng.service;

import java.util.LinkedHashSet;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: SortedSetService
 */

public interface SortedSetService {

    /**
     * 添加数据
     * @param key：redis-key
     * @param value: redis-value
     * @param score: 排序字段
     */
    public void add(String key, String value, Double score);

    /**
     * 查询所有数据
     * @param key：redis-key
     * @return LinkedHashSet<Object>
     */
    public LinkedHashSet<Object> findAll(String key);

    /**
     * 统计排序字段之前的数据个数
     * @param key：redis-key
     * @param scoreFrom: sort-start
     * @param scoreTo: sort_end
     * @return Long
     */
    public Long count(String key, Double scoreFrom, Double scoreTo);


    /**
     * 查询排序字段之的数据
     * @param key：redis-key
     * @param scoreFrom: sort-start
     * @param scoreTo: sort_end
     * @return LinkedHashSet<Object>
     */
    public LinkedHashSet<Object> findByScore(String key, Double scoreFrom, Double scoreTo);


    /**
     * 查询数据的score
     * @param key: redis-key
     * @param value：redis-value
     * @return Long: sort
     */
    public Long rank(String key, Object value);

    /**
     * 删除某个数据
     * @param key: redis-key
     * @param value: redis-value
     */
    public void remove(String key, String value);

    /**
     * 全部删除
     * @param key：redis-key
     */
    public void delete(String key);

}

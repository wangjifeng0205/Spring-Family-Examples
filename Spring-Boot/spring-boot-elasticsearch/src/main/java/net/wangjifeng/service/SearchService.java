package net.wangjifeng.service;

import net.wangjifeng.entity.Student;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/22
 * @description: SearchService
 */

public interface SearchService {

    /**
     * 同步搜索区间查询
     * List<Student>
     * @param from: 左区间，默认包含左边界
     * @param to ： 右区间，默认包含右边界
     * @param field : 字段名
     * @param index : 索引库名称
     * @return List<Student>
     */
    public List<Student> searchRange(Object from, Object to, String field, String index);


    /**
     * 同步搜索查询年龄在某区间的学生
     * @param from：左区间，默认包含左边界
     * @param to：右区间，默认包含右边界
     * @param index：索引
     * @return List<Student>
     */
    public List<Student> searchAgeRange(Integer from, Integer to, String index);


    /**
     * 组合查询
     * @return List<Student>
     */
    public List<Student> searchBool();


    /**
     * 组合查询 + 聚合查询
     */
    public void searchBoolAndAggregation();


    /**
     * 分词查询
     * @param match：分词词值
     * @return List<Student>
     */
    public List<Student> searchMatch(String match);

}

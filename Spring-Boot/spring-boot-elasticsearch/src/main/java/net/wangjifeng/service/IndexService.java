package net.wangjifeng.service;

import net.wangjifeng.entity.Student;

/**
 * @author: WJF
 * @date: 2020/5/22
 * @description: IndexService
 */

public interface IndexService {

    /**
     * 简单同步索引
     * @param student：学生实体
     * @return String
     */
    public String index(Student student);


    /**
     * 简单异步索引
     * @param student：学生实体
     * @return String
     */
    public String indexAsync(Student student);


}

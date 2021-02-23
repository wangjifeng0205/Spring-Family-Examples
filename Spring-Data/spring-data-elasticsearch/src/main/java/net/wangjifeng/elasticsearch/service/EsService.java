package net.wangjifeng.elasticsearch.service;

import net.wangjifeng.elasticsearch.entity.Student;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/9/29
 * @description: EsService
 */

public interface EsService {

    /**
     * 新增一条数据到索引库
     * @param student 学生
     */
    public void insert(Student student);

    /**
     * 新增多条条数据到索引库
     * @param list 学生列表
     */
    public void insertList(List<Student> list);

    /**
     * 根据id查询一个学生
     * @param id id
     * @return Student
     */
    public Student get(Long id);

    /**
     * 根据条件查询
     * @param student 条件
     * @return List<Student>
     */
    public SearchHits<Student> query(Student student);

}

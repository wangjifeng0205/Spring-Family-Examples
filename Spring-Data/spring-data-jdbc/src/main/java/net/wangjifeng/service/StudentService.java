package net.wangjifeng.service;

import net.wangjifeng.entity.Student;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/7/29
 * @description: StudentService
 */

public interface StudentService {

    /**
     * 查询所有学生
     * @return 学生列表
     */
    List<Student> findAll();

    /**
     * 根据id查询某个学生
     * @param id 学生id
     * @return 学生
     */
    public Student findById(Long id);

    /**
     * 根据学生姓名模糊查询学生列表
     * @param studentName 学生姓名
     * @return 学生列表
     */
    List<Student> findByStudentName(String studentName);

    /**
     * 新增学生
     * @param student 学生
     */
    void add(Student student);

    /**
     * 更新学生信息
     * @param student 学生
     */
    void update(Student student);

    /**
     * 根据主键删除学生信息
     * @param id 学生主键
     */
    void delete(Long id);

    /**
     * 学生总数
     * @return 学生总数
     */
    Integer count();

}

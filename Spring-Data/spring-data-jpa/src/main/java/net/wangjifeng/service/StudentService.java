package net.wangjifeng.service;

import net.wangjifeng.entity.Student;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/7/11
 * @description: StudentService
 */

public interface StudentService {

    /**
     * 查询学生列表
     * @return List<Student>
     */
    List<Student> findAll();

    /**
     * 根据学号查询学生信息
     * @param studentNo 学生学号
     * @return Student
     */
    Student findByStudentNo(String studentNo);

    /**
     * 根据学生姓名模糊查询学生列表
     * @param studentName 学生姓名
     * @return 学生列表
     */
    List<Student> findByStudentNameLike(String studentName);

    /**
     * 根据id查询学生信息
     * @param id 学生id
     * @return Student
     */
    Student findById(Long id);

    /**
     * 查询学生列表(带分页)
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @return List<Student>
     */
    List<Student> findAllPage(Integer pageNum, Integer pageSize);

    /**
     * 根据学生性别查询学生列表
     * @param sex 学生性别
     * @return List<Student>
     */
    List<Student> findBySex(String sex);

}

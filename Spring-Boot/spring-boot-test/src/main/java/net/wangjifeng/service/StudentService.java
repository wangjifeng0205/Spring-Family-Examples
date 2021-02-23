package net.wangjifeng.service;

import net.wangjifeng.entity.Student;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: StudentService
 */

public interface StudentService {

    /**
     * 查询所有学生
     * @return List<Student>
     */
    public List<Student> findAll();

    /**
     * 根据学生id查询学生信息
     * @param id：学生id
     * @return Student
     */
    public Student findOne(Long id);

    /**
     * 根据学号查询学生信息
     * @param studentNo：学号
     * @return Student
     */
    public Student findByStudentNo(String studentNo);
}

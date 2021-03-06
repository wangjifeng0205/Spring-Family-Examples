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
     * 根据学生学号查询学生信息
     * @param studentNo：学生学号
     * @return Student
     */
    public Student findByStudentNo(String studentNo);

    /**
     * 添加学生信息
     * @param student：学生实体
     */
    void add(Student student);

    /**
     * 根据id更新学生信息
     * @param id：学生id
     * @param student：学生实体
     */
    void updateAll(Long id, Student student);

    /**
     * 根据学生id删除对应的学生
     * @param id：学生id
     */
    void delete(Long id);

    /**
     * 根据学生id更新学生部分的信息
     * @param id：学生id
     * @param student：学生实体
     */
    void updatePart(Long id, Student student);
}

package net.wangjifeng.service;

import net.wangjifeng.entity.Student;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/6/23
 * @description: StudentService
 */

public interface StudentService {

    /**
     * 通过id查询某个学生的信息(BaseMapper<T>中的方法)
     * @param id: 学生id
     * @return Student
     */
    public Student selectById(Long id);

    /**
     * 通过id查询某个学生的信息(通过xmlMapper实现)
     * @param id: 学生id
     * @return Student
     */
    public Student findById(Long id);

    /**
     * 保存一个学生对象(BaseMapper<T>中的方法)
     * @param student
     */
    public void insert(Student student);

    /**
     * 查询性别为sex，年龄大于age的学生(普通的Wrapper)
     * @param sex: 性别
     * @param age: 年龄
     * @return 学生list
     */
    public List<Student> findByWrapper(String sex, Integer age);

    /**
     * 查询性别为sex，年龄大于age的学生(Lambda形式的Wrapper)
     * @param sex: 性别
     * @param age: 年龄
     * @return 学生list
     */
    public List<Student> findByWrapperLambda(String sex, Integer age);

    /**
     * 更新学生信息(Wrapper形式)
     * @param student: 需要更新的学生实体
     */
    public void updateByWrapper(Student student);

    /**
     * 更新学生信息(BaseMapper<T>中的方法)
     * @param student: 需要更新的学生实体
     */
    public void updateById(Student student);

    /**
     * 更新学生信息(Lambda形式的Wrapper)
     * @param student: 需要更新的学生实体
     */
    public void updateByWrapperLambda(Student student);
}

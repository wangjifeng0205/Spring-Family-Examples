package net.wangjifeng.mongodb.service;

import net.wangjifeng.mongodb.entity.Student;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/9/30
 * @description: MongoDBService
 */

public interface MongoDBService {

    /**
     * 根据id查找一个学生对象
     * @param id id
     * @return 学生对象
     */
    public Student findOne(Long id);

    /**
     * 新增一个学生对象
     * @param student 学生对象
     */
    public void add(Student student);

    /**
     * 查询某个年龄段的学生
     * @param form 年龄下限
     * @param to 年龄上限
     * @return List<Student>
     */
    public List<Student> query(Integer form, Integer to);


    /**
     * 删除一个学生
     * @param id id
     */
    public void delete(Long id);

    /**
     * 根据班级修改学生信息，查找clazz班级的学生，修改年龄为18岁
     * @param student 学生对象
     * @param clazz 班级
     */
    public void update(Student student, String clazz);

}

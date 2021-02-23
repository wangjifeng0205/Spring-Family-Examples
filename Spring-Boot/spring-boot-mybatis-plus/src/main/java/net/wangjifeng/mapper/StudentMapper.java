package net.wangjifeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.wangjifeng.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: StudentMapper
 */

/**
 * 此处'StudentMapper'继承了'BaseMapper<T>'接口，就拥有了mybatis-plus提供的公共基础的CRUD方法。
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 查询所有学生信息
     * @return List<Student>
     */
    List<Student> findAll();

    /**
     * 通过id查询学生信息
     * @param id：学生id
     * @return Student
     */
    Student findOne(Long id);

    /**
     * 通过学号查询学生信息
     * @param studentNo：学生学号
     * @return Student
     */
    Student findByStudentNo(String studentNo);


}

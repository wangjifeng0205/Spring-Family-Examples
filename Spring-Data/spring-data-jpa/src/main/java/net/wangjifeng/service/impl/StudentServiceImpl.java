package net.wangjifeng.service.impl;

import net.wangjifeng.entity.Student;
import net.wangjifeng.mapper.StudentMapper;
import net.wangjifeng.service.StudentService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: WJF
 * @date: 2020/7/11
 * @description: StudentServiceImpl
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public Student findByStudentNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }

    @Override
    public List<Student> findByStudentNameLike(String studentName) {
        return studentMapper.findByStudentNameLike("%" + studentName + "%");
    }

    /**
     * Optional<T> 这个类是为了防止空指针异常的，很好用的东西。
     * @param id 学生id
     * @return Student
     */
    @Override
    public Student findById(Long id) {
        Optional<Student> student = studentMapper.findById(id);
        return student.isPresent() ? student.get() : null;
    }

    /**
     * 查询学生信息列表，每页为pageSize条，排序按年龄升序。
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @return List<Student>
     */
    @Override
    public List<Student> findAllPage(Integer pageNum, Integer pageSize) {
        // 注意JPA的分页当前页是从0开始的，假如我们查询第一页的数据，对于JPA来说，当前页是0页。
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Order.asc("age")));
        Page<Student> students = studentMapper.findAll(pageable);
        return students.get().collect(Collectors.toList());
    }

    @Override
    public List<Student> findBySex(String sex) {
        return studentMapper.findBySex(sex);
    }


}

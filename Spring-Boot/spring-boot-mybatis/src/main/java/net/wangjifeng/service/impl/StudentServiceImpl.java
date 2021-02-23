package net.wangjifeng.service.impl;

import net.wangjifeng.service.StudentService;
import net.wangjifeng.entity.Student;
import net.wangjifeng.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/16
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
    public Student findOne(Long id) {
        return studentMapper.findOne(id);
    }

    @Override
    public Student findByStudentNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }


}

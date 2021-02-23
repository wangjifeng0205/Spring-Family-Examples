package net.wangjifeng.service.impl;

import net.wangjifeng.entity.Student;
import net.wangjifeng.mapper.StudentMapper;
import net.wangjifeng.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAll(Long id, Student student) {
        student.setId(id);
        studentMapper.delete(id);
        studentMapper.add(student);
    }

    @Override
    public void delete(Long id) {
        studentMapper.delete(id);
    }

    @Override
    public void updatePart(Long id, Student student) {
        student.setId(id);
        studentMapper.update(student);
    }

}

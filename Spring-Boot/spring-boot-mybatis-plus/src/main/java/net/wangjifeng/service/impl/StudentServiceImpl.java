package net.wangjifeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.wangjifeng.entity.Student;
import net.wangjifeng.mapper.StudentMapper;
import net.wangjifeng.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/6/23
 * @description: StudentServiceImpl
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student selectById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentMapper.findOne(id);
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public List<Student> findByWrapper(String sex, Integer age) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("SEX", sex);
        queryWrapper.gt("AGE", age);
        return studentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Student> findByWrapperLambda(String sex, Integer age) {
        LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery().eq(Student::getSex, sex).gt(Student::getAge, age);
        return studentMapper.selectList(queryWrapper);
    }

    @Override
    public void updateByWrapper(Student student) {
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("ID", student.getId());
        studentMapper.update(student, updateWrapper);
    }

    @Override
    public void updateById(Student student) {
        studentMapper.updateById(student);
    }

    @Override
    public void updateByWrapperLambda(Student student) {
        LambdaUpdateWrapper<Student> updateWrapper = Wrappers.<Student>lambdaUpdate().set(Student::getId, student.getId());
        studentMapper.update(student, updateWrapper);
    }
}

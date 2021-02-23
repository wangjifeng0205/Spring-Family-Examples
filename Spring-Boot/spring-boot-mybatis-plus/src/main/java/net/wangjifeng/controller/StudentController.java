package net.wangjifeng.controller;

import net.wangjifeng.entity.Student;
import net.wangjifeng.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/6/23
 * @description: StudentController
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    public StudentService studentService;

    @GetMapping("/selectById/{id}")
    public Student selectById(@PathVariable("id") Long id) {
        return studentService.selectById(id);
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @PostMapping("/insert")
    public void insert(@RequestBody Student student) {
        studentService.insert(student);
    }

    @RequestMapping("/findByWrapper")
    public List<Student> findByWrapper(String sex, Integer age) {
        return studentService.findByWrapper(sex, age);
    }

    @RequestMapping("/findByWrapperLambda")
    public List<Student> findByWrapperLambda(String sex, Integer age) {
        return studentService.findByWrapperLambda(sex, age);
    }

    @RequestMapping("/updateByWrapper")
    public void updateByWrapper(Student student) {
        studentService.updateByWrapper(student);
    }

    @RequestMapping("/updateById")
    public void updateById(Student student) {
        studentService.updateById(student);
    }

    @RequestMapping("/updateByWrapperLambda")
    public void updateByWrapperLambda(Student student) {
        studentService.updateByWrapperLambda(student);
    }

}

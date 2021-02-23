package net.wangjifeng.controller;

import net.wangjifeng.entity.Student;
import net.wangjifeng.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: StudentController
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/findAll")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @RequestMapping("/findOne")
    public Student findOne(Long id) {
        return studentService.findOne(id);
    }

    @RequestMapping("/findByStudentNo")
    public Student findByStudentNo(String studentNo) {
        return studentService.findByStudentNo(studentNo);
    }

}

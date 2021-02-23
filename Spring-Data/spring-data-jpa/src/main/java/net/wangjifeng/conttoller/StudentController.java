package net.wangjifeng.conttoller;

import net.wangjifeng.entity.Student;
import net.wangjifeng.service.StudentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/7/11
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

    @RequestMapping("/findByStudentNo/{studentNo}")
    public Student findByStudentNo(@PathVariable("studentNo") String studentNo) {
        return studentService.findByStudentNo(studentNo);
    }

    @RequestMapping("/findByStudentNameLike/{studentName}")
    public List<Student> findByStudentNameLike(@PathVariable("studentName") String studentName) {
        return studentService.findByStudentNameLike(studentName);
    }

    @RequestMapping("/findAllPage/{pageNum}/{pageSize}")
    public List<Student> findAllPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return studentService.findAllPage(pageNum,pageSize);
    }

    @RequestMapping("/findById/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @RequestMapping("/findBySex/{sex}")
    public List<Student> findBySex(@PathVariable("sex") String sex) {
        return studentService.findBySex(sex);
    }

}

package net.wangjifeng.test;

import net.wangjifeng.JDBCApplication;
import net.wangjifeng.entity.Student;
import net.wangjifeng.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/7/29
 * @description: StudentServiceTest
 */
@SpringBootTest(classes = JDBCApplication.class)
@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Resource
    private StudentService studentService;

    @Test
    public void findAllTest() {
        List<Student> list = studentService.findAll();
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void findByIdTest() {
        Student student = studentService.findById(1L);
        System.out.println(student);
    }

    @Test
    public void findByStudentNameTest() {
        List<Student> list = studentService.findByStudentName("王");
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void addTest() {
        Student student = new Student();
        student.setStudentName("李文进");
        student.setStudentNo("G030534");
        student.setClazz("G0305");
        student.setSex("男");
        student.setAge(20);
        student.setId(5L);
        studentService.add(student);
    }

    @Test
    public void updateTest() {
        Student student = new Student();
        student.setStudentName("李文进");
        student.setStudentNo("G030538");
        student.setClazz("G0305");
        student.setSex("男");
        student.setAge(22);
        student.setId(5L);
        studentService.update(student);
    }

    @Test
    public void deleteTest() {
        studentService.delete(5L);
    }

    @Test
    public void countTest() {
        System.out.println(studentService.count());
    }

}

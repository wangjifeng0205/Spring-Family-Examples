package net.wangjifeng.service;

import net.wangjifeng.TestApplication;
import net.wangjifeng.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/23
 * @description: StudentServiceTest
 */

/**
 * {@link SpringBootTest}：配置文件属性的读取。读取classes标志的启动类的配置文件和运行环境，并加载。
 * {@link RunWith}：'RunWith'注解就是一个运行器，加载value的Class测试环境。
 */
@SpringBootTest(classes = TestApplication.class)
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
    public void findOneTest() {
        Student student = studentService.findOne(1L);
        System.out.println(student);
    }

    @Test
    public void findByStudentNoTest() {
        Student student = studentService.findByStudentNo("G030511");
        System.out.println(student);
    }

}

package net.wangjifeng.service;

import net.wangjifeng.ElasticSearchApplication;
import net.wangjifeng.entity.Student;
import net.wangjifeng.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/5/26
 * @description: UpdateServiceTest
 */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class UpdateServiceTest {

    @Resource
    private UpdateService updateService;

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void updateTest() {
        Student student = new Student();
        student.setId(1L);
        student.setStudentName("胡锦强");
        student.setStudentNo("G030510");
        student.setAge(19);
        student.setSex("男");
        student.setClazz("G0305");
        String message = updateService.update(student);
        System.out.println(message);
    }


    /**
     * 参考{@link IndexServiceTest}的 'indexAsyncTest()' 的注释，同理。
     * @throws InterruptedException
     */
    @Test
    public void updateAsyncTest() throws InterruptedException {
        Student student = studentMapper.findOne(1L);
        String message = updateService.updateAsync(student);
        Thread.sleep(2000);
        System.out.println(message);
    }
}

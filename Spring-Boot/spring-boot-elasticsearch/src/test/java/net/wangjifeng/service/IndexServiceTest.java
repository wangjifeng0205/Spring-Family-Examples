package net.wangjifeng.service;

import net.wangjifeng.ElasticSearchApplication;
import net.wangjifeng.entity.Student;
import net.wangjifeng.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/23
 * @description: IndexServiceTest
 */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class IndexServiceTest {

    @Resource
    private IndexService indexService;

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void indexTest() {
        List<Student> list = studentMapper.findAll();
        for (Student student : list) {
            String message = indexService.index(student);
            System.out.println(message);
        }
    }

    /**
     * 测试异步索引的时候，执行indexAsync方法的线程还没有执行完毕，测试类的线程已经
     * 运行完毕，运行环境就会关闭，那么程序与elasticsearch的连接就会强制关闭，就会出现连接
     * 已经关闭的异常：Connection is Closed, 所以让测试类线程睡眠两秒钟，
     * 等待执行indexAsync方法的线程运行完毕。或者自己写一个controller去调用indexAsync方法，
     * 也不会出现异常。但是返回的响应还是不一定能接受到信息'message'。
     * @throws InterruptedException
     */
    @Test
    public void indexAsyncTest() throws InterruptedException {
        Student student = studentMapper.findOne(1L);
        student.setId(5L);
        String message = indexService.indexAsync(student);
        Thread.sleep(2000);
        System.out.println(message);
    }

}

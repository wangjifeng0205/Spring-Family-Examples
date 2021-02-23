package net.wangjifeng.service;

import net.wangjifeng.ElasticSearchApplication;
import net.wangjifeng.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/5/26
 * @description: GetServiceTest
 */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class GetServiceTest {

    @Resource
    private GetService getService;

    @Test
    public void getTest() {
        Student student = getService.get("1");
        System.out.println(student);
    }

    /**
     * 参考{@link IndexServiceTest}的 'indexAsyncTest()' 的注释，同理。
     * @throws InterruptedException
     */
    @Test
    public void getAsyncTest() throws InterruptedException {
        Student student = getService.getAsync("1");
        Thread.sleep(2000);
        System.out.println(student);
    }

    @Test
    public void getSourceTest() {
        Student student = getService.getSource("1");
        System.out.println(student);
    }


    /**
     * 参考{@link IndexServiceTest}的 'indexAsyncTest()' 的注释，同理。
     * @throws InterruptedException
     */
    @Test
    public void getSourceAsyncTest() throws InterruptedException {
        Student student = getService.getSourceAsync("1");
        Thread.sleep(2000);
        System.out.println(student);
    }


    @Test
    public void existsTest() {
        Boolean bool = getService.exists("1");
        System.out.println(bool);
    }

}

package net.wangjifeng.service;

import net.wangjifeng.ElasticSearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/5/26
 * @description: DeleteServiceTest
 */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class DeleteServiceTest {

    @Resource
    private DeleteService deleteService;

    @Test
    public void deleteTest() {
        String message = deleteService.delete("1");
        System.out.println(message);
    }

    /**
     * 参考{@link IndexServiceTest}的 'indexAsyncTest()' 的注释，同理。
     * @throws InterruptedException
     */
    @Test
    public void deleteAsyncTest() throws InterruptedException {
        String message = deleteService.deleteAsync("1");
        Thread.sleep(2000);
        System.out.println(message);
    }

}

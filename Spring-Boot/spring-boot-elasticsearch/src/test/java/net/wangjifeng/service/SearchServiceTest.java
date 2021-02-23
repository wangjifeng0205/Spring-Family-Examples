package net.wangjifeng.service;

import net.wangjifeng.ElasticSearchApplication;
import net.wangjifeng.constants.Constants;
import net.wangjifeng.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/26
 * @description: SearchServiceTest
 */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class SearchServiceTest {

    @Resource
    private SearchService searchService;


    /**
     * 例1：搜索年龄在17到18岁的学生。
     */
    @Test
    public void searchRangeTest() {
        List<Student> list = searchService.searchAgeRange(17, 18, Constants.INDEX_STUDENT);
        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     * 例2：搜索年龄在18到19岁并且班级为'G0305'的学生。
     */
    @Test
    public void searchBoolTest() {
        List<Student> list = searchService.searchBool();
        for (Student student : list) {
            System.out.println(student);
        }
    }


    /**
     * 例3：组合 + 聚合查询，异步的搜索我就不举例了，牛牛们自行测试，max，min函数和高亮搜索相信牛牛们也都可以的。
     */
    @Test
    public void searchBoolAndAggregationTest() {
        searchService.searchBoolAndAggregation();
    }


    /**
     * 例4：查询学生名称中包含'王'字的学生
     */
    @Test
    public void searchMatchTest() {
        List<Student> list = searchService.searchMatch("王");
        for (Student student : list) {
            System.out.println(student);
        }
    }
}

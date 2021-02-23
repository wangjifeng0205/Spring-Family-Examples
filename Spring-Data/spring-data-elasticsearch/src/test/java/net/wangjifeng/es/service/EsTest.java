package net.wangjifeng.es.service;

import net.wangjifeng.elasticsearch.ElasticSearchApplication;
import net.wangjifeng.elasticsearch.entity.Student;
import net.wangjifeng.elasticsearch.service.EsService;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: WJF
 * @date: 2020/9/29
 * @description: EsTest
 */
@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class EsTest {

    @Resource
    private EsService esService;

    @Test
    public void insert() {
        Student student = new Student();
        student.setId(5L);
        student.setAge(20);
        student.setClazz("G0306");
        student.setSex("男");
        student.setStudentName("whiteOmega");
        student.setStudentNo("G030606");
        esService.insert(student);
    }

    @Test
    public void get() {
        System.out.println(esService.get(1L));
    }

    @Test
    public void query() {
        Student student = new Student();
        student.setClazz("G0305");
        SearchHits<Student> searchHits = esService.query(student);
        List<Student> list = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        // 数据结果
        list.forEach(System.out::println);
        // 聚合结果
        Aggregations aggregations = searchHits.getAggregations();
        if (Objects.nonNull(aggregations)) {
            Terms terms = aggregations.get("sex");
            terms.getBuckets().forEach(bucket -> System.out.println("key: " + bucket.getKeyAsString() + "---> count: " + bucket.getDocCount()));
        }
    }

}

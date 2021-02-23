package net.wangjifeng.elasticsearch.service.impl;

import net.wangjifeng.elasticsearch.constants.Constants;
import net.wangjifeng.elasticsearch.entity.Student;
import net.wangjifeng.elasticsearch.service.EsService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: WJF
 * @date: 2020/9/29
 * @description: EsServiceImpl
 */
@Service
public class EsServiceImpl implements EsService {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void insert(Student student) {
        elasticsearchRestTemplate.index(indexQuery(student), IndexCoordinates.of(Constants.INDEX_STUDENT));
    }

    @Override
    public void insertList(List<Student> list) {
        List<IndexQuery> indexQueryList = new ArrayList<>();
        list.forEach(student -> indexQueryList.add(indexQuery(student)));
        elasticsearchRestTemplate.bulkIndex(indexQueryList, IndexCoordinates.of(Constants.INDEX_STUDENT));
    }

    @Override
    public Student get(Long id) {
        return elasticsearchRestTemplate.get(String.valueOf(id), Student.class, IndexCoordinates.of(Constants.INDEX_STUDENT));
    }

    @Override
    public SearchHits<Student> query(Student student) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (Objects.nonNull(student.getId())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("id", student.getId()));
        }
        if (StringUtils.isNotBlank(student.getStudentName())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("studentName" + Constants.KEYWORD, student.getStudentName()));
        }
        if (StringUtils.isNotBlank(student.getStudentNo())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("studentNo" + Constants.KEYWORD, student.getStudentNo()));
        }
        if (StringUtils.isNotBlank(student.getSex())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("sex" + Constants.KEYWORD, student.getSex()));
        }
        if (Objects.nonNull(student.getAge())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("age", student.getAge()));
        }
        if (StringUtils.isNotBlank(student.getClazz())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("clazz" + Constants.KEYWORD, student.getClazz()));
        }
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("sex").field("sex" + Constants.KEYWORD).order(BucketOrder.key(false));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .addAggregation(aggregationBuilder)
                .build();
        return elasticsearchRestTemplate.search(nativeSearchQuery, Student.class, IndexCoordinates.of(Constants.INDEX_STUDENT));
    }

    /**
     * 构建IndexQuery
     * @param student student
     * @return IndexQuery
     */
    private IndexQuery indexQuery(Student student) {
        return new IndexQueryBuilder()
                .withId(student.getId().toString())
                .withObject(student)
                .build();
    }

}

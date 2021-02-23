package net.wangjifeng.service.impl;

import net.wangjifeng.constants.Constants;
import net.wangjifeng.entity.Student;
import net.wangjifeng.service.SearchService;
import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/22
 * @description: SearchServiceImpl
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private Gson gson;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public List<Student> searchRange(Object from, Object to, String field, String index) {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(field);
        if (from != null) {
            rangeQueryBuilder.from(from, true);
        }
        if (to != null) {
            rangeQueryBuilder.to(to, true);
        }
        boolQueryBuilder.must();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Student> searchAgeRange(Integer from, Integer to, String index) {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").gte(from).lte(to));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Student> searchBool() {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").gte(18).lte(19));
        boolQuery.must(QueryBuilders.termQuery("clazz" + Constants.KEYWORD,"G0305"));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
        SearchRequest searchRequest = new SearchRequest(Constants.INDEX_STUDENT);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void searchBoolAndAggregation() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").gte(18).lte(19));
        boolQuery.must(QueryBuilders.termQuery("clazz" + Constants.KEYWORD,"G0305"));
        // 聚合分组：按clazz字段分组，并将结果取名为clazz，es默认是分词的，为了精确配置，需要加上‘.keyword’关键词后缀。
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("clazz").field("clazz" + Constants.KEYWORD);
        // 聚合求和：求符合查询条件的学生的年龄的和，并将结果取名为ageSum，因为不是字符串，所以默认是精确匹配，不支持分词。
        aggregationBuilder.subAggregation(AggregationBuilders.sum("ageSum").field("age"));
        // 聚合求平均：求符合查询条件的学生的年龄的平均值，并将结果取名为ageAvg，因为不是字符串，所以默认是精确匹配，不支持分词。
        aggregationBuilder.subAggregation(AggregationBuilders.avg("ageAvg").field("age"));
        // 聚合求数量：按学号查询符合查询条件的学生个数，并将结果取名为count，es默认是分词的，为了精确配置，需要加上‘.keyword’关键词后缀。
        aggregationBuilder.subAggregation(AggregationBuilders.count("count").field("studentNo" + Constants.KEYWORD));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boolQuery);
        builder.aggregation(aggregationBuilder);
        // 按年龄降序排序。
        builder.sort("age", SortOrder.DESC);
        SearchRequest request = new SearchRequest("student_info");
        request.source(builder);
        try {
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                System.out.println(student);
            }
            // 使用Terms对象接收
            Terms clazz = search.getAggregations().get("clazz");
            for (Terms.Bucket bucket : clazz.getBuckets()) {
                System.out.println(bucket.getDocCount());

                System.out.println("=====================");
                // 使用ParsedSum对象接收
                ParsedSum ageCount = bucket.getAggregations().get("ageSum");
                System.out.println(ageCount.getType());
                System.out.println(ageCount.getValue());
                System.out.println(ageCount.getValueAsString());
                System.out.println(ageCount.getMetaData());
                System.out.println(ageCount.getName());

                System.out.println("=====================");
                // 使用ParsedAvg对象接收
                ParsedAvg ageAvg = bucket.getAggregations().get("ageAvg");
                System.out.println(ageAvg.getType());
                System.out.println(ageAvg.getValue());
                System.out.println(ageAvg.getValueAsString());
                System.out.println(ageAvg.getMetaData());
                System.out.println(ageAvg.getName());

                System.out.println("=====================");
                // 使用ParsedValueCount对象接收
                ParsedValueCount count = bucket.getAggregations().get("count");
                System.out.println(count.getType());
                System.out.println(count.getValue());
                System.out.println(count.getValueAsString());
                System.out.println(count.getMetaData());
                System.out.println(count.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> searchMatch(String matchStudentName) {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 分词查询时不加'.keyword'关键字
        boolQueryBuilder.must(QueryBuilders.matchQuery("studentName",matchStudentName));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        SearchRequest searchRequest = new SearchRequest("student_info");
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits().getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

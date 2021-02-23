package net.wangjifeng.service.impl;

import net.wangjifeng.constants.Constants;
import net.wangjifeng.entity.Student;
import net.wangjifeng.service.GetService;
import com.google.gson.Gson;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author: WJF
 * @date: 2020/5/25
 * @description: GetServiceImpl
 */
@Service
public class GetServiceImpl implements GetService {

    @Resource
    private Gson gson;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Student get(String id) {
        Student student = new Student();
        GetRequest getRequest = new GetRequest(Constants.INDEX_STUDENT, id);
        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            String source = getResponse.getSourceAsString();
            student = gson.fromJson(source, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student getAsync(String id) {
        Student student = new Student();
        // 设置索引和文档id
        GetRequest getRequest = new GetRequest(Constants.INDEX_STUDENT, id);
        restHighLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, getActionListener(student));
        return student;
    }

    @Override
    public Student getSource(String id) {
        Student student = new Student();
        GetSourceRequest getSourceRequest = this.initGetSourceRequest(id);
        try {
            GetSourceResponse source = restHighLevelClient.getSource(getSourceRequest, RequestOptions.DEFAULT);
            Map<String, Object> objectMap = source.getSource();
            student.setId((Long) objectMap.get("id"));
            // 只有studentName字段会有值
            student.setStudentName((String) objectMap.get("studentName"));
            student.setStudentNo((String) objectMap.get("studentNo"));
            student.setSex((String) objectMap.get("sex"));
            student.setAge((Integer) objectMap.get("age"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student getSourceAsync(String id) {
        Student student = new Student();
        GetSourceRequest getSourceRequest = this.initGetSourceRequest(id);
        restHighLevelClient.getSourceAsync(getSourceRequest, RequestOptions.DEFAULT, getAsyncActionListener(student));
        return student;
    }

    @Override
    public Boolean exists(String id) {
        Boolean bool = false;
        GetRequest getRequest = new GetRequest(Constants.INDEX_STUDENT, id);
        try {
            bool = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }


    /**
     * 初始化GetSourceRequest，并设置数据源。
     * @param id
     * @return GetSourceRequest
     */
    private GetSourceRequest initGetSourceRequest(String id) {
        // 构建GetSourceRequest
        GetSourceRequest getSourceRequest = new GetSourceRequest(Constants.INDEX_STUDENT, id);
        // 设置includes为studentName为搜索字段，只搜索studentName这个字段，比如：select * from table 变成 select table.studentName from table
        String[] includes = new String[]{"studentName"};
        // 设置excludes为空
        String[] excludes = Strings.EMPTY_ARRAY;
        // 设置拿取数据源上下文的包含和排除的字段，includes和excludes都为null就是获取一整个对象
        FetchSourceContext context = new FetchSourceContext(true, includes, excludes);
        getSourceRequest.fetchSourceContext(context);
        return getSourceRequest;
    }


    /**
     * 异步获取的回调监听器，根据不同的结果做出不同的处理
     * @param student
     * @return ActionListener<GetResponse>
     */
    private ActionListener<GetResponse> getActionListener(Student student) {
        return new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getResponse) {
                String source = getResponse.getSourceAsString();
                Student JSONStudent = gson.fromJson(source, Student.class);
                student.setId(JSONStudent.getId());
                student.setStudentNo(JSONStudent.getStudentNo());
                student.setStudentName(JSONStudent.getStudentName());
                student.setSex(JSONStudent.getSex());
                student.setAge(JSONStudent.getAge());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
    }


    /**
     * 异步获取数据源的回调监听器，根据不同的结果做出不同的处理
     * @param student
     * @return ActionListener<GetSourceResponse>
     */
    private ActionListener<GetSourceResponse> getAsyncActionListener(Student student) {
        return new ActionListener<GetSourceResponse>() {
            @Override
            public void onResponse(GetSourceResponse getSourceResponse) {
                Map<String, Object> objectMap = getSourceResponse.getSource();
                student.setId((Long) objectMap.get("id"));
                // 只有studentName字段会有值
                student.setStudentName((String) objectMap.get("studentName"));
                student.setStudentNo((String) objectMap.get("studentNo"));
                student.setSex((String) objectMap.get("sex"));
                student.setAge((Integer) objectMap.get("age"));
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
    }
}

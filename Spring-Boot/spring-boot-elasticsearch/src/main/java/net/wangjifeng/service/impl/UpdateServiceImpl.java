package net.wangjifeng.service.impl;

import net.wangjifeng.constants.Constants;
import net.wangjifeng.entity.Student;
import net.wangjifeng.service.GetService;
import net.wangjifeng.service.UpdateService;
import com.google.gson.Gson;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: WJF
 * @date: 2020/5/25
 * @description: UpdateServiceImpl
 */

@Service
public class UpdateServiceImpl implements UpdateService {

    @Resource
    private Gson gson;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private GetService getService;

    @Override
    public String update(Student student) {
        this.checkData(String.valueOf(student.getId()));
        UpdateRequest updateRequest = new UpdateRequest(Constants.INDEX_STUDENT, String.valueOf(student.getId()));
        String source = gson.toJson(student);
        updateRequest.doc(source, XContentType.JSON);
        try {
            restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "修改成功！";
    }

    @Override
    public String updateAsync(Student student) {
        this.checkData(String.valueOf(student.getId()));
        StringBuilder builder = new StringBuilder();
        UpdateRequest updateRequest = new UpdateRequest(Constants.INDEX_STUDENT, String.valueOf(student.getId()));
        String source = gson.toJson(student);
        updateRequest.doc(source, XContentType.JSON);
        restHighLevelClient.updateAsync(updateRequest, RequestOptions.DEFAULT, updateActionListener(builder));
        return builder.toString();
    }


    /**
     * 核查数据
     * @param id
     */
    private void checkData(String id) {
        if (id == null) {
            throw new RuntimeException("参数student的id不能为null。");
        }
        if (Boolean.FALSE.equals(getService.exists(id))) {
            throw new RuntimeException(String.format("不存在id为%s的文档。", id));
        }
    }


    /**
     * 异步更新的回调监听器，根据不同的结果做出不同的处理
     * @param builder
     * @return ActionListener<UpdateResponse>
     */
    private ActionListener<UpdateResponse> updateActionListener(StringBuilder builder) {
        return new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                String statusName = updateResponse.status().name();
                int statusCode = updateResponse.status().getStatus();
                builder.append(statusName).append(Constants.CONNECTOR).append(statusCode);
            }

            @Override
            public void onFailure(Exception e) {
                builder.append("Fail").append(Constants.CONNECTOR).append(e.getMessage());
            }
        };
    }
}

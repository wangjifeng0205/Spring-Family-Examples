package net.wangjifeng.service.impl;

import net.wangjifeng.constants.Constants;
import net.wangjifeng.service.DeleteService;
import net.wangjifeng.service.GetService;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: WJF
 * @date: 2020/5/25
 * @description: DeleteServiceImpl
 */
@Service
public class DeleteServiceImpl implements DeleteService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private GetService getService;


    @Override
    public String delete(String id) {
        this.checkData(id);
        DeleteRequest deleteRequest = new DeleteRequest(Constants.INDEX_STUDENT, id);
        try {
            restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            return "删除失败！";
        }
        return "删除成功！";
    }

    @Override
    public String deleteAsync(String id) {
        this.checkData(id);
        StringBuilder builder = new StringBuilder();
        DeleteRequest deleteRequest = new DeleteRequest(Constants.INDEX_STUDENT, id);
        restHighLevelClient.deleteAsync(deleteRequest, RequestOptions.DEFAULT, deleteActionListener(builder));
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
     * 异步删除的回调监听器，根据不同的结果做出不同的处理
     * @param builder
     * @return ActionListener<DeleteResponse>
     */
    private ActionListener<DeleteResponse> deleteActionListener(StringBuilder builder) {
        return new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                String statusName = deleteResponse.status().name();
                int statusCode = deleteResponse.status().getStatus();
                builder.append(statusName).append(Constants.CONNECTOR).append(statusCode);
            }

            @Override
            public void onFailure(Exception e) {
                builder.append("Fail").append(Constants.CONNECTOR).append(e.getMessage());
            }
        };
    }
}

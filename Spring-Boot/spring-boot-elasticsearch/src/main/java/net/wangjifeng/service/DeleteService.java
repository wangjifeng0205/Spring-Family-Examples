package net.wangjifeng.service;

/**
 * @author: WJF
 * @date: 2020/5/25
 * @description: DeleteService
 */

public interface DeleteService {

    /**
     * 同步删除一个文档
     * @param id：文档id
     * @return String
     */
    public String delete(String id);


    /**
     * 异步删除一个文档
     * @param id：文档id
     * @return String
     */
    public String deleteAsync(String id);

}

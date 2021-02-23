package net.wangjifeng.service;

import net.wangjifeng.entity.Student;

/**
 * @author: WJF
 * @date: 2020/5/25
 * @description: UpdateService
 */

public interface UpdateService {

    /**
     * 同步修改一个学生信息
     * @param student：学生实体
     * @return String
     */
    public String update(Student student);


    /**
     * 异步修改一个学生信息
     * @param student：学生实体
     * @return String
     */
    public String updateAsync(Student student);

}

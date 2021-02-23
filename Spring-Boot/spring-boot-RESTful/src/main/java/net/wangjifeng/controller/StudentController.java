package net.wangjifeng.controller;

import net.wangjifeng.entity.Student;
import net.wangjifeng.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: StudentController
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * GET ：请求从服务器获取特定资源。举个例子：GET /student（获取所有学生）
     * @return List<Student>
     */
    @GetMapping("/student")
    public List<Student> student() {
        return studentService.findAll();
    }

    /**
     * GET ：请求从服务器获取特定资源。举个例子：GET /student/1（获取id为1学生）
     * @param id
     * @return Student
     */
    @GetMapping("/student/{id}")
    public Student student(@PathVariable("id") Long id) {
        return studentService.findOne(id);
    }

    /**
     * POST ：在服务器上创建一个新的资源。举个例子：POST /student（添加学生）
     * @param student
     */
    @PostMapping("/student")
    public void student(@RequestBody Student student) {
        studentService.add(student);
    }

    /**
     * PUT ：更新服务器上的资源（客户端提供更新后的整个资源）。举个例子：PUT /student/1（更新学号为 1 的学生的所有信息）
     * @param id
     */
    @PutMapping("/student/{id}")
    public void updateById(@PathVariable("id") Long id, Student student) {
        studentService.updateAll(id,student);
    }

    /**
     * DELETE ：从服务器删除特定的资源。举个例子：DELETE /student/1（删除学号为 1 的学生）
     * @param id
     */
    @DeleteMapping("/student/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    /**
     * PATCH ：更新服务器上的资源（客户端提供更改的属性，可以看做作是部分更新），使用的比较少，这里就不举例子了。
     * @param id
     * @param student
     */
    @PatchMapping("/student/{id}")
    public void updatePart(@PathVariable("id") Long id, Student student) {
        studentService.updatePart(id,student);
    }

}

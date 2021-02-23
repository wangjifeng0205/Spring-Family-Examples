package net.wangjifeng.controller;

import net.wangjifeng.entity.Student;
import net.wangjifeng.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: StudentController
 */
@Api(tags = "学生管理接口")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * GET ：请求从服务器获取特定资源。举个例子：GET /student（获取所有学生）
     * @return List<Student>
     */
    @ApiOperation(value = "查询所有学生", httpMethod = "GET", notes = "查询所有学生")
    @GetMapping("/student")
    public List<Student> student() {
        return studentService.findAll();
    }

    /**
     * GET ：请求从服务器获取特定资源。举个例子：GET /student/1（获取id为1学生）
     * @param id
     * @return Student
     */
    @ApiOperation(value = "查询学生详情", httpMethod = "GET", notes = "查询学生详情")
    @ApiImplicitParam(name = "id", value = "学生id", required = true, paramType = "form", dataTypeClass = Long.class)
    @GetMapping("/student/{id}")
    public Student student(@PathVariable("id") Long id) {
        return studentService.findOne(id);
    }

    /**
     * POST ：在服务器上创建一个新的资源。举个例子：POST /student（添加学生）
     * @param student
     */
    @PostMapping("/student")
    @ApiOperation(value = "添加学生", httpMethod = "POST", notes = "添加学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentName", value = "学生姓名", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "studentNo", value = "学生学号", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "sex", value = "学生性别", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "age", value = "学生年龄", required = true, paramType = "form", dataTypeClass = Integer.class)
    })
    public void student(@RequestBody Student student) {
        studentService.add(student);
    }

    /**
     * PUT ：更新服务器上的资源（客户端提供更新后的整个资源）。举个例子：PUT /student/1（更新学号为 1 的学生的所有信息）
     * @param id
     */
    @PutMapping("/student/{id}")
    @ApiOperation(value = "根据id修改学生信息(大范围)", httpMethod = "PUT", notes = "根据id修改学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生id", required = true, paramType = "from", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "studentName", value = "学生姓名", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "studentNo", value = "学生学号", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "sex", value = "学生性别", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "age", value = "学生年龄", required = true, paramType = "form", dataTypeClass = Integer.class)
    })
    public void updateById(@PathVariable("id") Long id, Student student) {
        studentService.updateAll(id,student);
    }

    /**
     * DELETE ：从服务器删除特定的资源。举个例子：DELETE /student/1（删除学号为 1 的学生）
     * @param id
     */
    @DeleteMapping("/student/{id}")
    @ApiOperation(value = "根据id删除学生信息", httpMethod = "DELETE", notes = "根据id删除学生信息")
    @ApiImplicitParam(name = "id", value = "学生id", required = true, paramType = "from", dataTypeClass = Long.class)
    public void deleteById(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    /**
     * PATCH ：更新服务器上的资源（客户端提供更改的属性，可以看做作是部分更新），使用的比较少，这里就不举例子了。
     * @param id
     * @param student
     */
    @PatchMapping("/student/{id}")
    @ApiOperation(value = "根据id修改学生信息(小范围)", httpMethod = "PATCH", notes = "根据id修改学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生id", required = true, paramType = "from", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "studentName", value = "学生姓名", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "studentNo", value = "学生学号", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "sex", value = "学生性别", required = true, paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "age", value = "学生年龄", required = true, paramType = "form", dataTypeClass = Integer.class)
    })
    public void updatePart(@PathVariable("id") Long id, Student student) {
        studentService.updatePart(id,student);
    }

}

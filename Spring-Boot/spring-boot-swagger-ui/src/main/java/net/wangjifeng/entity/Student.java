package net.wangjifeng.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: Student
 */

@ToString
@Getter
@Setter
@ApiModel("学生Entity")
public class Student implements Serializable {

    @ApiModelProperty("学生id")
    private Long id;

    @ApiModelProperty("学生姓名")
    private String studentName;

    @ApiModelProperty("学生学号")
    private String studentNo;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

}

package net.wangjifeng.entity;

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
public class Student implements Serializable {

    private Long id;

    private String studentName;

    private String studentNo;

    private String sex;

    private Integer age;

}

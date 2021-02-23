package net.wangjifeng.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: Student
 */

@ToString
@Getter
@Setter
@Entity
@Table(name = "t_student")
public class Student implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Column(name = "STUDENT_NO")
    private String studentNo;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "CLASS")
    private String clazz;

    /**
     * 实体类和数据库表对应，这些注解都很简单，我不再多说
     */

}

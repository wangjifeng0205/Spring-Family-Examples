package net.wangjifeng.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
/**
 * '@TableName'：此注解将表名和实体类映射起来，不写则默认以实体类名为表名进行数据库操作。
 * '@TableId'：此注解将声明的实体属性作为数据库表的主键字段，还有很多主键实现策咯，查看注解属性{@link TableId#type()}。
 * '@TableField'：此注解将表字段(非主键)和实体类属性映射起来，不写则默认以实体类属性名为表字段名进行数据库操作。
 */
@TableName("t_student")
public class Student implements Serializable {

    @TableId("ID")
    private Long id;

    @TableField("STUDENT_NAME")
    private String studentName;

    @TableField("STUDENT_NO")
    private String studentNo;

    @TableField("SEX")
    private String sex;

    @TableField("AGE")
    private Integer age;

    @TableField("CLASS")
    private String clazz;

}

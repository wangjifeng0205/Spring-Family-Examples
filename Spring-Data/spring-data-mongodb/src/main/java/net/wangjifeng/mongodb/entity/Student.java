package net.wangjifeng.mongodb.entity;

import net.wangjifeng.mongodb.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: Student
 */

@ToString
@Getter
@Setter
@Document(collection = Constants.INDEX_STUDENT)
public class Student implements Serializable {

    @Id
    private Long id;

    @Field("STUDENT_NAME")
    private String studentName;

    @Field("STUDENT_NO")
    private String studentNo;

    @Field("SEX")
    private String sex;

    @Field("AGE")
    private Integer age;

    @Field("CLAZZ")
    private String clazz;

}

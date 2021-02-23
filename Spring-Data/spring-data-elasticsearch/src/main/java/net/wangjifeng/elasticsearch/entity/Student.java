package net.wangjifeng.elasticsearch.entity;

import net.wangjifeng.elasticsearch.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author: WJF
 * @date: 2020/5/16
 * @description: Student
 */

@ToString
@Getter
@Setter
@Document(indexName = Constants.INDEX_STUDENT, type = Constants.DOC_TYPE)
public class Student implements Serializable {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String studentName;

    @Field(type = FieldType.Keyword)
    private String studentNo;

    @Field(type = FieldType.Keyword)
    private String sex;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Keyword)
    private String clazz;

}

package net.wangjifeng.mongodb.constants;


/**
 * @author: WJF
 * @date: 2020/5/22
 * @description: Constants
 */
public class Constants {

    /**
     * es搜索关键字
     */
    public static final String KEYWORD = ".keyword";

    /**
     * es的type类型：type字段将在 elasticsearch-version：8 中彻底删除，本来就觉得没得啥用。
     */
    public static final String DOC_TYPE = "_doc";

    /**
     * 学生信息索引类型
     */
    public static final String INDEX_STUDENT = "student_info";


    /**
     * 自定连接符
     */
    public static final String CONNECTOR = " --> ";


    /**
     * es的日期格式-国际
     */
    public static final String DATE_FORMAT_OS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * es的日期格式-China
     */
    public static final String DATE_FORMAT_CHINA = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

}

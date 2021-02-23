package net.wangjifeng.mapper;

import net.wangjifeng.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/7/11
 * @description: StudentMapper
 */
@org.springframework.stereotype.Repository
public interface StudentMapper extends JpaRepository<Student, Long>, CrudRepository<Student, Long>, Repository<Student, Long> {

    /**
     * 1. JPA提供了很多的接口：
     *  1、Repository接口
     *  2、CrudRepository接口
     *  3、PagingAndSortingRepository接口
     *  4、JpaRepository接口
     *  5、JPASpecificationExecutor接口
     *
     * 2. 实现Spring Data Jpa的Repository<T, ID>接口和CrudRepository<T, ID>接口即可拥有大量查询数据库的方法
     *    泛型：
     *     1. <T>: 实体类，用于和数据库表对应
     *     2. <ID>: 实体类的主键字段
     *
     * 3. Spring Data Jpa还可以根据你定义的查询数据库表的接口方法名称自动的生成代理对象，要继承Repository<T, ID>接口，
     *    才查询相应的内容，方法名称必须要遵循驼峰式命名规则，findXxxBy (findBy)（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
     */

    /* ================================start==============================> */

    /**
     * 这个方法式JPA提供的根据方法名称生成对应代理对象查询数据库的方法，只需要按照规则书写出方法名称，
     * 那么就Spring Data Jpa 就会自动的为此方法生成查询实现。当前方法的作用是根据学生学号查询学生信息。
     * @param studentNo 学生学号
     * @return 学生
     */
    Student findByStudentNo(String studentNo);

    /**
     * 这个方法式JPA提供的根据方法名称生成对应代理对象查询数据库的方法，只需要按照规则书写出方法名称，
     * 那么就Spring Data Jpa 就会自动的为此方法生成查询实现。当前方法的作用是根据学生姓名模糊查询学生信息列表。
     * @param studentName 学生学号
     * @return 学生列表
     */
    List<Student> findByStudentNameLike(String studentName);

    /* <===============================end=============================== */

    /* ================================start==============================> */

    /**
     * 这里使用的是JPQL语句，跟SQL语句很像，只是把表明换成对应的实体类entity，面向实体编程，这样才是一个完全的ORM关系的框架标准。
     * 注意这里不能使用星号，可以使用取别名的方式达到查询的效果。
     * JPQL语句 'SELECT s FROM Student s WHERE s.sex = :sex'的含义是：
     *           查询实体Student中属性sex(s.sex)为参数sex(:sex)的学生列表。
     * @param sex 学生性别
     * @return 学生信息列表
     */
    @Query("SELECT s FROM Student s WHERE s.sex = :sex")
    List<Student> findBySex(String sex);

    /* <===============================end=============================== */

}

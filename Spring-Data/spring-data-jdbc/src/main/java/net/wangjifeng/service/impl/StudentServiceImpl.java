package net.wangjifeng.service.impl;

import net.wangjifeng.entity.Student;
import net.wangjifeng.service.StudentService;
import net.wangjifeng.utils.MysqlUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/7/29
 * @description: StudentServiceImpl
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM `t_student`";
        return jdbcTemplate.query(sql, mapper());
    }

    @Override
    public Student findById(Long id) {
        String sql = "SELECT * FROM `t_student` WHERE ID = ?";
        /*
            实体类属性名称和表字段名称对不上时，需要自己实现RowMapper.
         */
        RowMapper<Student> mapper = new BeanPropertyRowMapper<>(Student.class);
        return jdbcTemplate.queryForObject(sql, mapper, id);
        // 自己实现RowMapper方式
        // return jdbcTemplate.queryForObject(sql, mapper(), id);
    }

    @Override
    public List<Student> findByStudentName(String studentName) {
        String sql = "SELECT * FROM `t_student` WHERE STUDENT_NAME LIKE CONCAT('%', ?, '%')";
        return jdbcTemplate.query(sql, mapper(), studentName);
    }

    @Override
    public void add(Student student) {
        String[] columns = {"ID", "STUDENT_NAME", "STUDENT_NO", "SEX", "AGE", "CLASS"};
        jdbcTemplate.update(
                MysqlUtils.insert("t_student", columns,
                        student.getId(),
                        student.getStudentName(), student.getStudentNo(),
                        student.getSex(),
                        student.getAge(),
                        student.getClazz())
        );
    }

    @Override
    public void update(Student student) {
        String[] columns = {"ID", "STUDENT_NAME", "STUDENT_NO", "SEX", "AGE", "CLASS"};
        jdbcTemplate.update(
                MysqlUtils.update("t_student", columns, "ID",
                        student.getId(),
                        student.getId(),
                        student.getStudentName(), student.getStudentNo(),
                        student.getSex(),
                        student.getAge(),
                        student.getClazz())
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(MysqlUtils.delete("t_student", "ID", id));
    }

    @Override
    public Integer count() {
        String sql = "SELECT COUNT(1) FROM `t_student`";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 表字段和数据实体属性映射关系
     * @return RowMapper<Student>
     */
    private RowMapper<Student> mapper() {
        return new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("ID");
                String studentName = rs.getString("STUDENT_NAME");
                String studentNo = rs.getString("STUDENT_NO");
                String sex = rs.getString("SEX");
                int age = rs.getInt("AGE");
                String clazz = rs.getString("CLASS");
                Student student = new Student();
                student.setId(id);
                student.setStudentName(studentName);
                student.setStudentNo(studentNo);
                student.setSex(sex);
                student.setAge(age);
                student.setClazz(clazz);
                return student;
            }
        };
    }


}

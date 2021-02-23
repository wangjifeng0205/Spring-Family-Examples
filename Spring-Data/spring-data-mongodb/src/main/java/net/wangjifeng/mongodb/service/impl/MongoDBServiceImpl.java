package net.wangjifeng.mongodb.service.impl;

import net.wangjifeng.mongodb.entity.Student;
import net.wangjifeng.mongodb.service.MongoDBService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/9/30
 * @description: MongoDBServiceImpl
 */
@Service
public class MongoDBServiceImpl implements MongoDBService {

    @Resource
    private MongoTemplate mongoTemplate;


    @Override
    public Student findOne(Long id) {
        return mongoTemplate.findById(id, Student.class);
    }

    @Override
    public void add(Student student) {
        mongoTemplate.insert(student);
    }

    @Override
    public List<Student> query(Integer form, Integer to) {
        Query query = new Query();
        Criteria criteria = Criteria.where("AGE").gte(form).lte(to);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Student.class);
    }

    @Override
    public void delete(Long id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("ID").is(id);
        query.addCriteria(criteria);
        mongoTemplate.remove(query, Student.class);
    }

    @Override
    public void update(Student student, String clazz) {
        Query query = new Query(Criteria.where("CLAZZ").is(clazz));
        Update update = new Update();
        update.inc("AGE", 18);
        mongoTemplate.update(Student.class)
                .matching(query)
                .apply(update)
                .findAndModifyValue();
    }
}

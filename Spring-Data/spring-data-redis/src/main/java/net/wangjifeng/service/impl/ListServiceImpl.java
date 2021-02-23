package net.wangjifeng.service.impl;

import net.wangjifeng.enums.OpsType;
import net.wangjifeng.service.ListService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: WJF
 * @date: 2020/5/28
 * @description: ListServiceImpl
 */
@Service
public class ListServiceImpl implements ListService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addList(String key, List<Object> list, OpsType type) {
        switch (type) {
            case RIGHT:
                redisTemplate.opsForList().rightPushAll(key, list);
                break;
            case LEFT:
                redisTemplate.opsForList().leftPushAll(key, list);
                break;
            default:
                throw new RuntimeException("type不能为null");
        }
    }

    @Override
    public void add(String redisKey, Object value, OpsType type) {
        switch (type) {
            case RIGHT:
                redisTemplate.opsForList().rightPush(redisKey, value);
                break;
            case LEFT:
                redisTemplate.opsForList().leftPush(redisKey, value);
                break;
            default:
                throw new RuntimeException("type不能为null");
        }
    }

    @Override
    public List<Object> get(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public Object update(String key, Object value, Integer index) {
        Object obj = redisTemplate.opsForList().index(key, index);
        redisTemplate.opsForList().set(key,index,value);
        return obj;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void deleteValue(String redisKey, OpsType type) {
        switch (type) {
            case RIGHT:
                redisTemplate.opsForList().rightPop(redisKey);
                break;
            case LEFT:
                redisTemplate.opsForList().leftPop(redisKey);
                break;
            default:
                throw new RuntimeException("type不能为null");
        }
    }
}

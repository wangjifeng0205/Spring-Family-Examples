package net.wangjifeng.service.impl;

import net.wangjifeng.enums.OpsType;
import net.wangjifeng.enums.RedisValueType;
import net.wangjifeng.service.RedisService;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @author: WJF
 * @date: 2020/5/24
 * @description: RedisServiceImpl
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void add(RedisValueType redisValueType, String redisKey, Object redisValue, OpsType opsType) {
        if (null == redisKey || "".equals(redisKey)) {
            throw new RuntimeException("参数redisKey不能为null或空字符串。");
        }
        if (redisValue == null) {
            throw new RuntimeException("参数redisValue不能为null。");
        }
        switch (redisValueType) {
            case VALUE:
                redisTemplate.boundValueOps(redisKey).set(redisValue);
                break;
            case HASH:
                if (redisValue instanceof Map) {
                    Map<Object, Object> map = (Map<Object, Object>) redisValue;
                    redisTemplate.boundHashOps(redisKey).putAll(map);
                } else {
                    throw new RuntimeException("当参数redisValueType为HASH时，参数redisValue必须为java.util.Map类型。");
                }
                break;
            case SET:
                redisTemplate.boundSetOps(redisKey).add(redisValue);
                break;
            case LIST:
                if (opsType == null) {
                    throw new RuntimeException("当参数redisValueType为LIST时，参数opsType不能为null。");
                }
                BoundListOperations listOperations = redisTemplate.boundListOps(redisKey);
                switch (opsType) {
                    case LEFT:
                        listOperations.leftPush(redisValue);
                        break;
                    case RIGHT:
                        listOperations.rightPush(redisValue);
                        break;
                    default:
                        throw new RuntimeException("当参数redisValueType为LIST时，参数opsType不能为null。");
                }
                break;
            case SORTED_SET:
                Set<ZSetOperations.TypedTuple<Object>> tupleSet;
                if (redisValue instanceof Set) {
                    Set set = (Set) redisValue;
                    for (Object obj : set) {
                        if (obj instanceof ZSetOperations.TypedTuple) {
                            break;
                        } else {
                            throw new RuntimeException("当参数redisValueType为SORTED_SET时，参数redisValue必须为java.util.Set<org.springframework.data.redis.core.ZSetOperations.TypedTuple<T>>类型。");
                        }
                    }
                    tupleSet = (Set<ZSetOperations.TypedTuple<Object>>) set;
                } else {
                    throw new RuntimeException("当参数redisValueType为SORTED_SET时，参数redisValue必须为java.util.Set<org.springframework.data.redis.core.ZSetOperations.TypedTuple<T>>类型。");
                }
                redisTemplate.boundZSetOps(redisKey).add(tupleSet);
                break;
            case STREAM:
                throw new RuntimeException("stream比较复杂，应用场景此处不适合，后期会有专题讲解。");
            case GEO:
                throw new RuntimeException("暂不支持地图解析相关类型。");
            default:
                throw new RuntimeException("参数redisValueType不能为null。");
        }
    }
}

package net.wangjifeng.service;

import net.wangjifeng.enums.OpsType;
import net.wangjifeng.enums.RedisValueType;

/**
 * @author: WJF
 * @date: 2020/5/24
 * @description: RedisService
 */

public interface RedisService {


    /**
     * 此处使用的是redisTemplate.boundXxxxOps(),其他的服务service使用的都是redisTemplate.opsForXxxx()
     * 往redis缓存中存入数据（通用）
     * @param type：redis数据类型
     * @param redisKey：redis-key
     * @param redisValue：redis-value
     * @param opsType：添加方式（仅在参数type为LIST时传参）
     */
    public void add(RedisValueType type, String redisKey, Object redisValue, OpsType opsType);

}

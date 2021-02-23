package net.wangjifeng.enums;

/**
 * @author: WJF
 * @date: 2020/5/24
 * @description: RedisValueType
 */

public enum RedisValueType {

    VALUE("value"),
    HASH("hash"),
    LIST("list"),
    SET("set"),
    SORTED_SET("sorted_set"),
    STREAM("stream"),
    GEO("geo")

    ;

    private String idea;

    RedisValueType(String idea) {
        this.idea = idea;
    }

}

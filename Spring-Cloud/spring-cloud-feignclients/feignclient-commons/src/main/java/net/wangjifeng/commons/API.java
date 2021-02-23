package net.wangjifeng.commons;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: WJF
 * @date: 2020/7/14
 * @description: API
 */
public interface API {

    public static final String SCHEMA = "http://";

    public static final String HOST = "api-provider";

    public static final String PROJECT_PREFIX = "/provider";

    public static final String API_PREFIX = "/apiOut";

    @RequestMapping(API_PREFIX + "/api")
    public abstract String api();

    static String URL() {
        return new StringBuffer(SCHEMA).append(HOST).append(PROJECT_PREFIX).toString();
    }

}

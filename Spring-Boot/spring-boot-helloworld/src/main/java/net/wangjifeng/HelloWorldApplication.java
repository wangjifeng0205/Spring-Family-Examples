package net.wangjifeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: WJF
 * @date: 2020/5/15
 * @description: HelloWorldApplication
 */

/**
 * {@link SpringBootApplication}: 这个注解帮助你加载配置文件{@link 'application.yml'}的配置，
 *                                被这个注解标识的类就是启动类，是整个工程的入口。
 */
@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class,args);
    }

}

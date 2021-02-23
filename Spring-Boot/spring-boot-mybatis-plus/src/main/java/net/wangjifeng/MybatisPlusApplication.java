package net.wangjifeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: WJF
 * @date: 2020/6/23
 * @description: MybatisPlusApplication
 */
@SpringBootApplication
/**
 * xmlMapper包扫描，与yml中效果相同。
 */
@MapperScan(basePackages = "net.wangjifeng.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class);
    }

}

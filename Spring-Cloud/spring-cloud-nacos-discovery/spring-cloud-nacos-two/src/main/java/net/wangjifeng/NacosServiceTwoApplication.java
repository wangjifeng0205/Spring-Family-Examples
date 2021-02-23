package net.wangjifeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: WJF
 * @date: 2020/5/17
 * @description: NacosServiceTwoApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosServiceTwoApplication.class,args);
    }

}

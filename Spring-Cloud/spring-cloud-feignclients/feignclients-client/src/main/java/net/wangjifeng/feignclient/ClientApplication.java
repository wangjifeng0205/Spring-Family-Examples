package net.wangjifeng.feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: WJF
 * @date: 2020/7/15
 * @description: ClientApplication
 */
@SpringBootApplication(scanBasePackages = {"net.wangjifeng.commons", "net.wangjifeng.feignclient"})
@EnableDiscoveryClient
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}

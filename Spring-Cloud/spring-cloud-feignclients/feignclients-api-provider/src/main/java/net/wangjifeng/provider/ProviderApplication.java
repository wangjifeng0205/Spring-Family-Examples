package net.wangjifeng.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: WJF
 * @date: 2020/7/14
 * @description: ProviderApplication
 */
@SpringBootApplication(scanBasePackages = {"net.wangjifeng.commons", "net.wangjifeng.provider"})
@EnableDiscoveryClient
@EnableFeignClients
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}

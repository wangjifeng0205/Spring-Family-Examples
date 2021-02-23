package net.wangjifeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: WJF
 * @date: 2020/5/17
 * @description: NacosServiceOneApplication
 */

/**
 * {@link EnableDiscoveryClient}：开启nacos服务发现注册功能
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosServiceOneApplication.class,args);
    }

    /**
     * {@link Bean}: 将方法的返回值放入到spring容器中管理
     * {@link LoadBalanced}：开启服务负载均衡，默认是轮询制，同时使restTemplate可以使用应用名称来代替host:port的方式调用服务
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

package net.wangjifeng.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: WJF
 * @date: 2020/7/10
 * @description: ProducerApplication
 */
@SpringBootApplication
public class ProducerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    /**
     * 启动工程的时候，请先启动生产者，然后访问一下生产者，此时交换机和队列才会创建出来，
     * 然后再去启动消费者，否则消费者会报错，找不到队列。因为rabbitmq是延迟加载的。
     */

}

package net.wangjifeng.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: WJF
 * @date: 2020/9/22
 * @description: ProducerApplication
 */
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    /**
     * 请先配置好队列，再启动工程，这样才不会报错。所有实现了amqp（高级消息队列协议）的消息队列，
     * 都可以使用Spring的AmqpTemplate进行操作，就跟jdbc一样。当前使用rabbitmq，因为他实现了amqp协议。
     */

}

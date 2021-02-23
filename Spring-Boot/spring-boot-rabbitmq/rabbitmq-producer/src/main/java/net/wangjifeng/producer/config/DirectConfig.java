package net.wangjifeng.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WJF
 * @date: 2020/7/11
 * @description: DirectConfig
 */

@Configuration
public class DirectConfig {

    /**
     * direct交换机类型队列的第一个队列:directOne
     */
    public static final String DIRECT_QUEUE_ONE_NAME = "directOne";

    /**
     * direct交换机类型队列的第二个队列:directTwo
     */
    public static final String DIRECT_QUEUE_TWO_NAME = "directTwo";

    /**
     * direct类型交换机
     */
    public static final String DIRECT_EXCHANGE = "directExchange";

    /**
     * 绑定键1：匹配队列路由键为 direct.bind.one
     */
    public static final String BIND_KEY_ONE = "direct.bind.one";

    /**
     * 绑定键1：匹配队列路由键为 direct.bind.two
     */
    public static final String BIND_KEY_TWO = "direct.bind.two";

    /**
     * 队列1
     * @return Queue
     */
    @Bean(DIRECT_QUEUE_ONE_NAME)
    public Queue directOne() {
        return new Queue(DIRECT_QUEUE_ONE_NAME);
    }

    /**
     * 队列2
     * @return Queue
     */
    @Bean(DIRECT_QUEUE_TWO_NAME)
    public Queue directTwo() {
        return new Queue(DIRECT_QUEUE_TWO_NAME);
    }

    /**
     * 构建一个direct类型的交换机，durable为true代表mq重启之后依然存在。
     * @return Exchange
     */
    @Bean(DIRECT_EXCHANGE)
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE).durable(true).build();
    }

    /**
     * 声明一个绑定，绑定键为BIND_KEY_ONE
     * @param queue 队列
     * @param exchange 交换机
     * @return 将队列和交换机绑定的绑定对象
     */
    @Bean
    public Binding directBindingOne(@Qualifier(DIRECT_QUEUE_ONE_NAME) Queue queue, @Qualifier(DIRECT_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(BIND_KEY_ONE).noargs();
    }

    /**
     * 声明一个绑定，绑定键为BIND_KEY_TWO
     * @param queue 队列
     * @param exchange 交换机
     * @return 将队列和交换机绑定的绑定对象
     */
    @Bean
    public Binding directBindingTwo(@Qualifier(DIRECT_QUEUE_TWO_NAME) Queue queue, @Qualifier(DIRECT_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(BIND_KEY_TWO).noargs();
    }

}

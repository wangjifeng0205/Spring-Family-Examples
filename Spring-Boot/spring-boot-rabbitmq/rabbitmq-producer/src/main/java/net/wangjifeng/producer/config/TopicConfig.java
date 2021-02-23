package net.wangjifeng.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WJF
 * @date: 2020/7/10
 * @description: TopicConfig
 */
@Configuration
public class TopicConfig {

    /**
     * topic交换机类型队列的第一个队列:topicOne
     */
    public static final String TOPIC_QUEUE_ONE_NAME = "topicOne";

    /**
     * topic交换机类型队列的第二个队列:topicTwo
     */
    public static final String TOPIC_QUEUE_TWO_NAME = "topicTwo";

    /**
     * topic类型交换机
     */
    public static final String TOPIC_EXCHANGE = "topicExchange";

    /**
     * 绑定键1：匹配队列路由键为'topic.bind.*'，'*'代表有且仅有一个单词
     */
    public static final String BIND_KEY_ONE = "topic.bind.*";

    /**
     * 绑定键1：匹配队列路由键为'topic.bind.#'，'#'代表有0个或多个单词
     */
    public static final String BIND_KEY_TWO = "topic.bind.#";

    /**
     * 队列1
     * @return Queue
     */
    @Bean(TOPIC_QUEUE_ONE_NAME)
    public Queue topicOne() {
        return new Queue(TOPIC_QUEUE_ONE_NAME);
    }

    /**
     * 队列2
     * @return Queue
     */
    @Bean(TOPIC_QUEUE_TWO_NAME)
    public Queue topicTwo() {
        return new Queue(TOPIC_QUEUE_TWO_NAME);
    }

    /**
     * 构建一个topic类型的交换机，durable为true代表mq重启之后依然存在。
     * @return Exchange
     */
    @Bean(TOPIC_EXCHANGE)
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE).durable(true).build();
    }

    /**
     * 声明一个绑定，绑定键为BIND_KEY_ONE
     * @param queue 队列
     * @param exchange 交换机
     * @return 将队列和交换机绑定的绑定对象
     */
    @Bean
    public Binding topicBindingOne(@Qualifier(TOPIC_QUEUE_ONE_NAME) Queue queue, @Qualifier(TOPIC_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(BIND_KEY_ONE).noargs();
    }

    /**
     * 声明一个绑定，绑定键为BIND_KEY_TWO
     * @param queue 队列
     * @param exchange 交换机
     * @return 将队列和交换机绑定的绑定对象
     */
    @Bean
    public Binding topicBindingTwo(@Qualifier(TOPIC_QUEUE_TWO_NAME) Queue queue, @Qualifier(TOPIC_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(BIND_KEY_TWO).noargs();
    }

}

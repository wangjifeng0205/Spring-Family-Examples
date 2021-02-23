package net.wangjifeng.producer.config;

import net.wangjifeng.producer.constants.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WJF
 * @date: 2020/7/10
 * @description: FanoutConfig
 */
@Configuration
public class FanoutConfig {

    /**
     * fanout交换机类型队列的第一个队列:fanoutOne
     */
    public static final String FANOUT_QUEUE_ONE_NAME = "fanoutOne";

    /**
     * fanout交换机类型队列的第二个队列:fanoutTwo
     */
    public static final String FANOUT_QUEUE_TWO_NAME = "fanoutTwo";

    /**
     * fanout类型交换机
     */
    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    /**
     * 队列1
     * @return Queue
     */
    @Bean(FANOUT_QUEUE_ONE_NAME)
    public Queue fanoutOne() {
        return new Queue(FANOUT_QUEUE_ONE_NAME);
    }

    /**
     * 队列2
     * @return Queue
     */
    @Bean(FANOUT_QUEUE_TWO_NAME)
    public Queue fanoutTwo() {
        return new Queue(FANOUT_QUEUE_TWO_NAME);
    }

    /**
     * 构建一个fanout类型的交换机，durable为true代表mq重启之后依然存在。
     * @return Exchange
     */
    @Bean(FANOUT_EXCHANGE)
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE).durable(true).build();
    }

    /**
     *
     * @param queue 队列
     * @param exchange 交换机
     * @return 将队列和交换机绑定的绑定对象
     */
    @Bean
    public Binding fanoutBindingOne(@Qualifier(FANOUT_QUEUE_ONE_NAME) Queue queue, @Qualifier(FANOUT_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constants.EMPTY_STRING).noargs();
    }

    /**
     *
     * @param queue 队列
     * @param exchange 交换机
     * @return 将队列和交换机绑定的绑定对象
     */
    @Bean
    public Binding fanoutBindingTwo(@Qualifier(FANOUT_QUEUE_TWO_NAME) Queue queue, @Qualifier(FANOUT_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constants.EMPTY_STRING).noargs();
    }


}

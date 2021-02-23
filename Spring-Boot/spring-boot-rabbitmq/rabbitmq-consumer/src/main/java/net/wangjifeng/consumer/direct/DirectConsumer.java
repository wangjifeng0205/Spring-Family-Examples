package net.wangjifeng.consumer.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: WJF
 * @date: 2020/7/11
 * @description: DirectConsumer
 */

@Component
public class DirectConsumer {

    /**
     * direct交换机类型队列的第一个队列:directOne
     */
    public static final String DIRECT_QUEUE_ONE_NAME = "directOne";

    /**
     * direct交换机类型队列的第二个队列:directTwo
     */
    public static final String DIRECT_QUEUE_TWO_NAME = "directTwo";

    @RabbitListener(queues = DIRECT_QUEUE_ONE_NAME)
    public void consumerOneTopic(String message, Channel channel) {
        System.out.println("One:" + message);
        System.out.println(channel);
    }

    @RabbitListener(queues = DIRECT_QUEUE_TWO_NAME)
    public void consumerTwoTopic(String message, Channel channel) {
        System.out.println("Two:" + message);
        System.out.println(channel);
    }

    /**
     * direct交换机相当于路由完全匹配的发送消息，跟这个交换机绑定的所有队列都会根据路由键和绑定键的完全匹配来接收到此交换机转发的消息。
     */

}

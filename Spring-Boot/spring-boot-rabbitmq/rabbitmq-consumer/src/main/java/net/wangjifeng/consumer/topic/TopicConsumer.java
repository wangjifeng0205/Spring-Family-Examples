package net.wangjifeng.consumer.topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: WJF
 * @date: 2020/7/11
 * @description: TopicConsumer
 */
@Component
public class TopicConsumer {

    /**
     * topic交换机类型队列的第一个队列:topicQueueOne
     */
    public static final String TOPIC_QUEUE_ONE_NAME = "topicOne";

    /**
     * topic交换机类型队列的第二个队列:topicQueueTwo
     */
    public static final String TOPIC_QUEUE_TWO_NAME = "topicTwo";

    @RabbitListener(queues = TOPIC_QUEUE_ONE_NAME)
    public void consumerOneTopic(String message, Channel channel) {
        System.out.println("One:" + message);
        System.out.println(channel);
    }

    @RabbitListener(queues = TOPIC_QUEUE_TWO_NAME)
    public void consumerTwoTopic(String message, Channel channel) {
        System.out.println("Two:" + message);
        System.out.println(channel);
    }

    /**
     * topic交换机相当于路由匹配的发送消息，跟这个交换机绑定的所有队列都会根据路由键和绑定键的匹配关系来接收到此交换机转发的消息。
     * topic交换机支持通配符
     */

}

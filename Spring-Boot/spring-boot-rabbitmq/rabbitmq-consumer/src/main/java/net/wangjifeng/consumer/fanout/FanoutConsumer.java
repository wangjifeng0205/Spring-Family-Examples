package net.wangjifeng.consumer.fanout;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: WJF
 * @date: 2020/7/10
 * @description: FanoutConsumer
 */
@Component
public class FanoutConsumer {

    /**
     * fanout交换机类型队列的第一个队列:fanoutOne
     */
    private static final String FANOUT_QUEUE_ONE_NAME = "fanoutOne";

    /**
     * fanout交换机类型队列的第二个队列:fanoutTwo
     */
    private static final String FANOUT_QUEUE_TWO_NAME = "fanoutTwo";

    /**
     * 监听队列FANOUT_QUEUE_ONE_NAME，获取队列发送的消息
     * @param message 消息生产者发送的消息
     * @param channel 消息通道
     */
    @RabbitListener(queues = FANOUT_QUEUE_ONE_NAME)
    public void consumerOneFanout(String message, Channel channel) {
        System.out.println("One:" + message);
        System.out.println(channel);
    }

    /**
     * 监听队列FANOUT_QUEUE_ONE_NAME，获取队列发送的消息
     * @param message 消息生产者发送的消息
     * @param channel 消息通道
     */
    @RabbitListener(queues = FANOUT_QUEUE_TWO_NAME)
    public void consumerTwoFanout(String message, Channel channel) {
        System.out.println("One:" + message);
        System.out.println(channel);
    }

    /**
     * fanout交换机相当于广播式的发送消息，跟这个交换机绑定的所有队列都会接收到此交换机转发的消息。
     */

}

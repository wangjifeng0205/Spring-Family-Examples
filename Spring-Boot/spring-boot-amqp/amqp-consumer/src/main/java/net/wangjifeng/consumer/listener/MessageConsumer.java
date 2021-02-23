package net.wangjifeng.consumer.listener;

import net.wangjifeng.consumer.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: WJF
 * @date: 2020/9/26
 * @description: MessageConsumer
 */
@Component
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues = Constants.TOPIC_ONE)
    public void messageOne(String message) {
        log.info("One: {}", message);
    }

    @RabbitListener(queues = Constants.TOPIC_TWO)
    public void messageTwo(String message) {
        log.info("Two: {}", message);
    }

    public void handlerOne(String message) {
        log.info("messageOne: {}", message);
    }

    public void handlerTwo(String message) {
        log.info("messageTwo: {}", message);
    }

}

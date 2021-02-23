package net.wangjifeng.producer.topic;

import net.wangjifeng.producer.config.TopicConfig;
import net.wangjifeng.producer.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/7/10
 * @description: TopicProducer
 */
@RestController
@RequestMapping("/topic")
public class TopicProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @RequestMapping("/topicOne/{message}")
    public String topicOne(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, Constants.routingKeyOneTopic, message);
        return "success One";
    }

    @RequestMapping("/topicTwo/{message}")
    public String topicTwo(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, Constants.routingKeyTwoTopic, message);
        return "success Two";
    }

    /**
     * 绑定键和路由键其实是同一个东西，只是为了好理解才区分别开来的，由路由键匹配绑定键来控制哪些队列接收消息，
     * 哪些队列不应该接收消息。
     */

}

package net.wangjifeng.producer.direct;

import net.wangjifeng.producer.config.DirectConfig;
import net.wangjifeng.producer.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/7/11
 * @description: DirectProducer
 */

@RestController
@RequestMapping("/direct")
public class DirectProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/directOne/{message}")
    public String topicOne(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, Constants.routingKeyOneDirect, message);
        return "success One";
    }

    @RequestMapping("/directTwo/{message}")
    public String topicTwo(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, Constants.routingKeyTwoDirect, message);
        return "success Two";
    }

    /**
     * 绑定键和路由键其实是同一个东西，只是为了好理解才区分别开来的，由路由键匹配绑定键来控制哪些队列接收消息，
     * 哪些队列不应该接收消息。
     */

}

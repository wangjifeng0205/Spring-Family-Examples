package net.wangjifeng.producer.controller;

import net.wangjifeng.producer.entity.AmqpDef;
import net.wangjifeng.producer.entity.ExchangeDef;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: WJF
 * @date: 2020/9/26
 * @description: ProducerController
 */
@RequestMapping("/producer")
@RestController
public class ProducerController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AmqpDef amqpDef;

    /**
     * routingKey为topic-one发送消息到队列topic-one
     * routingKey为topic-two发送消息到队列topic-two
     * @param routingKey
     * @param message
     */
    @RequestMapping("/send/{routingKey}/{message}")
    public String send(@PathVariable("routingKey") String routingKey, @PathVariable("message") String message) {
        ExchangeDef exchangeDef = amqpDef.getExchangeDefs().get(0);
        amqpTemplate.convertAndSend(exchangeDef.getExchangeName(), routingKey, message);
        return "success!";
    }

}

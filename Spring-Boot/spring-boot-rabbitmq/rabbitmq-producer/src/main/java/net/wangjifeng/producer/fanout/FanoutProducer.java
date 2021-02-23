package net.wangjifeng.producer.fanout;

import net.wangjifeng.producer.config.FanoutConfig;
import net.wangjifeng.producer.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/7/10
 * @description: FanoutProducer
 */
@RestController
@RequestMapping("/fanout")
public class FanoutProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 利用rabbitmq的fanout交换机的特性发送消息，与之绑定的所有队列都会接收到消息。
     * @param message 生产数据
     * @return String
     */
    @RequestMapping("/producer/{message}")
    public String producer(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE, Constants.EMPTY_STRING, message);
        return "success";
    }

}

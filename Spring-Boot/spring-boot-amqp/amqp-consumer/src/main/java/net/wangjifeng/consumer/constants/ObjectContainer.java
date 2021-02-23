package net.wangjifeng.consumer.constants;

import net.wangjifeng.consumer.listener.MessageConsumer;
import net.wangjifeng.consumer.utils.AmqpUtils;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: WJF
 * @date: 2020/9/27
 * @description: ObjectContainer
 */
@Component
public class ObjectContainer {

    @Bean
    public MessageConverter messageConverter() {
        return AmqpUtils.messageConverter(AmqpUtils.ConverterType.STRING);
    }

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private MessageConsumer messageConsumer;

    @PostConstruct
    public void init() {
        AmqpUtils.initQueueListener(messageConsumer, "handlerOne", messageConverter, connectionFactory, Constants.TOPIC_ONE);
        AmqpUtils.initQueueListener(messageConsumer, "handlerTwo", messageConverter, connectionFactory, Constants.TOPIC_TWO);
    }

}

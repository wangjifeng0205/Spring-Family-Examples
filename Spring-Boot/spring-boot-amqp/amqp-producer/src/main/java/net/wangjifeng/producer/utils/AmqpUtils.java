package net.wangjifeng.producer.utils;

import net.wangjifeng.producer.entity.AmqpDef;
import net.wangjifeng.producer.entity.ExchangeDef;
import net.wangjifeng.producer.entity.QueueDef;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.util.List;
import java.util.Objects;

/**
 * @author: WJF
 * @date: 2020/9/26
 * @description: AmqpUtils
 */
@Slf4j
public final class AmqpUtils {

    private static final String TOPIC = "topic";
    private static final String FANOUT = "fanout";
    private static final String DIRECT = "direct";
    private static final String HEADER = "header";

    private AmqpUtils() {}

    public static void init(AmqpDef amqpDef, AmqpAdmin amqpAdmin) {
        log.info(String.format("AMQP配置 ---> %s 初始化开始！", amqpDef.getAmqpDescription()));
        List<ExchangeDef> exchangeDefs = amqpDef.getExchangeDefs();
        initExchanges(exchangeDefs, amqpAdmin);
        log.info(String.format("AMQP配置 ---> %s 初始化完成！", amqpDef.getAmqpDescription()));
    }

    private static void initExchanges(List<ExchangeDef> exchangeDefs, AmqpAdmin amqpAdmin) {
        if (CollectionUtils.isNotEmpty(exchangeDefs)) {
            exchangeDefs.forEach(exchangeDef -> {
                Exchange exchange = null;
                if (TOPIC.equalsIgnoreCase(exchangeDef.getExchangeType())) {
                    exchange = ExchangeBuilder.topicExchange(exchangeDef.getExchangeName()).durable(exchangeDef.getDurable()).build();
                } else if (FANOUT.equalsIgnoreCase(exchangeDef.getExchangeType())) {
                    exchange = ExchangeBuilder.fanoutExchange(exchangeDef.getExchangeName()).durable(exchangeDef.getDurable()).build();
                } else if (DIRECT.equalsIgnoreCase(exchangeDef.getExchangeType())) {
                    exchange = ExchangeBuilder.directExchange(exchangeDef.getExchangeName()).durable(exchangeDef.getDurable()).build();
                } else if (HEADER.equalsIgnoreCase(exchangeDef.getExchangeType())) {
                    exchange = ExchangeBuilder.headersExchange(exchangeDef.getExchangeName()).durable(exchangeDef.getDurable()).build();
                } else {
                    throw new IllegalArgumentException("Unsupported exchange type");
                }
                amqpAdmin.declareExchange(exchange);
                initQueue(exchange, exchangeDef.getQueueDefs(), amqpAdmin);
            });
        }
    }

    private static void initQueue(Exchange exchange, List<QueueDef> queueDefs, AmqpAdmin amqpAdmin) {
        if (CollectionUtils.isNotEmpty(queueDefs)) {
            queueDefs.forEach(queueDef -> {
                Queue queue = new Queue(queueDef.getQueueName(), queueDef.getDurable(), false, false);
                amqpAdmin.declareQueue(queue);
                Binding binding = null;
                if (exchange instanceof FanoutExchange) {
                    binding = BindingBuilder.bind(queue).to((FanoutExchange) exchange);
                } else {
                    binding = BindingBuilder.bind(queue).to(exchange).with(queueDef.getRoutingKey()).noargs();
                }
                amqpAdmin.declareBinding(binding);
            });
        }
    }

    public static <T> MessageListenerContainer initQueueListener(T listener, String listenerMethodName, MessageConverter messageConverter, ConnectionFactory connectionFactory, String... queueNames) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        if (Objects.isNull(messageConverter)) {
            adapter.setMessageConverter(messageConverter(ConverterType.STRING));
        } else {
            adapter.setMessageConverter(messageConverter);
        }
        adapter.setDefaultListenerMethod(listenerMethodName);
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNames);
        container.setMessageListener(adapter);
        container.start();
        return container;
    }

    public static MessageConverter messageConverter(ConverterType converterType) {
        MessageConverter messageConverter = null;
        switch (converterType) {
            case JSON:
                messageConverter = new Jackson2JsonMessageConverter();
                break;
            case STRING:
            default:
                messageConverter = new SimpleMessageConverter();
                break;
        }
        return messageConverter;
    }

    public enum ConverterType {
        JSON,
        STRING;

        ConverterType() {}
    }

}

package net.wangjifeng.producer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: WJF
 * @date: 2020/9/26
 * @description: QueueDef
 */
@Getter
@Setter
@ToString
public class QueueDef {

    private Long id;

    private String queueName;

    private Boolean durable;

    private String queueDescription;

    private String routingKey;

}

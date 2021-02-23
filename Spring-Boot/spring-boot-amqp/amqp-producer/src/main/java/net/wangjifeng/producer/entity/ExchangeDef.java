package net.wangjifeng.producer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/9/26
 * @description: ExchangeDef
 */
@Getter
@Setter
@ToString
public class ExchangeDef {

    private Long id;

    private String exchangeName;

    private String exchangeType;

    private Boolean durable;

    private String exchangeDescription;

    private List<QueueDef> queueDefs;

}

package net.wangjifeng.producer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author: WJF
 * @date: 2020/9/22
 * @description: AmqpDef
 */
@Getter
@Setter
@ToString
public class AmqpDef {

    private String amqpDescription;

    private List<ExchangeDef> exchangeDefs;

}

package net.wangjifeng.producer.config;

import net.wangjifeng.producer.entity.AmqpDef;
import net.wangjifeng.producer.utils.AmqpUtils;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


/**
 * @author: WJF
 * @date: 2020/9/22
 * @description: AmqpConfig
 */
@Configuration
@ConditionalOnMissingBean(AmqpDef.class)
public class AmqpConfig {

    @ConfigurationProperties(prefix = "net.wangjifeng.amqp-def")
    @Bean
    public AmqpDef amqpDef() {
        return new AmqpDef();
    }

    @Autowired
    private AmqpDef amqpDef;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    @Autowired
    public void init() {
        AmqpUtils.init(amqpDef, amqpAdmin);
    }

}

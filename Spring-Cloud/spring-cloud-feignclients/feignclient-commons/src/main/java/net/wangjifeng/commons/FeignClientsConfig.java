package net.wangjifeng.commons;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WJF
 * @date: 2020/7/15
 * @description: FeignClientsConfig
 */
@Configuration
public class FeignClientsConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    public FeignClientsConfig() {
    }

    @Bean
    public Decoder decoder() {
        return new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
    }

    @Bean
    public Encoder encoder() {
        return new SpringEncoder(this.messageConverters);
    }

}

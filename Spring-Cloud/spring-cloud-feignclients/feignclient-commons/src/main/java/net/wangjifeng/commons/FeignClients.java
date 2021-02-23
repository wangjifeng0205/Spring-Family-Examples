package net.wangjifeng.commons;

import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author: WJF
 * @date: 2020/7/14
 * @description: FeignClients
 */
@Component
@Import(FeignClientsConfig.class)
public class FeignClients {

    private API api;

    @Autowired
    public FeignClients(Decoder decoder, Encoder encoder, Client client) {
        this.api = Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(new SpringMvcContract())
                .target(API.class, API.URL());
    }

    public API getApi() {
        return api;
    }

}

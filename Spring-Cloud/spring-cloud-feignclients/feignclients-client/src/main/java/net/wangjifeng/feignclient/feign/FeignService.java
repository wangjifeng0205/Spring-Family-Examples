package net.wangjifeng.feignclient.feign;

import net.wangjifeng.commons.FeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: WJF
 * @date: 2020/7/15
 * @description: FeignService
 */
@Component
public class FeignService {

    @Autowired
    private FeignClients feignClients;

    public String api() {
        return feignClients.getApi().api();
    }

}

package net.wangjifeng.provider.controller;

import net.wangjifeng.commons.API;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WJF
 * @date: 2020/7/14
 * @description: APIController
 */
@RestController
public class APIController implements API {

    @Override
    public String api() {
        return "Hello Feign!";
    }

}

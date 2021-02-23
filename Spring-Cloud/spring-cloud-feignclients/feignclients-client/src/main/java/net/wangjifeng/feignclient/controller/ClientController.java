package net.wangjifeng.feignclient.controller;

import net.wangjifeng.feignclient.feign.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/7/15
 * @description: ClientController
 */

@RestController
@RequestMapping("/client")
public class ClientController {

    @Resource
    private FeignService feignService;

    @GetMapping("/api")
    public String api() {
        return feignService.api();
    }

}

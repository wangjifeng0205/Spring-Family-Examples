package net.wangjifeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: WJF
 * @date: 2020/5/17
 * @description: NacosServiceOneController
 */
@RestController
@RequestMapping("/nacosServiceOne")
public class NacosServiceOneController {

    private static final String NACOS_SERVICE_TWO_URL = "http://nacos-discovery-two/nacosServiceTwo/go";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/go")
    public String go() {
        return restTemplate.getForObject(NACOS_SERVICE_TWO_URL,String.class);
    }

}

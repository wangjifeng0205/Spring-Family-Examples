package net.wangjifeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WJF
 * @date: 2020/5/17
 * @description: NacosServiceTwoController
 */
@RestController
@RequestMapping("/nacosServiceTwo")
public class NacosServiceTwoController {

    private static final String GO = "Hello NacosServiceOne!";

    @RequestMapping("/go")
    public String go() {
        return GO;
    }

}

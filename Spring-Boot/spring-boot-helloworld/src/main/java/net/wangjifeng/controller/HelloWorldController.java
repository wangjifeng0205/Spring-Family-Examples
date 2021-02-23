package net.wangjifeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WJF
 * @date: 2020/5/15
 * @description: Hello World!
 */
@RestController
@RequestMapping("/helloWorld")
public class HelloWorldController {

    private static final String HELLO_WORLD = "Hello World!";


    /**
     * 运行项目，访问：http://localhost:8080/helloWorld/go 即可看到hello world！
     * @return String
     */
    @RequestMapping("/go")
    public String go() {
        return HELLO_WORLD;
    }

}

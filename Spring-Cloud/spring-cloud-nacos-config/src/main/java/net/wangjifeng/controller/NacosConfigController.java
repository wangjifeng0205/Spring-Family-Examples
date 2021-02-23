package net.wangjifeng.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WJF
 * @date: 2020/5/23
 * @description: NacosConfigController
 */

/**
 * {@link RefreshScope}：自动刷新在nacos服务器上配置的配置文件。
 * {@link Value}：装载在nacos服务器配置的配置文件的变量，也可以
 * 使用{@link NacosValue}注解实现同样效果。
 * 我已经将nacos上的配置文件放置在resource目录下方。
 */
@RefreshScope
@RestController
@RequestMapping("/nacosConfig")
public class NacosConfigController {

    @Value(value = "${net.wangjifeng}")
    private Boolean flag;

    private static final String WANG_JI_FENG = "http://wangjifeng.net";

    private static final String NACOS_CONFIG = "nacos config";

    @RequestMapping("/go")
    public String go() {
        if (null != flag && flag) {
            return WANG_JI_FENG;
        }
        return NACOS_CONFIG;
    }

}

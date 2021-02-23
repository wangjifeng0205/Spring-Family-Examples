package net.wangjifeng.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WJF
 * @date: 2020/5/22
 * @description: ElasticSearchConfig
 */
@Configuration
public class ElasticSearchConfig implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

    /**
     * {@link FactoryBean<T>}：FactoryBean<T>是spring对外提供的对接接口，当向spring对象使用getBean("..")方法时，
     *                         spring会使用FactoryBean<T>的getObject 方法返回对象。所以当一个类实现的factoryBean<T>接口时，
     *                         那么每次向spring要这个类时，spring就返回T对象。
     *
     * {@link InitializingBean}：InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，
     *                          凡是继承该接口的类，在初始化bean的时候会执行该方法。在spring初始化bean的时候，如果该bean是
     *                          实现了InitializingBean接口，并且同时在配置文件中指定了init-method，系统则是
     *                          先调用afterPropertiesSet方法，然后在调用init-method中指定的方法。
     *
     * {@link DisposableBean}：DisposableBean接口为bean提供了销毁方法destroy-method，会在程序关闭前销毁对象。
     */

    @Value("#{'${net.wangjifeng.host}'.split(':')}")
    private String[] host;

    private RestHighLevelClient restHighLevelClient;

    private RestHighLevelClient restHighLevelClient() {
        restHighLevelClient = new RestHighLevelClient(

                RestClient.builder(new HttpHost(host[0],Integer.valueOf(host[1]),"http"))

        );
        return restHighLevelClient;
    }


    @Override
    public void destroy() throws Exception {
        restHighLevelClient.close();
    }

    @Override
    public RestHighLevelClient getObject() throws Exception {
        return restHighLevelClient;
    }

    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        restHighLevelClient();
    }

}

package net.wangjifeng.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @author: WJF
 * @date: 2020/9/29
 * @description: EsConfig
 */
@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

    @Value("#{'${net.wangjifeng.host}'.split(':')}")
    private String[] host;

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }

    @Override
    public RestHighLevelClient elasticsearchClient() {
        return new RestHighLevelClient(

                RestClient.builder(new HttpHost(host[0],Integer.valueOf(host[1]),"http"))

        );
    }
}

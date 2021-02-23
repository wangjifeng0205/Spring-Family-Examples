package net.wangjifeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import red.wjf.download.downloader.ImageDownloader;
import red.wjf.download.downloader.WebDownloader;

/**
 * @author: WJF
 * @date: 2020/7/29
 * @description:  DownloadConfig
 */

@Configuration
public class DownloadConfig {

    @Bean
    public WebDownloader webDownloader() {
        return new WebDownloader();
    }

    @Bean
    public ImageDownloader imageDownloader() {
        return new ImageDownloader();
    }

}

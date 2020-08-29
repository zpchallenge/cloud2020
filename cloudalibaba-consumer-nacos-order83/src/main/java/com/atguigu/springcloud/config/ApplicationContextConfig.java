package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangpeng
 * @create 2020/8/22 17:22
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //开启轮循
    public RestTemplate restTemplate(){
        return new RestTemplate ();
    }
}

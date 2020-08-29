package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangpeng
 * @create 2020/8/22 17:23
 */
@RestController
public class OrderNacosController {
    @Autowired
    private RestTemplate restTemplate;

    @Value ("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/payment/nacos/lb")
    public String lb(){
        return restTemplate.getForObject (serverURL+"/payment/nacos/lb", String.class);
    }
}

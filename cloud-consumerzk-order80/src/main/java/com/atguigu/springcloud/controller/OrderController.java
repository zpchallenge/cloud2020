package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    private final static String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/consumer/order/zk")
    public String orderZk(){
        return restTemplate.getForObject (INVOKE_URL + "/payment/zk", String.class);
    }

}

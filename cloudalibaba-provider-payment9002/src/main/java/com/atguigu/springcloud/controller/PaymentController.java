package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpeng
 * @create 2020/8/22 16:58
 */
@RestController
public class PaymentController {
    @Value ("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/lb")
    public String lb(){
        return "nacos discovery  O(∩_∩)O哈哈~ ....  port："+serverPort;
    }

}

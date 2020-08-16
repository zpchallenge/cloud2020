package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    /**
     * 服务发现  获取服务信息
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        return paymentService.create (payment);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentService.getPaymentById (id);
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices ();
        for (String service : services) {
            log.info ("****service:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances ("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info (instance.getServiceId () + "\t" + instance.getHost () + "\t" + instance.getPort () + "\t" + instance.getUri ());
        }

        return discoveryClient;
    }

    @GetMapping("/payment/timeout")
    public String testFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep (3);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        return "8001";
    }
}

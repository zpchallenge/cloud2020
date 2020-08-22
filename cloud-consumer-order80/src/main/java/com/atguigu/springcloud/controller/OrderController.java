package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@EnableEurekaClient
public class OrderController {

//    private final static String PAYMENT_URL = "http://localhost:8001";//单机
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";//集群

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment/create")
    public CommonResult<RestTemplate> create(Payment payment){
        log.info ("****获取的数据"+payment);
        return restTemplate.postForObject (PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        log.info ("****消费者端");
        return restTemplate.getForObject (PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/getForEntity/payment/get/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Long id){
        log.info ("****消费者端");
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity (PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        //ResponseEntity：包含了响应头、响应状态码、响应体等
        if (entity.getStatusCode ().is2xxSuccessful ()){
            log.info ("entity中包含的头信息"+entity.getHeaders ());
            return entity.getBody ();
        } else {
            return new CommonResult<> (404,"访问失败！");
        }
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }
}

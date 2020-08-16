package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

//服务降级处理 当服务端关闭或者宕机会执行这个实现类对应的方法
@Component
public class PaymentFallBackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallBackService... paymentInfo_OK   O(∩_∩)O";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallBackService...paymentInfo_TimeOut";
    }
}

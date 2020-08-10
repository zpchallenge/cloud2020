package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {

    public CommonResult create(Payment payment);

    public CommonResult getPaymentById(Long id);
}

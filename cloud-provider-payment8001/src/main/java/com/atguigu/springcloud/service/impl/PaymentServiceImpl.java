package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public CommonResult create(Payment payment) {

        int value = paymentDao.create (payment);
        CommonResult commonResult = null;

        log.info ("*********插入返回值："+value);

        if (value > 0){
            commonResult = new CommonResult (200, "插入成功，serverPort="+serverPort, value);
        } else {
            commonResult = new CommonResult (444, "插入失败", null);
        }

        return commonResult;
    }

    @Override
    public CommonResult getPaymentById(Long id) {
        Payment payment = paymentDao.getPaymentById (id);

        log.info ("*********查出的结果："+payment);
        CommonResult commonResult = null;

        if (payment != null){
            commonResult = new CommonResult (200, "查询数据成功! serverPort="+serverPort, payment);
        } else {
            commonResult = new CommonResult (444, "没有对应的记录，查询id："+id, null);
        }

        return commonResult;
    }
}

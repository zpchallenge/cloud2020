package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zzyy
 * @create 2020/3/6 22:23
 **/
@Service
@Slf4j
public class PaymentService {
    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            //paymentInfo_TimeOut运行超过3秒就执行fallbackMethod指定的方法
            @HystrixProperty (name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber =3;
        try {
            // 暂停3秒钟
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        log.info ("paymentInfo_TimeOut执行了，id:"+id);
//        int i = 5/0;

        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "\t" +
                "O(∩_∩)O哈哈~  耗时(秒)" + timeNumber;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {

        return "调用支付接口异常，当前线程名字："+Thread.currentThread ().getName () + "  id："+id;
    }


    //=======服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack", commandProperties = {
        @HystrixProperty (name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
        @HystrixProperty (name = "circuitBreaker.requestVolumeThreshold", value = "10"),  //请求次数
        @HystrixProperty (name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  //时间窗口期
        @HystrixProperty (name = "circuitBreaker.errorThresholdPercentage", value = "60")  //失败率达到多少后跳闸（open断路器）
            //上面配置含义：当在10s中的请求次数在中达到6次错误会打开断路器。断开后，正确的访问也不能进行。
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw  new RuntimeException ("***********id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID ();

        return Thread.currentThread ().getName () + "\t" + "调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreakerFallBack(Integer id){
        return "id 不能为负数，请稍后再试，o(╥﹏╥)o   id："+id;
    }


}
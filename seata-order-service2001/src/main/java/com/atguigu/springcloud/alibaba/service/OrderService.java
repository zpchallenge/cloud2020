package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.Order;

/**
 * @author zhangpeng
 * @create 2020/8/30 22:53
 */
public interface OrderService {
    //创建订单
    void create(Order order);
}

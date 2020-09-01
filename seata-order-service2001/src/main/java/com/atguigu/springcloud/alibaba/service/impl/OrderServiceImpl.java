package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangpeng
 * @create 2020/8/30 22:53
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    //下订单过程   下订单 --》 减库存 --》减余额 --》该状态
    @Override
    @GlobalTransactional(name = "fcp_create", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info ("======创建新订单start======");
        orderDao.create (order);

        log.info ("======微服务开始调用库存，减库存start======");
        storageService.decrease (order.getProductId (), order.getCount ());
        log.info ("======微服务开始调用库存，减库存end======");

        log.info ("======微服务开始调用账户，减余额start======");
        accountService.decrease (order.getUserId (), order.getMoney ());
        log.info ("======微服务开始调用账户，减余额end======");

        //修改订单状态 为1
        log.info ("======修改订单start======");
        orderDao.updateStatus (order.getUserId (), 0);
        log.info ("======修改订单end======");

        log.info ("======下订单end O(∩_∩)O哈哈~======");
    }
}

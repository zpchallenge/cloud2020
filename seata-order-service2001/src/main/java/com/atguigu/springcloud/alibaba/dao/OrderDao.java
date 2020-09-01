package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangpeng
 * @create 2020/8/30 22:45
 */
@Mapper
public interface OrderDao {
    //创建订单
    void create(Order order);
    //修改状态
    void updateStatus(@Param ("userId") Long userId, @Param ("status") Integer status);
}

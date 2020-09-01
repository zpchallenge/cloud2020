package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author zhangpeng
 * @create 2020/8/31 21:36
 */
@Mapper
public interface StorageDao {
    //减库存
    void decrease(@Param ("productId") Long productId, @Param ("count") Integer count);
}

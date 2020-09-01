package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author zhangpeng
 * @create 2020/8/31 21:40
 */
@Mapper
public interface AccountDao {
    //扣余额
    void decrease(@Param("userId") Long userId, @Param ("money") BigDecimal money);
}

package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.AccountDao;
import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpeng
 * @create 2020/8/31 21:49
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info ("======seata-account-service减余额start======");
        //超时操作 测试分布式事务  seata的注解 @GlobalTransactional  分布式事务控制
        try {
            TimeUnit.SECONDS.sleep (20);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        accountDao.decrease (userId, money);
        log.info ("======seata-account-service减余额end======");
    }
}

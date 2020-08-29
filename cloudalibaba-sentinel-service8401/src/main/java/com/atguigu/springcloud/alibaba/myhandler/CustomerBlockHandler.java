package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @author zhangpeng
 * @create 2020/8/29 17:18
 */
//自定义全局限流处理类
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult (444,"按照客户自定义 global handlerException ==== 1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult (444,"按照客户自定义 global handlerException ==== 2");
    }
}

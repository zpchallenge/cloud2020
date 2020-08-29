package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpeng
 * @create 2020/8/29 17:00
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "byResourceHandler")
    public CommonResult byResource() {
        return new CommonResult (200, "按照资源名称限流测试OK ^_^", new Payment (1001L, "serial2001"));
    }

    public CommonResult byResourceHandler(BlockException e) {
        return new CommonResult (444, e.getClass ().getCanonicalName () + "\t 服务不可以");
    }

    @GetMapping("/rate/byUrl")
    @SentinelResource(value = "byUrl")  //没有兜底方法 按照sentinel默认规则   Blocked by Sentinel (flow limiting)
    public CommonResult byUrl() {
        return new CommonResult (200, "按照URL地址限流测试OK ^_^", new Payment (1002L, "serial2002"));
    }

    //上面方法缺点
    // 1.系统默认 没有提现我们自己的业务
    // 2.自定义的处理方法和业务代码耦合在一起 不直观
    // 3.每个业务方法一个兜底方法  那代码加剧膨胀
    // 4.全局统一的处理方法没有提现

    /**
     * @SentinelResource：用于指定降级和限流处理规则
     *      value：资源名称
     *      blockHandlerClass：指定兜底统一处理类
     *      blockHandler：指定限流兜底方法  与blockHandlerClass一起用 指的就是blockHandlerClass中的方法  否则指的是本类方法
     *      fallback：指定业务代码异常降级方法
     *      exceptionsToIgnore：配置指定的异常不会做fallback降级处理
     */
    @GetMapping("/rate/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")  //没有兜底方法 按照sentinel默认规则   Blocked by Sentinel (flow limiting)
    public CommonResult customerBlockHandler() {
        return new CommonResult (200, "按照用户自定义限流测试OK ^_^", new Payment (1003L, "serial2003"));
    }
}

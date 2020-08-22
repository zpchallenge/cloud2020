package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author zhangpeng
 * @create 2020/8/22 12:26
 */
@EnableBinding(Source.class)  //定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output;  //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID ().toString ();
        output.send (MessageBuilder.withPayload (serial).build ());
        log.info ("序列号：{}", serial);
        return serial;
    }
}

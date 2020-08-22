package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author zhangpeng
 * @create 2020/8/22 13:17
 */
@Component
@EnableBinding(Sink.class) //定义消息的接收管道
@Slf4j
public class ReceiveMessageListenerController {
    @Value ("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)  //监听队列 用于消费者的队列的消息接收
    public void receive(Message<String> message){
        String messagePayload = message.getPayload ();
        log.info ("消费者1：=====》接受的消息："+messagePayload+"\t port："+serverPort);
    }
}

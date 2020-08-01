/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package site.zhangsun.utility.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.zhangsun.utility.mq.config.QueueConfig;


/**
 * @author ：Murphy ZhangSun
 * @version ：1
 * @description ：FirstConsumer
 * @program ：vevor_demo
 * @date ：Created in 2020/7/1 13:50
 */
@Component
public class FirstConsumer {

    @RabbitListener(queues = {QueueConfig.FIRST_QUEUE},
            containerFactory = "rabbitListenerContainerFactory")
    public void handleDirectMessage1(String message) throws Exception {
        // 处理消息
        System.out.println("FIRST_QUEUE {} handleMessage :"+message);
    }

    @RabbitListener(queues = {QueueConfig.SECOND_QUEUE},
            containerFactory = "rabbitListenerContainerFactory")
    public void handleTopicMessage2(String message) throws Exception {
        // 处理消息
        System.out.println("SECOND_QUEUE {} handleMessage :"+message);
    }

    @RabbitListener(queues = {QueueConfig.THIRD_QUEUE},
            containerFactory = "rabbitListenerContainerFactory")
    public void handleFanoutMessage2(String message) throws Exception {
        // 处理消息
        System.out.println("THIRD_QUEUE {} handleMessage :"+message);
    }

    @RabbitListener(queues = {QueueConfig.FOURTH_QUEUE},
            containerFactory = "rabbitListenerContainerFactory")
    public void handleHeadersMessage2(String message) throws Exception {
        // 处理消息
        System.out.println("FOURTH_QUEUE {} handleMessage :"+message);
    }

    @RabbitListener(queues = {QueueConfig.FIFTH_QUEUE},
            containerFactory = "rabbitListenerContainerFactory")
    public void handleHeadersMessage3(String message) throws Exception {
        // 处理消息
        System.out.println("FIFTH_QUEUE {} handleMessage :"+message);
    }

}
/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package site.zhangsun.utility.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description ：消息队列配置类，该类用于队列的维护。可以根据需要定义自己的消息队列。
 * @author ：Murphy ZhangSun
 * @version ：1
 * @program ：vevor_demo
 * @date ：Created in 2020/7/1 13:33
 */
@Configuration
public class QueueConfig {

    public static final String FIRST_QUEUE = "first-queue";
    public static final String SECOND_QUEUE = "second-queue";
    public static final String THIRD_QUEUE = "third-queue";
    public static final String FOURTH_QUEUE = "fourth-queue";
    public static final String FIFTH_QUEUE = "fifth-queue";

    /**
     * 测试队列
     *
     * @return 测试队列
     */
    @Bean
    public Queue firstQueue() {
        /*
         *   durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
         *   auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
         *   exclusive  表示该消息队列是否只在当前connection生效,默认是false
         */
        return new Queue(FIRST_QUEUE,true,false,false);
    }

    /**
     * 测试队列
     *
     * @return 测试队列
     */
    @Bean
    public Queue secondQueue() {
        return new Queue(SECOND_QUEUE,true,false,false);
    }

    /**
     * 测试队列
     *
     * @return 测试队列
     */
    @Bean
    public Queue thirdQueue() {
        return new Queue(THIRD_QUEUE,true,false,false);
    }

    /**
     * 测试队列
     *
     * @return 测试队列
     */
    @Bean
    public Queue fourthQueue() {
        return new Queue(FOURTH_QUEUE,true,false,false);
    }

    /**
     * 测试队列
     *
     * @return 测试队列
     */
    @Bean
    public Queue fifthQueue() {
        return new Queue(FIFTH_QUEUE,true,false,false);
    }
}
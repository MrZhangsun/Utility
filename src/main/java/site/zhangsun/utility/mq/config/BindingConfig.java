/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package site.zhangsun.utility.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Murphy ZhangSun
 * @version ：1
 * @description ：绑定配置类
 * @program ：vevor_demo
 * @date ：Created in 2020/7/1 15:16
 */
@Configuration
public class BindingConfig {

    private final QueueConfig queueConfig;
    private final ExchangeConfig exchangeConfig;

    public BindingConfig(QueueConfig queueConfig, ExchangeConfig exchangeConfig) {
        this.queueConfig = queueConfig;
        this.exchangeConfig = exchangeConfig;
    }

    /**
     * 路由键
     */
    public static final String ROUTING_KEY1 = "queue_one_key1";

    /**
     * 路由键
     */
    public static final String ROUTING_KEY2 = "queue_one_key2";

    /**
     * 路由键
     */
    public static final String ROUTING_KEY3 = "queue_one_key3";


//    /**
//     将消息队列1和direct交换机进行绑定
//     */
//    @Bean
//    public Binding directBinding1() {
//        return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.directExchange()).with(ROUTING_KEY1);
//    }
//
//    /**
//     * 将消息队列2和direct交换机进行绑定
//     */
//    @Bean
//    public Binding directBinding2() {
//        return BindingBuilder.bind(queueConfig.secondQueue()).to(exchangeConfig.directExchange()).with(ROUTING_KEY2);
//    }


    /**
     * 将消息队列3和topic交换机进行绑定
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.topicExchange()).with(ROUTING_KEY1);
    }

    /**
     * 将消息队列3和topic交换机进行绑定
     */
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(queueConfig.secondQueue()).to(exchangeConfig.topicExchange()).with(ROUTING_KEY2);
    }

    /**
     * 将消息队列3和topic交换机进行绑定
     */
    @Bean
    public Binding topicBinding3() {
        return BindingBuilder.bind(queueConfig.thirdQueue()).to(exchangeConfig.topicExchange()).with(ROUTING_KEY3);
    }

//    /**
//     * 将消息队列3和topic交换机进行绑定
//     */
//    @Bean
//    public Binding topicBinding4() {
//        return BindingBuilder.bind(queueConfig.fourthQueue()).to(exchangeConfig.topicExchange2()).with(ROUTING_KEY3);
//    }
//
    /**
     * 将消息队列2和fanout交换机进行绑定，所有发送到fanout交换机的消息都会被发送到该队列上的所有队列
     */
    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(queueConfig.fourthQueue()).to(exchangeConfig.fanoutExchange());
    }


//    /**
//     * 将消息队列2和headers交换机进行绑定
//     */
//    @Bean
//    public Binding fifthBinding() {
//        HashMap<String, Object> header = new HashMap<>(2);
//        header.put("queue", "queue1");
//        header.put("bindType", "whereAll");
//        return BindingBuilder.bind(queueConfig.fifthQueue()).to(exchangeConfig.headersExchange()).whereAll(header).match();
//    }
}
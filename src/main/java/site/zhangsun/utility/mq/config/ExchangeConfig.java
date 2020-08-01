/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package site.zhangsun.utility.mq.config;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Murphy ZhangSun
 * @version ：1.0
 * @description ：交换机配置类
 * @program ：vevor_demo
 * @date ：Created in 2020/7/1 13:32
 */
@Configuration
public class ExchangeConfig {

    /**
     * 消息交换机的名字
     */
    public static final String DIRECT_EXCHANGE = "demo-direct-exchange";

    /**
     * 消息交换机的名字
     */
    public static final String TOPIC_EXCHANGE = "demo-topic-exchange";

    /**
     * 消息交换机的名字
     */
    public static final String FANOUT_EXCHANGE = "demo-fanout-exchange";

    /**
     * 消息交换机的名字
     */
    public static final String HEADERS_EXCHANGE = "demo-headers-exchange";

    /**
     *   1.声明类型：direct exchange，绑定交换机名称
     *   2.durable="true" 持久化交换机，rabbitmq重启的时候不需要创建新的交换机
     *   3.direct交换器相对来说比较简单，匹配规则为：如果路由键匹配，消息就被投送到相关的队列
     *
     * @return Direct 交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE,true,false);
    }

    /**
     * topic交换器你采用模糊匹配路由键的原则进行转发消息到队列中
     *
     * @return Topic 交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE,true,false);
    }

    /**
     * topic交换器你采用模糊匹配路由键的原则进行转发消息到队列中
     *
     * @return Topic 交换机
     */
    @Bean
    public TopicExchange  topicExchange2() {
        return new TopicExchange("TOPIC_EXCHANGE",true,false);
    }

    /**
     * fanout交换器中没有路由键的概念，他会把消息发送到所有绑定在此交换器上面的队列中。
     *
     * @return Fanout 交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE,true,false);
    }

    /**
     *
     * @return Headers 交换机
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE,true,false);
    }
}
/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package site.zhangsun.utility.mq.publisher;

import java.util.Map;

/**
 * @author ：Murphy ZhangSun
 * @version ：1
 * @description ：MQ消息发送协议接口
 * @program ：vevor_demo
 * @date ：Created in 2020/7/1 14:28
 */
public interface MessageSender {


    /**
     * 发送点对点消息
     *
     * @param message 消息体
     * @param exchange 交换机
     * @param routeKey 路由键
     */
    void sendDirectMessage(String exchange, String routeKey, Object message);

    /**
     * 根据路由键模糊匹配消息
     *
     * @param message 消息体
     * @param exchange 交换机
     * @param routeKey 路由键
     */
    void sendTopicMessage(String exchange, String routeKey, Object message);

    /**
     * 根据路由键广播消息
     *
     * @param message 消息体
     * @param exchange 交换机
     */
    void sendFanoutMessage(String exchange, Object message);

    /**
     * 根据消息头信息匹配消息接收队列
     *
     * @param message 消息体
     * @param headers 消息头信息
     */
    void sendHeadersMessage(Object message, Map<String, String> headers);
}

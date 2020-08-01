/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package site.zhangsun.utility.mq.callback;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author ：Murphy ZhangSun
 * @version ：1
 * @description ：消息发送到交换机确认机制
 * @program ：vevor_demo
 * @date ：Created in 2020/7/1 13:48
 */
public class MsgSendReturnCallBack implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("收到失败消息：" + message + " i: " + i + " s: " + s + " s1: " + s1 + " s2: " + s2);
    }
}
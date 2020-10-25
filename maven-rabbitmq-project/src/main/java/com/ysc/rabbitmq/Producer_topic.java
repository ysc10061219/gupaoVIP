package com.ysc.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/***
 * topic方式
 */
public class Producer_topic {
    private static final String QUEUE_TOPIC1 = "queue_topic1";
    private static final String QUEUE_TOPIC2 = "queue_topic2";
    private static final String EXCHANGE_TOPIC = "exchange_topic";
    private static final String ROUTING_KEY_1 = "topic.#";
    private static final String ROUTING_KEY_2 = "#.ysc";

    public static void main(String[] args) throws Exception {
        //设置mq参数
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机,FANOUT类型交换机
        channel.exchangeDeclare(EXCHANGE_TOPIC, BuiltinExchangeType.TOPIC);
        //声明两个队列
        channel.queueDeclare(QUEUE_TOPIC1,true,false,false,null);
        channel.queueDeclare(QUEUE_TOPIC2,true,false,false,null);
        //绑定交换机与队列,
        channel.queueBind(QUEUE_TOPIC1,EXCHANGE_TOPIC,ROUTING_KEY_1);
        channel.queueBind(QUEUE_TOPIC2,EXCHANGE_TOPIC,ROUTING_KEY_2);
        //发送消息
        for (int i = 0; i < 10; i++) {
            String message = "";
            if(i % 3 == 0){
                message = "this is message with [topic.hahaha]" + i;
                channel.basicPublish(EXCHANGE_TOPIC,"topic.hahaha",null,message.getBytes());
            }else{
                message = "this is message with [xixix.ysc]" + i;
                channel.basicPublish(EXCHANGE_TOPIC,"xixix.ysc",null,message.getBytes());
            }
            System.out.println("send msg success : " + message);
        }
    }
}

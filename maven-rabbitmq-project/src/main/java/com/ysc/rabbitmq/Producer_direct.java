package com.ysc.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/***
 * direct方式
 */
public class Producer_direct {
    //队列名称
    private static final String QUEUE = "queue_direct";
    private static final String EXCHANE = "exchane_direct";
    private static final String ROUTING_KEY = "routing_key_direct";
    public static void main(String[] args) throws Exception {
        //设置mq参数
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        //创建连接
        connection = connectionFactory.newConnection();
        //创建通道
        channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE, true, false, false, null);
        //声明交换机
        channel.exchangeDeclare(EXCHANE, BuiltinExchangeType.DIRECT);
        //绑定
        channel.queueBind(QUEUE,EXCHANE,ROUTING_KEY);
        for (int i = 0; i < 10; i++) {
            String message = "";
            if(i % 2 == 0){
                message = "this is message for direct with routing_key : " + i;
                channel.basicPublish(EXCHANE, ROUTING_KEY, null, message.getBytes());
            }else{
                message = "this is message for direct without routing_key" + i;
                channel.basicPublish(EXCHANE, "", null, message.getBytes());
            }
            System.out.println("send msg : " + message);
        }
    }
}

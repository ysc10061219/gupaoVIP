package com.ysc.rabbitmq;

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
        String message = "this is maven type for direct..." + System.currentTimeMillis();
        channel.basicPublish("", QUEUE, null, message.getBytes());
        System.out.println("send msg : " + message);
    }
}

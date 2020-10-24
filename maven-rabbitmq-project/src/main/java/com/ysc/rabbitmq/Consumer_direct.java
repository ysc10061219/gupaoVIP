package com.ysc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/***
 * 消费者 direct
 */
public class Consumer_direct {
    //队列名称
    private static final String QUEUE = "queue_direct";

    public static void main(String[] args) throws Exception {
        //设置mq参数
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        Connection connection = null;
        Channel channel = null;
        //创建连接
        connection = connectionFactory.newConnection();
        //创建通道
        channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE, true, false, false, null);
        //定义消费方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //消息内容
                String msg = new String(body, "utf-8");
                System.out.println("receive message.." + msg);
            }
        };
        channel.basicConsume(QUEUE, true, consumer);
    }
}

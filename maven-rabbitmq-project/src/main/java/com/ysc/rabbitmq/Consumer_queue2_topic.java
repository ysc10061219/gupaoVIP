package com.ysc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/****
 * topic消费者1
 */
public class Consumer_queue2_topic {
    private static final String QUEUE_TOPIC2 = "queue_topic2";
    private static final String EXCHANGE_TOPIC = "exchange_topic";
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
        //声明队列
        channel.queueDeclare(QUEUE_TOPIC2,true,false,false,null);
        //绑定队列与交换机
        channel.queueBind(QUEUE_TOPIC2,EXCHANGE_TOPIC,ROUTING_KEY_2);
        //定义消费方法
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"utf-8");
                System.out.println(message);
            }
        };
        channel.basicConsume(QUEUE_TOPIC2,true,consumer);
    }
}

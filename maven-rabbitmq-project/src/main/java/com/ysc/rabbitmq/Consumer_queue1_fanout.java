package com.ysc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/***
 * fanout方式消费者
 */
public class Consumer_queue1_fanout {
    private static final String QUEUE_FANOUT1 = "queue_fanout1";
    private static final String EXCHANGE_FANOUT = "exchange_fanout";

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
        channel.exchangeDeclare(EXCHANGE_FANOUT, BuiltinExchangeType.FANOUT);
        //声明队列
        channel.queueDeclare(QUEUE_FANOUT1,true,false,false,null);
        //绑定队列与交换机
        channel.queueBind(QUEUE_FANOUT1,EXCHANGE_FANOUT,"");
        //定义消费方法
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"utf-8");
                System.out.println(message);
            }
        };
        channel.basicConsume(QUEUE_FANOUT1,true,consumer);
    }
}

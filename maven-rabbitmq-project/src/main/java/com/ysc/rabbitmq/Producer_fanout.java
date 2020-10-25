package com.ysc.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/***
 * fanout方式
 */
public class Producer_fanout {
    private static final String QUEUE_FANOUT1 = "queue_fanout1";
    private static final String QUEUE_FANOUT2 = "queue_fanout2";
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
        //声明两个队列
        channel.queueDeclare(QUEUE_FANOUT1,true,false,false,null);
        channel.queueDeclare(QUEUE_FANOUT2,true,false,false,null);
        //绑定交换机与队列,fanout不需要routingkey
        channel.queueBind(QUEUE_FANOUT1,EXCHANGE_FANOUT,"");
        channel.queueBind(QUEUE_FANOUT2,EXCHANGE_FANOUT,"");
        //发送消息
        for (int i = 0; i < 10; i++) {
            String message = "this is fanout message :" + i;
            channel.basicPublish(EXCHANGE_FANOUT,"",null,message.getBytes());
            System.out.println("send msg success : " + message);
        }
    }
}

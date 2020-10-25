package com.ysc.consumer;

import com.rabbitmq.client.Channel;
import com.ysc.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer_fanout {
    @RabbitListener(queues = {RabbitConfig.QUEUE_FANOUT_1})
    public void receiFanout1(String msg, Message message, Channel channel){
        System.out.println("fanout1 reveive msg : " + msg);
    }
    @RabbitListener(queues = {RabbitConfig.QUEUE_FANOUT_2})
    public void receiFanout2(String msg, Message message, Channel channel){
        System.out.println("fanout2 reveive msg : " + msg);
    }
}

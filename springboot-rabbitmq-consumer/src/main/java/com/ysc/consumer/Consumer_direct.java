package com.ysc.consumer;

import com.rabbitmq.client.Channel;
import com.ysc.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class Consumer_direct {
    @RabbitListener(queues = {RabbitConfig.QUEUE_DIRECT})
    public void receiDirect(String msg, Message message, Channel channel){
        System.out.println(msg);
    }
}

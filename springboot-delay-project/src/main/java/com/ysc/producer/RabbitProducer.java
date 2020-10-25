package com.ysc.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RabbitProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendDelayMessage(List<Integer> list) {
        //这里的消息可以是任意对象，无需额外配置，直接传即可
        System.out.println("===============延时队列生产消息====================");
        System.out.println("发送时间:"+LocalDateTime.now()+",发送内容:"+list.toString());
        this.rabbitTemplate.convertAndSend(
                "delay_exchange",
                "delay_key",
                list,
                message -> {
                    //注意这里时间可以使long，而且是设置header
                    message.getMessageProperties().setHeader("x-delay",60000);
                    return message;
                }
        );
        System.out.println("60000ms后执行");
    }
}

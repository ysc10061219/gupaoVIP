package com.ysc.producer;

import com.ysc.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Producer_topic {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void test_send_topic(){
        for (int i = 0; i < 10; i++) {
            String message = "this is message use topic1_" + i;
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC,"topic.nihao",message);
            System.out.println("send message success : " + message);
        }
        for (int i = 0; i < 10; i++) {
            String message = "this is message use topic2_" + i;
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC,"woaini.ysc",message);
            System.out.println("send message success : " + message);
        }
    }
}

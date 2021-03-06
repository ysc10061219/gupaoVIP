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
public class Producer_fanout {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void test_send_fanout(){
        for (int i = 0; i < 10; i++) {
            String message = "this is message use fanout " + i;
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_FANOUT,"",message);
            System.out.println("send message success : " + message);
        }
    }
}

package com.ysc.controller;

import com.ysc.producer.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/delay")
public class TestController {
    @Autowired
    RabbitProducer rabbitProducer;

    @RequestMapping("/test")
    public void test(){
        List l1 = new ArrayList();
        l1.add("林楚凡");
        l1.add("wang shuang qin");
        rabbitProducer.sendDelayMessage(l1);
    }
}

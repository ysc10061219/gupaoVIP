package com.ysc.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /*direct参数*/
    public static final String QUEUE_DIRECT = "queue_direct";
    public static final String ROUTING_KEY_DIRECT = "routing_key_direct";
    public static final String EXCHANGE_DIRECT="exchange_direct";
    /*fanout参数*/
    public static final String QUEUE_FANOUT_1 = "queue_fanout_1";
    public static final String QUEUE_FANOUT_2 = "queue_fanout_2";
    public static final String EXCHANGE_FANOUT="exchange_fanout";
    /*topic参数*/
    public static final String QUEUE_TOPIC_1 = "queue_topic_1";
    public static final String QUEUE_TOPIC_2 = "queue_topic_2";
    public static final String ROUTING_KEY_TOPIC_1 = "topic.#";
    public static final String ROUTING_KEY_TOPIC_2 = "#.ysc";
    public static final String EXCHANGE_TOPIC="exchange_topic";

    /**********************交换机声明开始******************************/

    @Bean(EXCHANGE_DIRECT)
    public Exchange EXCHANGE_DIRECT(){
        return ExchangeBuilder.directExchange(EXCHANGE_DIRECT).durable(true).build();
    }

    @Bean(EXCHANGE_FANOUT)
    public Exchange EXCHANGE_FANOUT(){
        return ExchangeBuilder.fanoutExchange(EXCHANGE_FANOUT).durable(true).build();
    }

    @Bean(EXCHANGE_TOPIC)
    public Exchange EXCHANGE_TOPIC(){
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC).durable(true).build();
    }
    /**********************交换机声明结束******************************/



    /**********************队列声明开始******************************/
    @Bean(QUEUE_DIRECT)
    public Queue QUEUE_DIRECT() {
        Queue queue = new Queue(QUEUE_DIRECT);
        return queue;
    }

    @Bean(QUEUE_FANOUT_1)
    public Queue QUEUE_FANOUT_1() {
        Queue queue = new Queue(QUEUE_FANOUT_1);
        return queue;
    }

    @Bean(QUEUE_FANOUT_2)
    public Queue QUEUE_FANOUT_2() {
        Queue queue = new Queue(QUEUE_FANOUT_2);
        return queue;
    }

    @Bean(QUEUE_TOPIC_1)
    public Queue QUEUE_TOPIC_1() {
        Queue queue = new Queue(QUEUE_TOPIC_1);
        return queue;
    }

    @Bean(QUEUE_TOPIC_2)
    public Queue QUEUE_TOPIC_2() {
        Queue queue = new Queue(QUEUE_TOPIC_2);
        return queue;
    }
    /**********************队列声明结束******************************/



    /**********************绑定队列与交换机开始******************************/
    @Bean
    public Binding BINDING_ROUTING_KEY_DIRECT(@Qualifier(QUEUE_DIRECT) Queue queue,
                                            @Qualifier(EXCHANGE_DIRECT) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_DIRECT).noargs();
    }


    @Bean
    public Binding BINDING_FANOUT_1(@Qualifier(QUEUE_FANOUT_1) Queue queue,
                                            @Qualifier(EXCHANGE_FANOUT) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding BINDING_FANOUT_2(@Qualifier(QUEUE_FANOUT_2) Queue queue,
                                            @Qualifier(EXCHANGE_FANOUT) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }


    @Bean
    public Binding BINDING_ROUTING_KEY_TOPIC_1(@Qualifier(QUEUE_TOPIC_1) Queue queue,
                                               @Qualifier(EXCHANGE_TOPIC) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_TOPIC_1).noargs();
    }
    @Bean
    public Binding BINDING_ROUTING_KEY_TOPIC_2(@Qualifier(QUEUE_TOPIC_2) Queue queue,
                                            @Qualifier(EXCHANGE_TOPIC) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_TOPIC_2).noargs();
    }

    /**********************绑定队列与交换机结束******************************/
}

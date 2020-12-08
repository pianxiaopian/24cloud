package com.twenty.four.demo.service;

import com.twenty.four.demo.domain.OrderEntity;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "demoTopic", consumerGroup = "demo_consumer")
public class OrdeConsumer implements RocketMQListener<OrderEntity> {
    @Override
    public void onMessage(OrderEntity o) {

        System.out.println("o:" + o);
    }
}

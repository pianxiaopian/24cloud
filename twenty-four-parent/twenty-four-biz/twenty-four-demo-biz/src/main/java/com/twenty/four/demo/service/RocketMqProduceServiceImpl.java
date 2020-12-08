package com.twenty.four.demo.service;

import com.twenty.four.demo.api.RocketMqProduceService;
import com.twenty.four.demo.domain.OrderEntity;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: rocketmq 生产者实现类
 * @author: chendong
 * @create: 2020/12/1 11:57
 */
@RestController
public class RocketMqProduceServiceImpl implements RocketMqProduceService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public String sendMsg() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId("1111");
        orderEntity.setOrderName("2222");
        rocketMQTemplate.convertAndSend("demoTopic", orderEntity);
        return "success";
    }

}

package com.twenty.four.demo.api;


import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: rocketmq 生产者
 * @author: chendong
 * @create: 2020/12/1 11:54
 */
public interface RocketMqProduceService {
    @RequestMapping("/sendMsg")
    String sendMsg();

}

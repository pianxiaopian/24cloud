package com.twenty.four.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Data;

/**
 * @description: 用于生产者生成数据实体类
 * @author: chendong
 * @create: 2020/12/1 11:55
 */
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class OrderEntity{

    private String orderId;
    private String orderName;

//    public OrderEntity(String orderId, String orderName) {
//        this.orderId = orderId;
//        this.orderName = orderName;
//    }

    public OrderEntity() {
    }

}

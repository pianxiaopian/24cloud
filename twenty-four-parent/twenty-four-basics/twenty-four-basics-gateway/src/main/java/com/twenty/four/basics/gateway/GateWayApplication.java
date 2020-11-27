package com.twenty.four.basics.gateway;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



/**
 * @ClassName EoZyGateWayApplication
 * @Description TODO 网关服务启动类
 * @Author chendong
 * @Date 2020/9/22
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GateWayApplication.class, args);
    }



}

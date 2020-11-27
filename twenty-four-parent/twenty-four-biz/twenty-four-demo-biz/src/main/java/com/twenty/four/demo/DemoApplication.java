package com.twenty.four.demo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: demo启动类
 * @author: chendong
 * @create: 2020/11/27 15:23
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableCaching
@MapperScan("com.twenty.four.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}

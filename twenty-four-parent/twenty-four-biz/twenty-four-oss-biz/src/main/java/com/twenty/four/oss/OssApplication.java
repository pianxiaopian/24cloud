package com.twenty.four.oss;

import com.spring4all.swagger.EnableSwagger2Doc;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.redis.service.RedisService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 用户服务启动类
 * @author: chendong
 * @create: 2020/11/30 9:35
 */


@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@MapperScan(basePackages = {"com.twenty.four.oss.mapper"})
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }

    @Bean
    public RedisService redisService(){
        return new RedisService();
    }
}

package com.twenty.four.auth;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 联合登录启动类
 * @author: chendong
 * @create: 2020/12/4 15:57
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@MapperScan("com.twenty.four.auth.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }

}

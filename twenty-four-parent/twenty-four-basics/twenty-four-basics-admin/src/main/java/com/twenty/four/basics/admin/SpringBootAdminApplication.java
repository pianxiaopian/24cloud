package com.twenty.four.basics.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 监控服务启动类
 * @author: chendong
 * @create: 2020/11/26 16:16
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer //开启admin监控功能
public class SpringBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class,args);
    }
}

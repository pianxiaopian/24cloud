# 24cloud
24cloud

需求讨论与技术架构选型

技术选型
A. 项目采用SpringBoot2.x+SpringCloud2.x+SpringCloudAlibaba2.x构建微服务电商项目
1.使用Nacos作为注册中心，实现服务治理
2.使用Gateway网关框架管理服务请求入口
3.使用Ribbon实现本地负载均衡器和OpenFeign/restTemplate客户端调用工具
4.使用Sentinel服务保护框架(服务降级、隔离、熔断、限流)
5.使用 Zipkin+SpringCloudSleuth 实现服务追踪
6.微服务API接口安全控制与单点登陆系统spring+security+oatuh2.0
7.使用Redis+Canal+Kafka 实现mysql与redis数据一致性
8.使用Seata 解决分部署事务
9.使用nginx 实现反向代理
10.使用 lvs+keepalived+nginx实现nginx高可用
11.使用mysql 做数据存储


B. 分布式基础设施环境构建
1.分布式任务调度平台XXL-Job
2.分布式日志采集系统ELK 
3.分布式事务解决方案Seata
4.分布式锁解决方案Zookeeper、Redis
5.分布式配置中心nacos 
6.高并发分布式全局ID生成雪花算法
7.分布式Session框架Spring-Session
8.分布式服务追踪与调用链ZipKin  



C.项目运营与部署环境
1.分布式设施环境，统一采用docker安装
2.使用jenkins+docker+k8s实现自动部署 
3.微服务API管理ApiSwagger
4.使用github代码管理 

环境要求

为了能够更好的学习互联网微服务架构，该项目对环境要求非常高，建议电脑配置CPU在I5、32GB内存或者电脑采用集群化部署。
1.JDK统一要求:JDK1.8K 
2.Maven 统一管理Jar
3.统一采用Docker安装软件
4.编码统一采用为UTF-8
5.开发工具IDEA


系统架构
 
 


构建项目

twenty-four-parent-----公共Pranet接口
-----twenty-four-basics----分布式基础设施
---------twenty-four-basics-gateway—统一请求入口 80
--------- twenty-four-basics-xuxueli-xxljob—分布式任务调度平台
--------- twenty-four-basics-seata—分布式事务解决框架
--------- twenty-four-basics-zipkin  —分布式调用链系统


----- twenty-four-api提供公共接口
------------ twenty-four-demo-api demo模块
服务接口中包含内存内容: 实体类层、接口层 

-----twenty-four-biz公共接口的实现
------------ twenty-four-demo-biz demo服务接口实现
-----twenty-four-common 工具类
---------twenty-four-common-core—核心工具类
---------twenty-four-common-redis—redis工具类

-----twenty-four-portal 门户平台
--------twenty-four-portal-web 门户网站 
--------twenty-four-portal-sso 单点登陆系统 
--------twenty-four-portal-search 搜索系统
--------twenty-four-portal-spike 秒杀系统
--------twenty-four-portal-cms 系统 

后期有新的功能再加。

注意事项
统一采用@GetMapping或者@PostMapping实现接口映射
接口使用@GetMapping、@PostMapping、@PutMapping、@DeleteMapping对应注解
功能说明
门户登录/注册模块
主要功能：
登录：qq登录/微信登录/短信登录/游客登录
注册：邮箱注册/手机号注册
密码重置：邮箱重置/短信重置
门户信息修改模块
主要功能：

 


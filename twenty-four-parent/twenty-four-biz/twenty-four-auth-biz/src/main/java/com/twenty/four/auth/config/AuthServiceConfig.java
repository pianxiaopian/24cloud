package com.twenty.four.auth.config;

import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.core.utils.ParamToOtherUtils;
import com.twenty.four.common.core.utils.TokenUtils;
import com.twenty.four.common.redis.service.RedisService;
import com.twenty.four.oss.api.OssService;
import com.twenty.four.oss.model.vo.CodeVO;
import com.twenty.four.oss.model.vo.UserRegisterVO;
import javax.validation.Valid;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 联合登录配置类
 * @author: chendong
 * @create: 2020/12/4 17:25
 */
@Configuration
public class AuthServiceConfig {

    @Bean
    public TokenUtils tokenUtils(){
        return new TokenUtils();
    }

    @Bean
    public RedisService redisService(){
        return new RedisService();
    }

    @Bean
    public ParamToOtherUtils paramToOtherUtils(){
        return new ParamToOtherUtils();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

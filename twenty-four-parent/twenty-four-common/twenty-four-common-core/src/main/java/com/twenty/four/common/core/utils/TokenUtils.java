package com.twenty.four.common.core.utils;

import com.twenty.four.common.redis.service.RedisService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: TokenUtils
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1221:47
 */
@Component
public class TokenUtils {
    @Autowired
    private RedisService redisService;

    public String createToken(String prefix
            , String value,TimeUnit timeUnit) {
        return createToken(prefix, value, null,timeUnit);
    }

    public void setOpenId(String key ,String value, Long timeOut, TimeUnit timeUnit) {
        redisService.setCacheObject(key, value, timeOut,timeUnit);
    }

    public void setKey(String key ,String value, Long timeOut, TimeUnit timeUnit) {
        redisService.setCacheObject(key, value, timeOut,timeUnit);
    }

    /**
     * 前缀
     *
     * @param prefix
     * @param value
     * @return
     */
    public String createToken(String prefix
            , String value, Long timeOut, TimeUnit timeUnit) {
        //1.生成我们的令牌
        String token = prefix + "_" + UUID.randomUUID().toString().replace("-", "");
        // 2.将该token存入到Redis中
        redisService.setCacheObject(token, value, timeOut,timeUnit);
        return token;
    }

    public String getTokenValue(String token) {
        return redisService.getCacheObject(token);
    }

    public  boolean delToken(String token){
        return  redisService.deleteObject(token);
    }

}

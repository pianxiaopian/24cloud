package com.twenty.four.common.redis.utils;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: chendong
 * @create: 2020/11/5 14:43
 */
@Component
public class RedisClient {
    /**
     * 获取redis模版
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置redis的key-value
     */
    public void setString(String key, String value) {
        setString(key, value, null);
    }

    /**
     * 设置redis的key-value，带过期时间
     */
    public void setString(String key, String value, Long timeOut) {
        stringRedisTemplate.opsForValue().set(key, value);
        if (timeOut != null) {
            stringRedisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取redis中key对应的值
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除redis中key对应的值
     */
    public Boolean deleteKey(String key) {
        return stringRedisTemplate.delete(key);
    }
}

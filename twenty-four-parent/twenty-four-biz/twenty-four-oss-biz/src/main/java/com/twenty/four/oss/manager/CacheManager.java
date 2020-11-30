package com.twenty.four.oss.manager;

import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.redis.service.RedisService;
import com.twenty.four.oss.model.vo.CodeVO;
import com.twenty.four.oss.strategys.RegisterStrategy;
import com.twenty.four.oss.util.SpringUtils;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description: 缓存类
 * @author: chendong
 * @create: 2020/11/30 14:59
 */
@Component
public class CacheManager {

    @Autowired
    private RedisService redisServiceUtils;

    @Cacheable(value = "twenty-four-oss-getStrategyId", key = "'getStrategyId'+'_'+#beanId", unless = "#result != null")
    public RegisterStrategy getStrategyId(String beanId){
        RegisterStrategy registerStrategy = null;
        try{
            registerStrategy = SpringUtils.getBean(beanId, RegisterStrategy.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return registerStrategy;
    }

    public Result getCode(CodeVO codeVO){
        //模拟生成验证码
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        //判断验证码类型
        if(codeVO.getCodeType() == SwingConstants.NORTH){
            //验证码用于注册
            //将获取到的验证码放入redis中
            redisServiceUtils.setCacheObject(codeVO.getMobile(),String.valueOf(code),10L, TimeUnit.MINUTES);
        }else if(codeVO.getCodeType() == SwingConstants.LEFT){
            //验证码用于登录
            //将获取到的验证码放入redis中
            redisServiceUtils.setCacheObject(codeVO.getMobile(),String.valueOf(code),10L, TimeUnit.MINUTES);
        }else{
            return Result.fail("验证码类型有误");
        }
        return Result.ok(code);
    }
}

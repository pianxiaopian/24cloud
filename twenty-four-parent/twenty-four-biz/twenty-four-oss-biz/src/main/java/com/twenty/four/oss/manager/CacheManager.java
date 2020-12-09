package com.twenty.four.oss.manager;

import com.baomidou.mybatisplus.extension.api.R;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.core.utils.RandomUtil;
import com.twenty.four.common.redis.service.RedisService;
import com.twenty.four.oss.model.vo.CodeVO;
import com.twenty.four.oss.model.vo.TokenVO;
import com.twenty.four.oss.strategys.RegisterStrategy;
import com.twenty.four.oss.util.SpringUtils;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

/**
 * @description: 缓存类
 * @author: chendong
 * @create: 2020/11/30 14:59
 */
@Component
public class CacheManager {

    @Autowired
    private RedisService redisServiceUtils;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.sender}")
    private String sender;

    @Value("${email.subject}")
    private String subject;

    @Value("${email.content}")
    private String content;


    @Cacheable(value = "twenty-four-oss-getStrategyId", key = "'getStrategyId'+'_'+#beanId", unless = "#registerStrategy == null")
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
            return Result.ok(code,"[注册]获取验证码成功");
        }else if(codeVO.getCodeType() == SwingConstants.LEFT){
            //验证码用于登录
            //将获取到的验证码放入redis中
            redisServiceUtils.setCacheObject(codeVO.getMobile(),String.valueOf(code),10L, TimeUnit.MINUTES);
            return Result.ok(code,"[登录]获取验证码成功");
        }else if(codeVO.getCodeType() == SwingConstants.BOTTOM){
            //验证码用于关联用户
            //将获取到的验证码放入redis中
            redisServiceUtils.setCacheObject(codeVO.getMobile(),String.valueOf(code),10L, TimeUnit.MINUTES);
            return Result.ok(code,"[关联]获取验证码成功");
        }else {
            return Result.fail("验证码类型有误");
        }
    }

    public Result getEmailToken(TokenVO tokenVO) {
        // 生成6位随机数字令牌
        String emailToken = RandomUtil.generateEmailToken(6);

        // 发送令牌到指定邮箱
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(tokenVO.getEmail());
        message.setSubject(subject);
        message.setText(String.format(content, emailToken));

        try {
            javaMailSender.send(message);
            // 将生成的令牌放入redis中
            redisServiceUtils.setCacheObject(tokenVO.getEmail(), emailToken, 10L, TimeUnit.MINUTES);
        } catch (MailSendException e) {
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            return Result.fail("发送邮件令牌失败");
        }

        return Result.ok(true, "验证码已发送到您的邮箱,请注意查收");
    }
}

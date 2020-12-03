package com.twenty.four.oss.manager;

import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.core.utils.EmailValidateUtil;
import com.twenty.four.common.core.utils.IDCardValidateUtil;
import com.twenty.four.common.core.utils.MobileValidateUtil;
import com.twenty.four.common.redis.service.RedisService;
import com.twenty.four.oss.constants.StrategyConstants;
import com.twenty.four.oss.model.vo.UserRegisterVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @description: 验证参数类
 * @author: chendong
 * @create: 2020/11/30 10:32
 *
 */

@Component
public class ValidationManager {

    @Autowired
    private RedisService redisServiceUtils;

    public Result validationRegisterVO(UserRegisterVO userRegisterVO){
        //判断身份证是否有效
        if(!IDCardValidateUtil.IDCardValidate(userRegisterVO.getIdCardNum())){
            return Result.fail("身份证号码有误");
        }
        if(userRegisterVO.getServiceName().equals(StrategyConstants.OSS_RG_MOBILE)){
            //判单手机号是否有效
            if(userRegisterVO.getMobile() != null && !MobileValidateUtil.mobileValidate(userRegisterVO.getMobile())){
                return Result.fail("手机号有误");
            }
            //判断验证码请求参数是否为空
            if(StringUtils.isBlank(userRegisterVO.getCode())){
                return Result.fail("验证码有误");
            }
            String rgCode = redisServiceUtils.getCacheObject(userRegisterVO.getMobile());
            if(!userRegisterVO.getCode().equals(rgCode)){
                return Result.fail("验证码输入错误");
            }
        }else{
            //判单手机号是否有效
            if(userRegisterVO.getMobile() != null){
                if(!MobileValidateUtil.mobileValidate(userRegisterVO.getMobile())){
                    return Result.fail("手机号有误");
                }
            }
        }
        if(userRegisterVO.getServiceName().equals(StrategyConstants.OSS_RG_EMAIL)){
            //判断电子邮箱是否有效
            if(!EmailValidateUtil.validateEmail(userRegisterVO.getEmail())){
                return Result.fail("电子邮箱有误");
            }
            //判断验证码请求参数是否为空
            if(StringUtils.isBlank(userRegisterVO.getCode())){
                return Result.fail("请输入验证码");
            }
            String emailToken = redisServiceUtils.getCacheObject(userRegisterVO.getEmail());
            if(!userRegisterVO.getCode().equals(emailToken)){
                return Result.fail("令牌输入错误");
            }
        }else{
            if(userRegisterVO.getEmail() != null){
                //判断电子邮箱是否有效
                if(!EmailValidateUtil.validateEmail(userRegisterVO.getEmail())){
                    return Result.fail("电子邮箱有误");
                }
            }
        }

        //密码加密
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bcryptPasswordEncoder.encode(userRegisterVO.getPassword());
        userRegisterVO.setPassword(encodePassword);
        return Result.ok();
    }

    /**
     * 手机号验证
     */
    public Result validationMobile(String mobile){
        //判单手机号是否有效
        if(!MobileValidateUtil.mobileValidate(mobile)){
            return Result.fail("手机号无效");
        }
        return Result.ok();
    }

    /**
     * 邮箱验证
     */
    public Result validationEmail(String email) {
        // 判断邮箱是否有效
        if (!EmailValidateUtil.validateEmail(email)) {
            return Result.fail("邮箱地址无效");
        }
        return Result.ok();
    }

}

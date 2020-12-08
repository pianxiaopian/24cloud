package com.twenty.four.oss.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twenty.four.common.core.constant.Constants;
import com.twenty.four.common.core.enums.ResultCode;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.redis.service.RedisService;
import com.twenty.four.oss.api.OssService;
import com.twenty.four.oss.domain.StrategyDO;
import com.twenty.four.oss.domain.UserInfoDO;
import com.twenty.four.oss.manager.CacheManager;
import com.twenty.four.oss.manager.UnionUserManager;
import com.twenty.four.oss.manager.ValidationManager;
import com.twenty.four.oss.mapper.OssMapper;
import com.twenty.four.oss.mapper.StrategyMapper;
import com.twenty.four.oss.model.dto.UserInfoDTO;
import com.twenty.four.oss.model.vo.CodeVO;
import com.twenty.four.oss.model.vo.TokenVO;
import com.twenty.four.oss.model.vo.UnionUserVO;
import com.twenty.four.oss.model.vo.UserRegisterVO;
import com.twenty.four.oss.strategys.RegisterStrategy;
import javax.swing.SwingConstants;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户服务接口实现类
 * @author: chendong
 * @create: 2020/11/30 9:37
 */
@RestController
public class OssServiceImpl extends ServiceImpl<OssMapper, UserInfoDO> implements OssService {

    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private ValidationManager validationManager;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UnionUserManager unionUserManager;

    @Override
    public Result register(@Valid UserRegisterVO registerVO) throws Exception{
        //参数校验
        Result validationResult = validationManager.validationRegisterVO(registerVO);
        if(validationResult.getCode() == Constants.FAIL){
            return validationResult;
        }
        //创建查询体
        QueryWrapper<StrategyDO> queryWrapper = new QueryWrapper<>();
        //设置查询条件
        queryWrapper.eq("strategy_name",registerVO.getServiceName());
        //调用查询方法
        StrategyDO strategyDO = strategyMapper.selectOne(queryWrapper);
        //判断数据库中是否存有服务名称
        if(strategyDO == null){
            return Result.fail(ResultCode.FAILURE,"服务名称不存在");
        }
        //获取bean
        String beanId = strategyDO.getStrategyBeanId();
        RegisterStrategy registerStrategy = cacheManager.getStrategyId(beanId);
        //判断程序中是否有beanId
        if (registerStrategy == null) {
            return Result.fail(ResultCode.FAILURE,"服务不存在。");
        }
        //调用对应的策略实现类
        Result result = registerStrategy.userRegister(registerVO);
        return result;
    }

    @Override
    public Result getRegisterCode(CodeVO codeVO) {
        Result mobileResult = validationManager.validationMobile(codeVO.getMobile());
        if(mobileResult.getCode() == Constants.FAIL){
            return mobileResult;
        }
        Result codeResult = cacheManager.getCode(codeVO);
        return codeResult;
    }

    @Override
    public Result getEmailToken(TokenVO tokenVO) {
        Result emailResult = validationManager.validationEmail(tokenVO.getEmail());
        if (emailResult.getCode() == Constants.FAIL) {
            return emailResult;
        }

        return cacheManager.getEmailToken(tokenVO);
    }

    @Override
    public Result getUserInfo(String openId,String columnName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(columnName,openId);
        queryWrapper.eq("delete_flag", SwingConstants.CENTER);
        queryWrapper.eq("state", SwingConstants.CENTER);
        UserInfoDO userInfoDO = null;
        try{
            userInfoDO = baseMapper.selectOne(queryWrapper);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e+"");
            return Result.fail("系统错误");
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        if(userInfoDO == null){
            String openIdRedis = redisService.getCacheObject(columnName);
            if(StringUtils.isBlank(openIdRedis)){
                return Result.fail("信息已超时,请重试");
            }
            return Result.fail_result(ResultCode.NOT_UNION_QUERIED,openIdRedis);
        }
        BeanUtils.copyProperties(userInfoDO,userInfoDTO);
        return Result.ok(userInfoDTO,"登录成功");
    }

    @Override
    public Result unionUser(@Valid UnionUserVO unionUserVO) {
        Result mobileResult = validationManager.validationMobile(unionUserVO.getMobile());
        if(mobileResult.getCode() == Constants.FAIL){
            return mobileResult;
        }
        Result result = unionUserManager.unionUserMethod(unionUserVO);
        return result;
    }
}

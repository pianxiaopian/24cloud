package com.twenty.four.oss.strategys.impl;

import com.twenty.four.common.core.exception.SqlException;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.core.utils.DateUtil;
import com.twenty.four.common.core.utils.UuidUtils;
import com.twenty.four.common.redis.service.RedisService;
import com.twenty.four.oss.domain.UserInfoDO;
import com.twenty.four.oss.mapper.OssMapper;
import com.twenty.four.oss.model.vo.UserRegisterVO;
import com.twenty.four.oss.strategys.RegisterStrategy;
import javax.swing.SwingConstants;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 手机注册策略实现类
 * @author: chendong
 * @create: 2020/11/30 9:55
 */
@Component
public class MobileRegisterStrategyImpl implements RegisterStrategy {

    @Autowired
    private OssMapper ossMapper;
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result userRegister(UserRegisterVO userRegisterVO) throws Exception{
        UserInfoDO userInfoDO = new UserInfoDO();
        String uuid = UuidUtils.newUUIDString();
        BeanUtils.copyProperties(userRegisterVO,userInfoDO);
        userInfoDO.setCreateTime(DateUtil.getCreateDate());
        userInfoDO.setUuid(uuid);
        userInfoDO.setRegisterType(SwingConstants.CENTER+"");
        int insert = 0;
        try{
            insert = ossMapper.insert(userInfoDO);
        }catch (DuplicateKeyException e){
            throw new SqlException("参数违法唯一约束",e+"");
        }catch (Exception e){
            throw new Exception(e+"");
        }
        if(insert > SwingConstants.CENTER){
            redisService.deleteObject(userRegisterVO.getMobile());
            return Result.ok("注册成功");
        }
        return Result.fail("注册失败");
    }
}

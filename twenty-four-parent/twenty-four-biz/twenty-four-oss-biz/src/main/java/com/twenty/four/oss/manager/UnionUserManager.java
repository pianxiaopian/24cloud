package com.twenty.four.oss.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twenty.four.common.core.enums.ResultCode;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.redis.service.RedisService;
import com.twenty.four.oss.domain.UserInfoDO;
import com.twenty.four.oss.mapper.OssMapper;
import com.twenty.four.oss.model.dto.UserInfoDTO;
import com.twenty.four.oss.model.vo.UnionUserVO;
import java.sql.SQLException;
import java.sql.Wrapper;
import javax.swing.SwingConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 联合登录关联用户类
 * @author: chendong
 * @create: 2020/12/8 16:21
 */

@Component
@Slf4j
public class UnionUserManager {

    @Autowired
    private RedisService redisServiceUtils;
    @Autowired
    private OssMapper ossMapper;

    @Transactional
    public Result unionUserMethod(UnionUserVO unionUserVO){
        String rgCode = redisServiceUtils.getCacheObject(unionUserVO.getMobile());
        if(unionUserVO.getCode().equals(rgCode)){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("mobile",unionUserVO.getMobile());
            queryWrapper.eq("delete_flag", SwingConstants.CENTER);
            queryWrapper.eq("state", SwingConstants.CENTER);
            UserInfoDO userInfoDO = null;
            try{
                userInfoDO = ossMapper.selectOne(queryWrapper);
            }catch (Exception e){
                e.printStackTrace();
                log.error(e+"");
                return Result.fail("系统错误");
            }
            if(userInfoDO == null){
                return Result.fail(ResultCode.NOT_QUERIED);
            }
            String openId = redisServiceUtils.getCacheObject(unionUserVO.getUnionToken());
            UserInfoDO updateData = new UserInfoDO();
            updateData.setQqUnionId(openId);
            if(StringUtils.isBlank(openId)){
                return Result.fail("信息已超时,请重试");
            }
            QueryWrapper updateWrapper = new QueryWrapper();
            updateWrapper.eq("uuid",userInfoDO.getUuid());
            int update = ossMapper.update(updateData, updateWrapper);
            if(update > SwingConstants.CENTER){
                //更新成功
                UserInfoDTO userInfoDTO = new UserInfoDTO();
                BeanUtils.copyProperties(userInfoDO,userInfoDTO);
                redisServiceUtils.deleteObject(unionUserVO.getMobile());
                return Result.ok(userInfoDTO,"关联成功");
            }else{
                return Result.fail("系统错误");
            }
        }else{
            return Result.fail("验证码输入错误");
        }

    }
}

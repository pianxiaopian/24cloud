package com.twenty.four.demo.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twenty.four.common.core.enums.ResultCode;
import com.twenty.four.common.core.exception.SqlException;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.core.utils.UuidUtils;
import com.twenty.four.demo.api.OssService;
import com.twenty.four.demo.domain.UserInfoDO;
import com.twenty.four.demo.mapper.OssServiceMapper;
import com.twenty.four.demo.model.dto.UserInfoRespDTO;
import com.twenty.four.demo.model.vo.UserInfoVO;
import java.util.List;
import javax.swing.SwingConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 单点登录实现类
 * @author: chendong
 * @create: 2020/11/20 15:46
 */
@RestController
@Slf4j
public class OssServiceImpl extends ServiceImpl<OssServiceMapper, UserInfoDO> implements
    OssService {


    @Override
    @Cacheable(value = "twenty-four-demo-selectDemo",
        key = "'selectDemo'",
        unless = "#result.code != 200")
    public Result<List<UserInfoRespDTO>> selectDemo() {
        List<UserInfoRespDTO> userInfoRespDTOS = baseMapper.selectUserInfo();
        if(userInfoRespDTOS == null || userInfoRespDTOS.size() == SwingConstants.CENTER){
            return Result.fail(ResultCode.NOT_QUERIED);
        }
        return Result.ok(userInfoRespDTOS);
    }

    @Override
    @Transactional
    @CacheEvict(value = "twenty-four-demo-selectDemo",allEntries = true)
    public Result save(UserInfoVO userInfoVO) throws Exception {
        UserInfoDO userInfoDO = new UserInfoDO();
        String uid = UuidUtils.newUUIDString();
        BeanUtils.copyProperties(userInfoVO,userInfoDO);
        userInfoDO.setUuid(uid);
        int insert = 0;
        try{
            insert = baseMapper.insert(userInfoDO);
        }catch (DuplicateKeyException e){
            throw new SqlException("参数违法唯一约束",e+"");
        }
        if(insert > SwingConstants.CENTER){
            return Result.ok();
        }
        return Result.fail();
    }
}

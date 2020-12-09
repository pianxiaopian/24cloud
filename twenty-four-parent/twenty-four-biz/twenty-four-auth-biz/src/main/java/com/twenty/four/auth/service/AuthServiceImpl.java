package com.twenty.four.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twenty.four.auth.api.AuthService;
import com.twenty.four.auth.domain.UnionLoginDo;
import com.twenty.four.auth.mapper.AuthMapper;
import com.twenty.four.auth.strategys.UnionLoginStrategy;
import com.twenty.four.auth.util.SpringUtils;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.core.utils.TokenUtils;
import javax.servlet.http.HttpServletRequest;
import javax.swing.SwingConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @description: 联合登录启动类
 * @author: chendong
 * @create: 2020/12/4 15:58
 */

@RestController
public class AuthServiceImpl extends ServiceImpl<AuthMapper, UnionLoginDo> implements AuthService {


    @Override
    public Result<JSONObject> createAddress(String unionPublicId) {
        if (StringUtils.isEmpty(unionPublicId)) {
            return Result.fail("unionPublicId不为空");
        }
        //创建查询体
        QueryWrapper<UnionLoginDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_public_id",unionPublicId);
        queryWrapper.eq("is_availability", SwingConstants.NORTH);
        UnionLoginDo unionLoginDo = new UnionLoginDo();
        try{
            unionLoginDo = baseMapper.selectOne(queryWrapper);
        }catch (Exception e){
            return Result.fail("查询渠道失败");
        }
        if (unionLoginDo == null) {
            return Result.fail("渠道已经关闭或者该渠道不存在");
        }
        // 从Spring容器中
        String unionBeanId = unionLoginDo.getUnionBeanId();
        if (StringUtils.isEmpty(unionBeanId)) {
            return Result.fail("bean参数配置错误");
        }
        UnionLoginStrategy unionLoginStrategy =
            SpringUtils.getBean(unionBeanId, UnionLoginStrategy.class);
        Result result = unionLoginStrategy.unionLoginCallback(unionLoginDo);
        return result;
    }

    @Override
    public Result<JSONObject> unionLoginCallBack(String unionPublicId) {
        if (StringUtils.isEmpty(unionPublicId)) {
            return Result.fail("unionPublicId不为空");
        }
        //创建查询体
        QueryWrapper<UnionLoginDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_public_id",unionPublicId);
        queryWrapper.eq("is_availability", SwingConstants.NORTH);
        UnionLoginDo unionLoginDo = new UnionLoginDo();
        try{
            unionLoginDo = baseMapper.selectOne(queryWrapper);
        }catch (Exception e){
            return Result.fail("查询渠道失败");
        }
        if (unionLoginDo == null) {
            return Result.fail("渠道已经关闭或者该渠道不存在");
        }
        // 从Spring容器中
        String unionBeanId = unionLoginDo.getUnionBeanId();
        if (StringUtils.isEmpty(unionBeanId)) {
            return Result.fail("bean参数配置错误");
        }
        UnionLoginStrategy unionLoginStrategy =
            SpringUtils.getBean(unionBeanId, UnionLoginStrategy.class);
        // 获取授权码参数
        HttpServletRequest request = ((ServletRequestAttributes)
            (RequestContextHolder.currentRequestAttributes())).getRequest();
        Result result = unionLoginStrategy.unionLoginCallback(request,unionLoginDo);
        return result;
    }

}

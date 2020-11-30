package com.twenty.four.oss.strategys.impl;

import com.twenty.four.common.core.result.Result;
import com.twenty.four.oss.model.vo.UserRegisterVO;
import com.twenty.four.oss.strategys.RegisterStrategy;
import org.springframework.stereotype.Component;

/**
 * @description: qq注册策略实现类
 * @author: chendong
 * @create: 2020/11/30 9:57
 */
@Component
public class QqRegisterStrategyImpl implements RegisterStrategy {

    @Override
    public Result userRegister(UserRegisterVO userRegisterVO) {
        return null;
    }
}

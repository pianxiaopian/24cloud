package com.twenty.four.oss.strategys;

import com.twenty.four.common.core.result.Result;
import com.twenty.four.oss.model.vo.UserRegisterVO;

/**
 * @description: 注册策略类
 * @author: chendong
 * @create: 2020/11/30 9:52
 */
public interface RegisterStrategy {

    /**
     * 用户注册
     * @param userRegisterVO
     * @return
     */
    Result userRegister(UserRegisterVO userRegisterVO) throws Exception;
}

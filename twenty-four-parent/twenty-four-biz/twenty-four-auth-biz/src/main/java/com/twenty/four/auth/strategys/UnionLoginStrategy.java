package com.twenty.four.auth.strategys;

import com.twenty.four.auth.domain.UnionLoginDo;
import com.twenty.four.common.core.result.Result;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 联合登录策略类
 * @author: chendong
 * @create: 2020/12/4 15:58
 */
public interface UnionLoginStrategy {

    Result unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo);

    Result unionLoginCallback(UnionLoginDo unionLoginDo);
}

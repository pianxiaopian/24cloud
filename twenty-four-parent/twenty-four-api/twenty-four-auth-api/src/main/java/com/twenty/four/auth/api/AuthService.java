package com.twenty.four.auth.api;


import com.alibaba.fastjson.JSONObject;
import com.twenty.four.common.core.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 联合登录接口类
 * @author: chendong
 * @create: 2020/12/4 15:22
 */
@Api(value = "oauth2.0协议联合登录")
public interface AuthService {

    /**
     * 联合登录 生成授权链接
     * @param unionPublicId
     * @return
     */
    @GetMapping("/createAddress")
    Result<JSONObject> createAddress(@RequestParam("unionPublicId") String unionPublicId);

    /**
     * 联合登录回调方法 用户选择授权，生成授权链接
     * @param unionPublicId
     * @return
     */
    @GetMapping("/login/oauth/callback")
    Result<JSONObject> unionLoginCallBack(@RequestParam("unionPublicId") String unionPublicId);


}

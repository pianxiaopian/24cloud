package com.twenty.four.oss.api;

import com.twenty.four.common.core.result.Result;
import com.twenty.four.oss.model.vo.CodeVO;
import com.twenty.four.oss.model.vo.UnionUserVO;
import com.twenty.four.oss.model.vo.UserRegisterVO;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 用户服务接口
 * @author: chendong
 * @create: 2020/11/28 14:36
 */
@Api(value = "用户服务接口")
public interface OssService {

    /**
     * 用户注册接口
     * @param registerVO
     * @return
     * @throws Exception
     */
    @PostMapping("register")
    Result register(@RequestBody @Valid UserRegisterVO registerVO) throws Exception;


    /**
     * 获取验证码
     * @param codeVO
     * @return
     */
    @PostMapping("getRegisterCode")
    Result getRegisterCode(@RequestBody @Valid CodeVO codeVO);


    /**
     * 根据条件获取用户信息
     * @param openId
     * @param columnName
     * @return
     */
    @PostMapping("getUserInfo")
    Result getUserInfo(@RequestParam("openId") String openId,
                       @RequestParam("columnName") String columnName);


    /**
     * 联合登录关联用户
     * @param unionUserVO
     * @return
     */
    @PostMapping("unionUser")
    Result unionUser(@RequestBody @Valid UnionUserVO unionUserVO);

}

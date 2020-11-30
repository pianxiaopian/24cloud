package com.twenty.four.oss.api;

import com.twenty.four.common.core.result.Result;
import com.twenty.four.oss.model.vo.CodeVO;
import com.twenty.four.oss.model.vo.UserRegisterVO;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


    @PostMapping("getRegisterCode")
    Result getRegisterCode(@RequestBody @Valid CodeVO codeVO);

}

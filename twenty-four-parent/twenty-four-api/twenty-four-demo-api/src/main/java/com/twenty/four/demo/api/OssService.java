package com.twenty.four.demo.api;


import com.twenty.four.common.core.result.Result;
import com.twenty.four.demo.model.dto.UserInfoRespDTO;
import com.twenty.four.demo.model.vo.UserInfoVO;
import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 单点登录接口服务
 * @author: chendong
 * @create: 2020/11/20 15:02
 */
@Api(value = "单点登录服务接口")
public interface OssService {


    /**
     * 查询全部用户信息
     * @return
     */
    @PostMapping("/selectDemo")
    Result<List<UserInfoRespDTO>> selectDemo();

    /**
     * 保存用户信息
     */
    @PostMapping("/save")
    Result save(@RequestBody @Valid UserInfoVO userInfoVO) throws Exception;

    /**
     * 保存用户信息
     */
    @PostMapping("/search")
    Result<List<UserInfoRespDTO>> search(@RequestParam(required = false) String searchKey);
}

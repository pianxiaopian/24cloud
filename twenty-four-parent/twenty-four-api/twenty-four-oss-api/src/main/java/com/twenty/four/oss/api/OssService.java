package com.twenty.four.oss.api;

import com.twenty.four.common.core.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 用户服务接口
 * @author: chendong
 * @create: 2020/11/28 14:36
 */
@Api(value = "用户服务接口")
public interface OssService {

    @PostMapping("register")
    Result register();
}

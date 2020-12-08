package com.twenty.four.oss.model.vo;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description: 查询用户信息-参数接收类
 * @author: chendong
 * @create: 2020/12/7 14:28
 */

@Data
public class ReqUserInfoVO {

    @ApiModelProperty(value = "联合登录表id")
    private String unionLoginId;
}

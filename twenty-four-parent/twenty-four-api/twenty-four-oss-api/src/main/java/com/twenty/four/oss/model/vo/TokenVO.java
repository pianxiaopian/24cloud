package com.twenty.four.oss.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 获取邮箱令牌前端参数
 * @author: pianxiaopian
 * @create: 2020/12/03 17:08
 */
@Data
public class TokenVO {

    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    @NotNull(message = "邮箱地址不能为空")
    private String email;

    /**
     * 验证码类型
     */
    @ApiModelProperty(value = "令牌类型(1.用于注册，2.用于登录)")
    @NotNull(message = "验证码类型不能为空")
    private Integer tokenType;
}

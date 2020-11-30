package com.twenty.four.oss.model.vo;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description: 获取验证码前端参数
 * @author: chendong
 * @create: 2020/11/30 15:15
 */
@Data
public class CodeVO {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String mobile;

    /**
     * 验证码类型
     */
    @ApiModelProperty(value = "验证码类型(1.用于注册，2.用于登录)")
    @NotNull(message = "验证码类型不能为空")
    private Integer codeType;


}

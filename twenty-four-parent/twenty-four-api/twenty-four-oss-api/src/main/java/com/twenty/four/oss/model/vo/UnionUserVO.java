package com.twenty.four.oss.model.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description: 联合登录关联用户
 * @author: chendong
 * @create: 2020/12/8 16:26
 */

@Data
public class UnionUserVO {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String mobile;


    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不能为空")
    private String code;


    /**
     * 关联类型
     */
    @ApiModelProperty(value = "关联类型")
    @NotNull(message = "关联类型不能为空（1.qq_union_id,2.wx_union_id）")
    private String type;


    /**
     * unionToken
     */
    @ApiModelProperty(value = "unionToken")
    @NotNull(message = "unionToken不能为空")
    private String unionToken;

}

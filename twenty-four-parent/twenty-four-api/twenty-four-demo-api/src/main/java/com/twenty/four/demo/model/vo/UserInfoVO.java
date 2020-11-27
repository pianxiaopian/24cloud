package com.twenty.four.demo.model.vo;


import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description: 前端入参类
 * @author: chendong
 * @create: 2020/11/20 16:10
 */
@Data
public class UserInfoVO {


    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    @NotNull(message = "登录密码不能为空")
    private String password;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    @NotNull(message = "用户真实姓名不能为空")
    private String name;

    /**
     * 用户身份证号
     */
    @ApiModelProperty(value = "用户身份证号")
    @NotNull(message = "用户身份证号不能为空")
    private String idCardNum;
}

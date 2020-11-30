package com.twenty.four.oss.model.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 用户注册接受请求参数实体类
 * @author: chendong
 * @create: 2020/11/30 9:13
 */
@Data
public class UserRegisterVO {


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

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称")
    @NotNull(message = "服务名称不能为空")
    private String serviceName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;

}

package com.twenty.four.oss.domain;



/**
 * @description: 用户表映射类
 * @author: chendong
 * @create: 2020/11/20 15:32
 */
@ApiModel(value = "用户表映射UserInfo类", description="用户表信息")
@TableName(value = "user_info")
@Data
public class UserInfoDO {

    @ApiModelProperty(value = "uuid")
    @TableId(value = "uuid",type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    @TableField(value = "password")
    private String password;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    @TableField(value = "name")
    private String name;

    /**
     * 用户身份证号
     */
    @ApiModelProperty(value = "用户身份证号")
    @TableField(value = "id_card_num")
    private String idCardNum;

    /**
     * 用户状态：0:正常状态,1：用户被锁定
     */
    @ApiModelProperty(value = "用户状态")
    @TableField(value = "state")
    private String state;
}

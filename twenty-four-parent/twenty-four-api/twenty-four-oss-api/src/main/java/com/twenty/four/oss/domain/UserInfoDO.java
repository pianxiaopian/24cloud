package com.twenty.four.oss.domain;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

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

    /**
     * 删除标志：0.false,1.true
     */
    @ApiModelProperty(value = "删除标志")
    @TableField(value = "delete_flag")
    private String deleteFlag;

    /**
     * 注册类型： 0.手机号，1.邮箱，2.qq，3.微信
     */
    @ApiModelProperty(value = "注册类型")
    @TableField(value = "register_type")
    private String registerType;


    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    @TableField(value = "create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField(value = "mobile")
    private String mobile;

    /**
     * qq_open_id
     */
    @ApiModelProperty(value = "qq_open_id")
    @TableField(value = "qq_union_id")
    private String qqUnionId;


    /**
     * wechat_open_id
     */
    @ApiModelProperty(value = "wechat_open_id")
    @TableField(value = "wx_union_id")
    private String wxUnionId;



}

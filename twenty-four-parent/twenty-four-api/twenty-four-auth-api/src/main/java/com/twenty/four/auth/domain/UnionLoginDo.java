package com.twenty.four.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 联合登录数据库映射层
 * @author: chendong
 * @create: 2020/12/4 16:33
 */
@Data
@TableName(value = "pian_union_login")
public class UnionLoginDo {
    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    Long id;
    /**
     * 登陆名称 比如 腾讯QQ 腾讯支付
     */
    @ApiModelProperty(value = "联合登录名称")
    @TableField(value = "union_name")
    String unionName;
    /**
     * appId
     */
    @ApiModelProperty(value = "appId")
    @TableField(value = "app_id")
    String appId;
    /**
     * 联合登陆的id
     */
    @ApiModelProperty(value = "回调地址标识")
    @TableField(value = "union_public_id")
    String unionPublicId;
    /**
     * beanId
     */
    @ApiModelProperty(value = "策略bean_id")
    @TableField(value = "union_bean_id")
    String unionBeanId;
    /**
     * appKey
     */
    @ApiModelProperty(value = "appKey")
    @TableField(value = "app_key")
    String appKey;
    /**
     * redirectUri 回调地址
     */
    @ApiModelProperty(value = "redirectUri回调地址")
    @TableField(value = "redirect_uri")
    String redirectUri;
    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调请求地址")
    @TableField(value = "request_address")
    String requestAddress;

    /**
     * 渠道是否可用
     */
    @ApiModelProperty(value = "渠道是否可用")
    @TableField(value = "is_availability")
    String isAvailability;
}

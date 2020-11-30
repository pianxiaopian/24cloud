package com.twenty.four.oss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 策略类
 * @author: chendong
 * @create: 2020/11/30 10:03
 */
@Data
@TableName(value = "user_register_strategy")
public class StrategyDO {

    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 服务名称
     */
    @TableField(value = "strategy_name")
    private String strategyName;

    /**
     * beanId(OOS_RG_QQ,OOS_RG_MOBILE,OSS_RG_WECHAT,OSS_RG_EMAIL)
     */
    @TableField(value = "strategy_bean_id")
    private String strategyBeanId;


}

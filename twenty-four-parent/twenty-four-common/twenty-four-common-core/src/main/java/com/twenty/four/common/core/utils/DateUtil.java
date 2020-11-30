package com.twenty.four.common.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 时间转换工具类
 * @author: chendong
 * @create: 2020/11/30 11:29
 */
public class DateUtil {

    /**
     * 获取创建时间
     * @return
     */
    public static Date getCreateDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        Date createTime = null;
        try {
            createTime = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createTime;
    }
}

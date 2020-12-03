package com.twenty.four.common.core.utils;

import java.util.Random;

/**
 * @description: 随机数工具类
 * @author: pianxiaopian
 * @create: 2020/11/30 17：35
 */
public class RandomUtil {

    /**
     * 生成随机数字
     * @param length
     * @return
     */
    public static String generateEmailToken(Integer length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(String.valueOf(number));
        }

        return sb.toString();
    }
}

package com.twenty.four.common.core.utils;

/**
 * @description: 手机号判断类
 * @author: chendong
 * @create: 2020/11/30 10:41
 */
public class MobileValidateUtil {
    /**
     * 用正则表达式判断手机号格式是否正确
     */
    public static boolean mobileValidate(String mobile){

        //定义手机号的规则
        String regex = "1[57]\\d{9}"; //1表示 以 1开头；[57]表示第二位是 5或者 7  \\d[9]表示 还剩9位  总共11位

        //调用功能 判断
        boolean flag = mobile.matches(regex);

        //输出结果
        //System.out.println("flag:"+flag);
        return flag;
    }

    public static void main(String[] args) {
        mobileValidate("3413415");
    }
}

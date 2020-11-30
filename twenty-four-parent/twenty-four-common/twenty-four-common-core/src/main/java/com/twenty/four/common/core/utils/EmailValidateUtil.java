package com.twenty.four.common.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 邮箱验证类
 * @author: chendong
 * @create: 2020/11/30 11:00
 */
public class EmailValidateUtil {

    private static final Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    public static boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        //System.out.println(matcher.matches());
        return matcher.matches();
    }

    public static void main(String[] args) {
        validateEmail("222");
    }

}

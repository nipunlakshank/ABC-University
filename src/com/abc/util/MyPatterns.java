/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.abc.util;

import java.util.regex.Pattern;

/**
 *
 * @author Nipun
 */
public interface MyPatterns {
    Pattern ANY_PHONE = Pattern.compile("0((11)|(2(1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|(7(0|1|2|[4-8]))|([8-9]1))[0-9]{7}");
    Pattern LAND_PHONE = Pattern.compile("0((11)|(2(1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|([8-9]1))[0-9]{7}");
    Pattern MOBILE_PHONE = Pattern.compile("(0|/+94)7[01245678][0-9]{7}");
    Pattern EMAIL_CASE_INSENSITIVE = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,8}$", Pattern.CASE_INSENSITIVE);
    Pattern EMAIL_CASE_SENSITIVE = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,8}$");
    Pattern MARKS = Pattern.compile("^(([0-9]{1,2}|100)|(([0-9]{1,2}|100)[.]{1}[0-9]{1,2}))$");
}

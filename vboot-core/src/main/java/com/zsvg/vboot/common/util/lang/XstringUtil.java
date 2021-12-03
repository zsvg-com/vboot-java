package com.zsvg.vboot.common.util.lang;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XstringUtil {

    public static boolean containChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

//    public static boolean isEmpty(String str) {
//        return str == null || str.length() == 0|| "null".equals(str);
//    }
//
//    public static boolean isNotEmpty(String str) {
//        return !XstringUtil.isEmpty(str);
//    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0|| "null".equals(str)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllBlank(String str1,String str2) {
        return XstringUtil.isBlank(str1)&&XstringUtil.isBlank(str2);
    }

    public static boolean isAllBlank(String str1,String str2,String str3) {
        return XstringUtil.isBlank(str1)&&XstringUtil.isBlank(str2)&&XstringUtil.isBlank(str3);
    }

    public static boolean isNotBlank(String str) {
        return !XstringUtil.isBlank(str);
    }

    public static String addWhereInCondition(String field,List<String> list){
        if(list==null||list.size()==0){
            return " 1=0 ";
        }
        StringBuilder sb=new StringBuilder(field).append(" in (");
        for (String str : list) {
            sb.append("'").append(str).append("',");
        }
        sb.setCharAt(sb.length()-1,')');
        return sb.toString();
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //生成随机字母和数字,
    public static String getRSID(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
//                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + 97);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static String getRSID4() {
        return getRSID(4);
    }

    public static String getRSID8() {
        return getRSID(8);
    }

    //首字母转小写
    public static String toLowerfirst(String s)
    {
        if (Character.isLowerCase(s.charAt(0)))
        {
            return s;
        } else
        {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    //首字母转大写
    public static String toUpperfirst(String s)
    {
        if (Character.isUpperCase(s.charAt(0)))
        {
            return s;
        } else
        {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    public static String DelZeroBefore(String str){
       return str.replaceAll("^(0+)", "");
    }

    public static String addZeroBefore(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }



}

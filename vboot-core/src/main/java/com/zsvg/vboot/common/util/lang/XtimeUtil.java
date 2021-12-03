package com.zsvg.vboot.common.util.lang;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XtimeUtil
{
    private static long lastTime;
    private static long startTime;

    public static String getTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void calStart()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
        startTime=System.currentTimeMillis();
        lastTime=startTime;
        System.err.println("----"+sdf.format(new Date())+" 计时开始▼ ");
    }

    public static void calMid()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
        long curTime=System.currentTimeMillis();
        long time = curTime - lastTime;
        System.err.println("----"+sdf.format(new Date())+" 中间用时■ "+time + "毫秒");
        lastTime=curTime;
    }

    public static void calEnd()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
        long curTime=System.currentTimeMillis();
        long time = curTime - lastTime;
        long time2 = curTime - startTime;
        System.err.println("----"+sdf.format(new Date())+" 计时结束▲ "+time + "毫秒,合计：" + time2+ "毫秒");

    }

    public static String getTimeByDate(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Boolean compare(String s1, String s2)
    {
        if(s1==null){
            return false;
        }else if(s2==null){
            return true;
        }
        int i = s1.compareTo(s2);
        if (i >= 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public static long getTimeGap(long beforeTimeMillis)
    {
       return  (System.currentTimeMillis() - beforeTimeMillis) / 1000;
    }
}

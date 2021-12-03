package com.zsvg.vboot.module.sys.job.main;

import com.zsvg.vboot.module.sys.job.root.IJob;
import com.zsvg.vboot.module.sys.job.root.IJobGroup;
import com.zsvg.vboot.common.util.lang.XtimeUtil;
import org.springframework.stereotype.Component;

@IJobGroup
@Component
public class SysJobDemo
{
    private static boolean usingFlag = false;

    //每10分钟一次
    @IJob(key="demo1",cron = "0 10 0-23 * * ?", name = "10分钟执行一次的DEMO1")
    public void demo1()
    {
        if (!usingFlag)
        {
            usingFlag = true;
            try
            {
//                Thread.sleep(20000);
                //Thread.sleep(5000);
//                int i=1/0;
                System.out.println("定时任务-demo1:" + XtimeUtil.getTime());
                //---------------
            } finally
            {
                usingFlag = false;
            }
        }

    }

//    //每10秒
//    @IJob(key="demo2",cron = "0/20 * 0-23 * * ?", name = "20秒执行一次的DEMO2")
//    public void demo2()
//    {
//        if (!usingFlag)
//        {
//            usingFlag = true;
//            try
//            {
//                System.out.println("job-demo2:" + XtimeUtil.getTime());
//            } finally
//            {
//                usingFlag = false;
//            }
//        }
//
//    }
}
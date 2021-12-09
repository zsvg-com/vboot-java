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

    @IJob(code="demo1",cron = "0/10 * 0-23 * * ?", name = "10秒执行一次的DEMO1")
    public void demo1()
    {
        System.out.println("定时任务-demo1:" + XtimeUtil.getTime());
    }

    @IJob(code="demo2",cron = "0/20 * 0-23 * * ?", name = "20秒执行一次的DEMO2")
    public void demo2()
    {
        if (!usingFlag)
        {
            usingFlag = true;
            try
            {
                System.out.println("定时任务-demo2:" + XtimeUtil.getTime());
            } finally
            {
                usingFlag = false;
            }
        }

    }
}
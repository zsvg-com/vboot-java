package com.zsvg.vboot.bi.etl.main;

import com.zsvg.vboot.common.util.web.XspringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class KetJobFactory implements Job {


    @Override
    public void execute(JobExecutionContext context) {
          String kettleId = (String) context.getMergedJobDataMap().get("etl");
        try {
            BiEtlMainService service= XspringUtil.getBean(BiEtlMainService.class);
            service.zrunById(kettleId,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.zsvg.vboot.bi.etl.main;

import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

@Configuration
@Slf4j
public class BiEtlJobListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private BiEtlMainHandler handler;

    @Autowired
    private BiEtlMainService etlService;

    //初始启动quartz
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<BiEtlMain> kettleList = etlService.findAll();
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        try {
            handler.startAllEtlJobs(kettleList);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
//            log.info("----ETL定时调度任务开启----");
    }


}
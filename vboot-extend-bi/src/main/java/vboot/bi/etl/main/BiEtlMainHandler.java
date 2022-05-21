package vboot.bi.etl.main;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BiEtlMainHandler {



    //开始执行所有任务
    public void startAllEtlJobs(List<BiEtlMain> kettleList) throws SchedulerException {
        for (int i = 0; i < kettleList.size(); i++) {
            if(kettleList.get(i).getAvtag()){
                initEtlJob(scheduler,kettleList.get(i));
            }
        }
        //scheduler.start();
    }

    private void initEtlJob(Scheduler scheduler, BiEtlMain kettle) throws SchedulerException {
        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder.newJob(KetJobFactory.class).withIdentity(kettle.getId(), "etl").build();
        // 基于表达式构建触发器
        jobDetail.getJobDataMap().put("etl", kettle.getId());
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(kettle.getCron());
        // CronTrigger表达式触发器 继承于Trigger
        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(kettle.getId(), "etl")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    //禁用任务
    public void stopEtlJob(BiEtlMain kettle) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(kettle.getId(), "etl");
        scheduler.deleteJob(jobKey);
        kettle.setAvtag(false);
        biEtlMainRepo.save(kettle);
    }

    //启用任务
    public void startEtlJob(BiEtlMain kettle) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(kettle.getId(), "etl");
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder.newJob(KetJobFactory.class)
                    .withIdentity(kettle.getId(), "etl").build();
            jobDetail.getJobDataMap().put("etl", kettle.getId());
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(kettle.getCron());
            trigger = TriggerBuilder.newTrigger().withIdentity(kettle.getId(), "etl")
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(kettle.getCron());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, trigger);
        }
        kettle.setAvtag(true);
        biEtlMainRepo.save(kettle);
    }

    // 任务调度
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private BiEtlMainRepo biEtlMainRepo;

}

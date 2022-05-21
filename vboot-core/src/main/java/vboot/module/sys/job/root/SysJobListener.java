package vboot.module.sys.job.root;

import vboot.module.sys.job.main.SysJobMain;
import vboot.module.sys.job.main.SysJobMainService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

@Configuration
@Slf4j
public class SysJobListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private SysJobHandler handler;

    @Autowired
    private SysJobMainService sysJobMainService;


    //初始启动quartz
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<SysJobMain> dbJobList = sysJobMainService.getJobList();
        try {
            handler.startAllJobs(dbJobList);
//            log.info("----SYS定时调度任务开启----");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


}
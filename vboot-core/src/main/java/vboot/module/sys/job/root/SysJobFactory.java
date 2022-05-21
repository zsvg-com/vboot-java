package vboot.module.sys.job.root;

import vboot.module.sys.job.log.SysJobLog;
import vboot.module.sys.job.log.SysJobLogRepo;
import vboot.module.sys.job.main.SysJobMain;
import vboot.common.util.lang.XstringUtil;
import vboot.common.util.lang.XtimeUtil;
import vboot.common.util.web.XspringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.util.ReflectionUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class SysJobFactory implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        SysJobMain job = (SysJobMain) context.getMergedJobDataMap().get("job");
        Method method = ReflectionUtils.findMethod(XspringUtil.getBean(job.getJgroup()).getClass(), job.getJid());
        SysJobLog log = new SysJobLog();
        log.setId(XstringUtil.getUUID());
        SysJobLogRepo syJobMlogDao= (SysJobLogRepo) XspringUtil.getBean("sysJobLogRepo");
        log.setName(job.getName());
        long start = System.currentTimeMillis();
        try {
            log.setSttim(XtimeUtil.getTime());
            method.invoke(XspringUtil.getBean(job.getJgroup()));
            log.setRet("成功");
            log.setMsg("用时：" + (System.currentTimeMillis() - start) / 1000 + "秒");
            log.setEntim(XtimeUtil.getTime());
            syJobMlogDao.save(log);
        } catch (Exception e) {
            System.err.println("定时任务运行失败");
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            log.setRet("失败");
            String message = sw.toString();
            if (message.length() >= 5000)
            {
                log.setMsg(message.substring(0, 5000));
            } else
            {
                log.setMsg(message);
            }
            log.setEntim(XtimeUtil.getTime());
            syJobMlogDao.save(log);
        }
    }


}
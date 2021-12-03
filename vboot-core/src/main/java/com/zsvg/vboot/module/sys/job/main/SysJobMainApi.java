package com.zsvg.vboot.module.sys.job.main;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.module.sys.job.root.SysJobHandler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/job/main")
public class SysJobMainApi {

    private String table = "sys_job_main";

    @GetMapping
    public RestResult get(String id, String name) {
        Sqler sqler = new Sqler("t.id,t.name,t.jid,t.jgroup,t.cron,t.state", table);
        sqler.addLike("t.name", name);
        return RestResult.ok(jdbcDao.findPageData(sqler));
    }

    @GetMapping("start/{ids}")
    public RestResult startJob(@PathVariable String[] ids) throws SchedulerException {
        for (int i = 0; i < ids.length; i++) {
            SysJobMain job = repo.findById(ids[i]).get();
            handler.startJob(job);
        }
        return RestResult.ok();
    }

    @GetMapping("stop/{ids}")
    public RestResult list_stop(@PathVariable String[] ids) throws SchedulerException {
        for (int i = 0; i < ids.length; i++) {
            SysJobMain job = repo.findById(ids[i]).get();
            handler.stopJob(job);
        }
        return RestResult.ok();
    }

    @GetMapping("one/{id}")
    public RestResult get(@PathVariable String id) {
        SysJobMain main=repo.findById(id).get();
        return RestResult.ok(main);
    }

    @GetMapping("once/{id}")
    public RestResult edit_startOnce(@PathVariable String id) throws InterruptedException, SchedulerException {
        SysJobMain main=repo.findById(id).get();
        handler.onceJob(main);
        return RestResult.ok();
    }

    @PutMapping
    public RestResult update(@RequestBody SysJobMain main) {
        SysJobMain sysJobMain = repo.findById(main.getId()).get();
        sysJobMain.setCron(main.getCron());
        repo.save(sysJobMain);
        return RestResult.ok();

    }
    @Autowired
    private SysJobHandler handler;

    @Autowired
    private SysJobMainRepo repo;

    @Autowired
    private JdbcDao jdbcDao;


}
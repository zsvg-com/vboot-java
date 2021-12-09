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
        Sqler sqler = new Sqler("t.id,t.name,t.reurl,t.cron,t.avtag,t.code,t.crtim,t.notes", table);
        sqler.addLike("t.name", name);
        return RestResult.ok(jdbcDao.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SysJobMain main=repo.findById(id).get();
        return RestResult.ok(main);
    }

    @PutMapping
    public RestResult put(@RequestBody SysJobMain main) throws SchedulerException {
        SysJobMain dbJob = repo.findById(main.getId()).get();
        if(dbJob.getAvtag()){
            String[] arr= dbJob.getReurl().split("/");
            dbJob.setJgroup(arr[0]);
            dbJob.setJid(arr[1]);
            handler.stopJob(dbJob);
        }
        repo.save(main);
        if(main.getAvtag()){
            String[] arr2= main.getReurl().split("/");
            main.setJgroup(arr2[0]);
            main.setJid(arr2[1]);
            handler.startJob(main);
        }
        return RestResult.ok();
    }


    @PostMapping("start/{ids}")
    public RestResult startJob(@PathVariable String[] ids) throws SchedulerException {
        for (int i = 0; i < ids.length; i++) {
            SysJobMain job = repo.findById(ids[i]).get();
            String[] arr= job.getReurl().split("/");
            job.setJgroup(arr[0]);
            job.setJid(arr[1]);
            handler.startJob(job);
        }
        return RestResult.ok();
    }

    @PostMapping("stop/{ids}")
    public RestResult stopJob(@PathVariable String[] ids) throws SchedulerException {
        for (int i = 0; i < ids.length; i++) {
            SysJobMain job = repo.findById(ids[i]).get();
            String[] arr= job.getReurl().split("/");
            job.setJgroup(arr[0]);
            job.setJid(arr[1]);
            handler.stopJob(job);
        }
        return RestResult.ok();
    }


    @PostMapping("once/{id}")
    public RestResult startOnce(@PathVariable String id) throws InterruptedException, SchedulerException {
        SysJobMain main=repo.findById(id).get();
        String[] arr= main.getReurl().split("/");
        main.setJgroup(arr[0]);
        main.setJid(arr[1]);
        handler.onceJob(main);
        return RestResult.ok();
    }


    @Autowired
    private SysJobHandler handler;

    @Autowired
    private SysJobMainRepo repo;

    @Autowired
    private JdbcDao jdbcDao;


}
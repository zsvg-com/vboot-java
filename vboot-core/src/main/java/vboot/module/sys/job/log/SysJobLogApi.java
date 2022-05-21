package vboot.module.sys.job.log;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/job/log")
public class SysJobLogApi {

    private String table = "sys_job_log";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("t.id,t.name,t.sttim,t.entim,t.ret", table);
//        XreqUtil.setPageParam(sqler);
        sqler.addLike("t.name", name);
        sqler.addOrder("t.entim desc");
        System.out.println(sqler.getSql());
        return RestResult.ok(service.findPageData(sqler)) ;
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        return RestResult.ok(service.findOne(id));
    }


    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids)  {
        return RestResult.ok(service.delete(ids));
    }



    @Autowired
    private SysJobLogService service;
}

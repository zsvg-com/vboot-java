package vboot.bi.etl.main;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bi/etl/main")
public class BiEtlMainApi {

    @Value("${app.att.path}")
    private String ATT_PATH;

    private String table = "bi_etl_main";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("t.id,t.name,t.code,t.cron,t.avtag", table);
        sqler.addLeftJoin("o.name as crman", "sys_org o", "o.id=t.crman");
        sqler.addLike("t.name", name);
        sqler.addOrder("t.crtim desc");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        return RestResult.ok(service.findOne(id));
    }

    @PostMapping("once/{id}")
    public RestResult edit_startOnce(@PathVariable String id) throws Exception {
        BiEtlMain kettle = service.findOne(id);
        KetUtil.runTran(ATT_PATH + "/" + kettle.getZpath(), null, true);
        return RestResult.ok();
    }

    @PostMapping
    public RestResult post(@RequestBody BiEtlMain main) {
        main.setAvtag(false);
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody BiEtlMain main) throws SchedulerException {
        if (main.getAvtag()) {
            handler.startEtlJob(main);
        }
        return RestResult.ok(service.update(main));
    }

    @PostMapping("start/{ids}")
    public RestResult startJob(@PathVariable String[] ids) throws SchedulerException {
        for (int i = 0; i < ids.length; i++) {
            BiEtlMain kettle =service.findOne(ids[i]);
            handler.startEtlJob(kettle);
        }
        return RestResult.ok();
    }

    @PostMapping("stop/{ids}")
    public RestResult list_stop(@PathVariable String[] ids) throws SchedulerException {
        for (int i = 0; i < ids.length; i++) {
            BiEtlMain kettle = service.findOne(ids[i]);
            handler.stopEtlJob(kettle);
        }
        return RestResult.ok();
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) throws SchedulerException {
        for (int i = 0; i < ids.length; i++) {
            BiEtlMain kettle = service.findOne(ids[i]);
            handler.stopEtlJob(kettle);
        }
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private BiEtlMainHandler handler;

    @Autowired
    private BiEtlMainService service;

}
package com.zsvg.vboot.module.sys.org.post;

import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.api.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/org/post")
public class SysOrgPostApi {

    private String table = "sys_org_post";

    @GetMapping
    public RestResult get(String deptid, String name) {
        Sqler sqler = new Sqler(table);
        sqler.addEqual("t.deptid", deptid);
        sqler.addLike("t.name", name);
        sqler.addDescOrder("t.crtim");
        sqler.addSelect("t.crtim,t.uptim,t.deptid,t.notes");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SysOrgPost main = service.findById(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody SysOrgPost main) throws InterruptedException {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SysOrgPost main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SysOrgPostService service;

}

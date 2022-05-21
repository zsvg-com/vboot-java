package com.abc.module.sa.agent.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;

@RestController
@RequestMapping("sa/agent/main")
public class SaAgentMainApi {

    @GetMapping
    public RestResult get(String name,String catid) {
        Sqler sqler = new Sqler("sa_agent_main");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.addre");
        return RestResult.ok(service.findPageData(sqler));
    }


    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SaAgentMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody SaAgentMain main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SaAgentMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SaAgentMainService service;

}

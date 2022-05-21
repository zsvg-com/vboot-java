package com.abc.module.sa.prod.tem;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sa/prod/tem")
public class SaProdTemApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("sa_prod_tem");
        sqler.addLike("t.name", name);
        sqler.addLeftJoin("m.name as modna", "te_prod_model m", "m.id=t.modid");
        sqler.addOrder("t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SaProdTem main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody SaProdTem main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SaProdTem main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SaProdTemService service;

}

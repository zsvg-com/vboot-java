package com.abc.module.sa.draw.main;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sa/draw/main")
public class SaDrawMainApi {

    @GetMapping
    public RestResult get(String name,String catid) {
        Sqler sqler = new Sqler("sa_draw_main");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.catid", catid);
        return RestResult.ok(service.findPageData(sqler));
    }


    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SaDrawMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody SaDrawMain main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SaDrawMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SaDrawMainService service;

}

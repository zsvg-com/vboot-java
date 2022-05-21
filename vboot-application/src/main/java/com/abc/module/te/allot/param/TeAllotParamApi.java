package com.abc.module.te.allot.param;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/allot/param")
public class TeAllotParamApi {

    @GetMapping
    public RestResult get(String name,String modid) {
        Sqler sqler = new Sqler("te_allot_param");
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeAllotParam main = service.findOne(id);
        return RestResult.ok(main);
    }

    @GetMapping("screens")
    public RestResult screens(String id) {
        return RestResult.ok(service.findData(id));
    }

    @PostMapping
    public RestResult post(@RequestBody TeAllotParam main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeAllotParam main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeAllotParamService service;

}

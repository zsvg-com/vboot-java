package com.abc.module.te.allot.check;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/allot/check")
public class TeAllotCheckApi {

    @GetMapping
    public RestResult get(String name,String modid) {
        Sqler sqler = new Sqler("te_allot_check");
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeAllotCheck main = service.findOne(id);
        return RestResult.ok(main);
    }


    @GetMapping("checks")
    public RestResult checks(String id) {
        return RestResult.ok(service.findData(id));
    }



    @PostMapping
    public RestResult post(@RequestBody TeAllotCheck main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeAllotCheck main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeAllotCheckService service;

}

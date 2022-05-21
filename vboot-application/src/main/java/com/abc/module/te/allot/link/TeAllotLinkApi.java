package com.abc.module.te.allot.link;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/allot/link")
public class TeAllotLinkApi {

    @GetMapping
    public RestResult get(String name,String modid) {
        Sqler sqler = new Sqler("te_allot_link");
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeAllotLink main = service.findOne(id);
        return RestResult.ok(main);
    }

    @GetMapping("links")
    public RestResult links(String id) {
        return RestResult.ok(service.findData(id));
    }

    @PostMapping
    public RestResult post(@RequestBody TeAllotLink main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeAllotLink main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeAllotLinkService service;

}

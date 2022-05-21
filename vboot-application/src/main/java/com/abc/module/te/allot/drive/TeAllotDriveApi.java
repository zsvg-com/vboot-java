package com.abc.module.te.allot.drive;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/allot/drive")
public class TeAllotDriveApi {

    @GetMapping
    public RestResult get(String name,String modid) {
        Sqler sqler = new Sqler("te_allot_drive");
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeAllotDrive main = service.findOne(id);
        return RestResult.ok(main);
    }


    @GetMapping("drives")
    public RestResult drives(String id) {
        return RestResult.ok(service.findData(id));
    }


    @PostMapping
    public RestResult post(@RequestBody TeAllotDrive main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeAllotDrive main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeAllotDriveService service;

}

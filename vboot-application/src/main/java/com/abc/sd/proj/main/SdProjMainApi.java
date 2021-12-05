package com.abc.sd.proj.main;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sd/proj/main")
public class SdProjMainApi {

    //分页查询项目清单
    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("sd_proj_main");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.addre,t.crtim,t.notes");
        return RestResult.ok(service.findPageData(sqler));
    }

    //查询单个项目
    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SdProjMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    //新增项目
    @PostMapping
    public RestResult post(@RequestBody SdProjMain main) {
        return RestResult.ok(service.insert(main));
    }

    //修改项目
    @PutMapping
    public RestResult put(@RequestBody SdProjMain main) {
        return RestResult.ok(service.update(main));
    }

    //删除项目
    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SdProjMainService service;

}

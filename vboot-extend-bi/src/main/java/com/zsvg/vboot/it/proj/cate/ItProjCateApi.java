package com.zsvg.vboot.it.proj.cate;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("it/proj/cate")
public class ItProjCateApi {

    private String table = "it_proj_cate";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes,t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        ItProjCate cate = service.findOne(id);
        return RestResult.ok(cate);
    }

    @PostMapping
    public RestResult post(@RequestBody ItProjCate cate) {
        return RestResult.ok(service.insert(cate));
    }

    @PutMapping
    public RestResult put(@RequestBody ItProjCate cate) {
        return RestResult.ok(service.update(cate));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private ItProjCateService service;

}
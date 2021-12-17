package com.zsvg.vboot.ps.proj.cate;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ps/proj/cate")
public class PsProjCateApi {

    private String table = "ps_proj_cate";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addSelect("t.pid,t.crtim,t.uptim");
        sqler.addLike("t.name", name);
        sqler.addOrder("t.ornum");
        return RestResult.ok(service.findTree(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        PsProjCate cate = service.findOne(id);
        return RestResult.ok(cate);
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        List<Ztree> list = service.findTreeList(table,name);
        return RestResult.ok(list);
    }

    @PostMapping
    public RestResult post(@RequestBody PsProjCate main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody PsProjCate main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private PsProjCateService service;
}
package com.zsvg.vboot.ps.proj.main;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ps/proj/main")
public class PsProjMainApi {

    private String table = "ps_proj_main";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        PsProjMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    @GetMapping("list")
    public RestResult getList(String name) {
        Sqler sqler = new Sqler("id value,name label",table);
        return RestResult.ok(jdbcDao.findMapList(sqler));
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        return RestResult.ok(service.findTree(name));
    }

    @PostMapping
    public RestResult post(@RequestBody PsProjMain main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody PsProjMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private PsProjMainService service;


    @Autowired
    private JdbcDao jdbcDao;

}
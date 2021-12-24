package com.zsvg.vboot.ps.proj.stage;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ps/proj/stage")
public class PsProjStageApi {

    private String table = "ps_proj_stage";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findMapList(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        PsProjStage main = service.findOne(id);
        return RestResult.ok(main);
    }

    @GetMapping("list")
    public RestResult getList(String projid) {
        Sqler sqler = new Sqler("id,name,projid",table);
        sqler.addEqual("t.projid", projid);
        return RestResult.ok(jdbcDao.findMapList(sqler));
    }

    @PostMapping
    public RestResult post(@RequestBody PsProjStage main) {
//        main.setId(null);
        return RestResult.ok(service.insert(main).getId());
    }

    @PutMapping
    public RestResult put(@RequestBody PsProjStage main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private PsProjStageService service;

    @Autowired
    private JdbcDao jdbcDao;

}
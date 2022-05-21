package com.abc.module.te.feat.screen;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Zinp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("te/feat/screen")
public class TeFeatScreenApi {

    @GetMapping
    public RestResult get(String name,String catid) {
        Sqler sqler = new Sqler("te_feat_screen");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.catid", catid);
        sqler.addLeftJoin("c.name as catna", "te_prod_cate c", "c.id=t.catid");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        List<Zinp> list = service.findTreeList(name);
        return RestResult.ok(list);
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeFeatScreen main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody TeFeatScreen main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeFeatScreen main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeFeatScreenService service;

}
